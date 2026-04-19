const express = require('express');
const cors = require('cors');
const fs = require('fs');
const path = require('path');

const app = express();
const PORT = process.env.PORT || 5000;
const DB_FILE = path.join(__dirname, 'data', 'inventory.json');

app.use(cors());
app.use(express.json());

function ensureDb() {
  if (!fs.existsSync(path.dirname(DB_FILE))) {
    fs.mkdirSync(path.dirname(DB_FILE), { recursive: true });
  }
  if (!fs.existsSync(DB_FILE)) {
    fs.writeFileSync(DB_FILE, JSON.stringify([], null, 2));
  }
}

function readItems() {
  ensureDb();
  return JSON.parse(fs.readFileSync(DB_FILE, 'utf8'));
}

function writeItems(items) {
  ensureDb();
  fs.writeFileSync(DB_FILE, JSON.stringify(items, null, 2));
}

function matchesSameItem(a, b) {
  return (a.barcode || '') === (b.barcode || '') &&
    (a.name || '').trim().toLowerCase() === (b.name || '').trim().toLowerCase() &&
    (a.expiryDate || '') === (b.expiryDate || '');
}

app.get('/', (_req, res) => {
  res.json({ message: 'ShelfLife backend is running' });
});

app.get('/api/items', (_req, res) => {
  res.json(readItems());
});

app.post('/api/items', (req, res) => {
  const items = readItems();
  const incoming = {
    barcode: req.body.barcode || '',
    name: req.body.name || 'Unknown product',
    brand: req.body.brand || '',
    category: req.body.category || 'Unknown',
    quantity: Number(req.body.quantity || 1),
    expiryDate: req.body.expiryDate || '',
    imageUrl: req.body.imageUrl || '',
    notes: req.body.notes || ''
  };

  const existingIndex = items.findIndex((item) => matchesSameItem(item, incoming));
  if (existingIndex >= 0) {
    items[existingIndex] = { ...items[existingIndex], ...incoming };
    writeItems(items);
    return res.status(200).json(items[existingIndex]);
  }

  const item = {
    _id: Date.now().toString(),
    ...incoming
  };
  items.push(item);
  writeItems(items);
  res.status(201).json(item);
});

app.put('/api/items/:id', (req, res) => {
  const items = readItems();
  const index = items.findIndex((item) => item._id === req.params.id);
  if (index === -1) {
    return res.status(404).json({ error: 'Item not found' });
  }
  items[index] = { ...items[index], ...req.body, _id: req.params.id };
  writeItems(items);
  res.json(items[index]);
});

app.delete('/api/items/:id', (req, res) => {
  const items = readItems();
  const filtered = items.filter((item) => item._id !== req.params.id);
  if (filtered.length === items.length) {
    return res.status(404).json({ error: 'Item not found' });
  }
  writeItems(filtered);
  res.status(204).send();
});

app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});
