const db = require('../models/db');

// Fetch all products
exports.getProducts = async (req, res) => {
    try {
        const [products] = await db.query('SELECT id, name, price, maxQty, image_url FROM products');
        res.status(200).json(products);
    } catch (error) {
        console.error('Error fetching products:', error.message);
        res.status(500).json({ message: 'Failed to fetch products' });
    }
};
