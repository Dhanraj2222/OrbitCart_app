require('dotenv').config();
const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const productRoutes = require('./routes/productRoutes');
const path = require('path'); // Ensure the `path` module is required

const app = express();

// Middleware
app.use(cors());
app.use(bodyParser.json());

// Serve static images from the public/images directory
app.use('/images', express.static(path.join(__dirname, 'public/images')));

// Routes
app.use('/api/products', productRoutes);

// Server
const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
    console.log(`Server is running on http://192.168.43.44:${PORT}`);
    console.log(`Static files served from: ${path.join(__dirname, 'public/images')}`);
});
