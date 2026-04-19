const mongoose = require('mongoose');

const inventoryItemSchema = new mongoose.Schema(
  {
    barcode: { type: String, required: true },
    name: { type: String, required: true },
    brand: { type: String, default: '' },
    category: { type: String, default: 'Unknown' },
    quantity: { type: Number, default: 1 },
    expiryDate: { type: String, required: true },
    imageUrl: { type: String, default: '' },
    notes: { type: String, default: '' }
  },
  { timestamps: true }
);

module.exports = mongoose.model('InventoryItem', inventoryItemSchema);
