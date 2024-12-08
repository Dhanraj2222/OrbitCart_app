Cart Screen Application
This is a Cart Screen Application built for Android, developed using Java with a backend powered by Node.js and SQL. The application allows users to view and manage products dynamically, calculate totals, and display detailed order information.

Features
Dynamic Product Listing:

Uses RecyclerView to display product details with images.
Supports quantity increment/decrement for each product.
Order Summary:

Displays key details like:
Item Total: Total price of selected items.
Delivery Charges: (Free in this implementation).
Total Items: Total number of items selected.
To Pay: Final payable amount.
Backend Integration:

APIs developed in Node.js and SQL for:
Fetching product details.
Managing quantity and price data.
Local Storage:

Product data is cached locally for better performance during subsequent app launches.
Technologies Used
Frontend:

Android (Java)
ConstraintLayout and LinearLayout for UI design.
Glide for loading product images.
Backend:

Node.js
SQL database for product management.
How It Works
Fetches product data from the backend and displays it in a horizontal card format using RecyclerView.
Updates item totals and quantities dynamically as the user interacts.
Displays real-time calculations of totals in the order summary section.
Future Enhancements
Add support for multiple delivery options.
Enhance UI with Material Design components.
Extend the backend for user authentication and order history.





