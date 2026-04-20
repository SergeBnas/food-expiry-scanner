package com.example.foodexpiryscanner.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodexpiryscanner.R
import com.example.foodexpiryscanner.data.local.InventoryItemEntity
import com.example.foodexpiryscanner.databinding.ItemInventoryBinding
import com.example.foodexpiryscanner.utils.DateUtils

class InventoryAdapter(
    private val onDelete: (InventoryItemEntity) -> Unit
) : RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>() {

    private var items: List<InventoryItemEntity> = emptyList()

    fun submitList(newItems: List<InventoryItemEntity>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val binding = ItemInventoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InventoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        holder.bind(items[position], onDelete)
    }

    override fun getItemCount(): Int = items.size

    class InventoryViewHolder(private val binding: ItemInventoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: InventoryItemEntity, onDelete: (InventoryItemEntity) -> Unit) {
            binding.tvName.text = item.name
            binding.tvBrand.text = item.brand.ifBlank { item.category }
            binding.tvMeta.text = "Qty ${item.quantity} • Expiry ${item.expiryDate}"
            binding.tvSyncState.text = if (item.isSynced) "Synced" else "Pending Sync"
            binding.tvSyncState.setBackgroundResource(if (item.isSynced) R.drawable.bg_badge else R.drawable.bg_badge_warning)

            val daysLeft = DateUtils.daysUntil(item.expiryDate)
            val daysLabel = when {
                daysLeft == null -> "Unknown date"
                daysLeft < 0 -> "Expired"
                daysLeft == 0 -> "Expires today"
                daysLeft == 1 -> "Expires tomorrow"
                else -> "${daysLeft} days left"
            }
            binding.tvDaysLeft.text = daysLabel

            when {
                daysLeft == null -> binding.tvDaysLeft.setBackgroundResource(R.drawable.bg_badge)
                daysLeft < 0 || daysLeft <= 1 -> binding.tvDaysLeft.setBackgroundResource(R.drawable.bg_badge_danger)
                daysLeft <= 3 -> binding.tvDaysLeft.setBackgroundResource(R.drawable.bg_badge_warning)
                else -> binding.tvDaysLeft.setBackgroundResource(R.drawable.bg_badge)
            }

            Glide.with(binding.ivProduct.context)
                .load(item.imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_gallery)
                .into(binding.ivProduct)

            binding.btnDelete.setOnClickListener { onDelete(item) }
        }
    }
}
