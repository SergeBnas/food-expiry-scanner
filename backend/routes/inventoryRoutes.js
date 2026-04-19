const express = require('express');
const { getAllItems, createItem, updateItem, deleteItem } = require('../controllers/inventoryController');

const router = express.Router();
router.get('/', getAllItems);
router.post('/', createItem);
router.put('/:id', updateItem);
router.delete('/:id', deleteItem);

module.exports = router;
