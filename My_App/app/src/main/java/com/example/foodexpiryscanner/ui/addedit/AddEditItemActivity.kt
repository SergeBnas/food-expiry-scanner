package com.example.foodexpiryscanner.ui.addedit

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.foodexpiryscanner.data.local.InventoryItemEntity
import com.example.foodexpiryscanner.data.remote.ApiModule
import com.example.foodexpiryscanner.databinding.ActivityAddEditItemBinding
import com.example.foodexpiryscanner.utils.DateUtils
import com.example.foodexpiryscanner.viewmodel.InventoryViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import com.example.foodexpiryscanner.R
class AddEditItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditItemBinding
    private val viewModel: InventoryViewModel by viewModels()
    private var selectedDate = ""
    private var imageUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val barcode = intent.getStringExtra("barcode") ?: ""
        binding.etBarcode.setText(barcode)
        binding.btnBack.setOnClickListener { finish() }
        binding.tvDate.setOnClickListener { openDatePicker() }
        binding.btnFetchProduct.setOnClickListener {
            val manualBarcode = binding.etBarcode.text.toString().trim()
            if (manualBarcode.isBlank()) {
                DateUtils.toast(this, "Enter a barcode first")
            } else {
                fetchProduct(manualBarcode)
            }
        }
        binding.btnSave.setOnClickListener { saveItem() }

        if (barcode.isNotBlank()) fetchProduct(barcode)
    }

    private fun openDatePicker() {
        val c = Calendar.getInstance()
        DatePickerDialog(this, { _, year, month, dayOfMonth ->
            selectedDate = DateUtils.format(year, month, dayOfMonth)
            binding.tvDate.text = getString(R.string.expiry_date_format, selectedDate)
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun fetchProduct(barcode: String) {
        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            binding.tvStatus.text = getString(R.string.loading_product_info)
            try {
                val response = ApiModule.openFoodApi.getProductByBarcode(barcode)
                if (response.isSuccessful && response.body()?.status == 1) {
                    val product = response.body()?.product
                    binding.etName.setText(product?.product_name.orEmpty())
                    binding.etBrand.setText(product?.brands.orEmpty())
                    binding.etCategory.setText(product?.categories.orEmpty())
                    imageUrl = product?.image_url.orEmpty()
                    binding.tvStatus.text = getString(R.string.product_loaded_edit_before_saving)
                } else {
                    binding.tvStatus.text = getString(R.string.product_not_found_fill_manually)
                }
            } catch (_: Exception) {
                binding.tvStatus.text = getString(R.string.no_internet_manual_save)
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun saveItem() {
        val name = binding.etName.text.toString().trim()
        val expiry = selectedDate.ifBlank { binding.tvDate.text.toString().removePrefix("Expiry date: ").trim() }
        if (name.isBlank()) {
            DateUtils.toast(this, "Please enter a product name")
            return
        }
        if (expiry.isBlank() || expiry == "tap to choose") {
            DateUtils.toast(this, "Please choose an expiry date")
            return
        }

        val item = InventoryItemEntity(
            barcode = binding.etBarcode.text.toString().trim(),
            name = name,
            brand = binding.etBrand.text.toString().trim(),
            category = binding.etCategory.text.toString().trim().ifBlank { "Unknown" },
            quantity = binding.etQuantity.text.toString().toIntOrNull() ?: 1,
            expiryDate = expiry,
            imageUrl = imageUrl,
            notes = binding.etNotes.text.toString().trim()
        )
        viewModel.insertItem(item)
        DateUtils.toast(this, "Item saved")
        finish()
    }
}
