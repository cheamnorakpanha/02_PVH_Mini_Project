# 📦 Stock Management System

## 📖 Project Overview

```
This is a Java-based Stock Management System that helps businesses manage their inventory efficiently.  
It allows users to add, update, delete, and search products, track stock levels, and record stock transactions.

This project is developed as a mini project for our Java course, with a focus on team collaboration and database integration
using JDBC.
```

---

## ✨ Features
This is all Required Features in our project.

| No. | Feature | Description | Branch Name |
|-----|---------|-------------|-------------|
| 1 | Display Product List | View all products in the system | feature/display-product-list |
| 2 | Write Product | Add a new product | feature/write-product |
| 3 | Read Product by ID | Retrieve product details using its ID | feature/read-product-by-id |
| 4 | Update Product | Modify existing product information | feature/update-product |
| 5 | Delete Product | Remove a product from the system | feature/delete-product |
| 6 | Search by Product Name | Find products using part or full name | feature/search-product |
| 7 | Set Number of Display Rows | Adjust how many products appear per page | feature/set-display-rows |
| 8 | Save | Commit new or updated products to the database | feature/save-product |
| 9 | Unsave | View inserted or updated products without committing to the database | feature/unsave-product |
| 10 | Pagination | Navigate product list (First, Next, Previous, Last, Go to specific page) | feature/pagination |

---

### ⭐ Bonus Features (Optional)

| No. | Feature | Description | Branch Name |
|-----|---------|-------------|-------------|
| 1 | Recovery | Recover data by setting a specific row | feature/recovery |
| 2 | Backup | Save product data as a version backup | feature/backup |
| 3 | Restore | Restore product data from a selected backup version | feature/restore |

---

## 🛠️ Technologies Used

- Java
- JDBC
- PostgreSQL
- Git & GitHub
- IDE: IntelliJ IDEA

---

## 🗄️ Database Structure

| **Property** | **Value** |
|--------------|-----------|
| **name** | database_java_mini_project@localhost |
| **database** | stock_management |
| **url** | jdbc:postgresql://localhost:5432/stock_management |

**🔗 Project and Database Connection YouTube Video:** [Project and Database connection](https://youtu.be/K7RDTmZC0Zw).

---

## ⚙️ Setup Instructions
1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/stock-management.git
   ```
2. Open the project in your IDE **(IntelliJ IDEA).**
3. Create a MySQL database named `stock_management`.
4. Import the SQL file: `/database/stock_management.sql`
5. Update database credentials in `DatabaseConnection.java`:
    ```
    username=postgres
    password=Admin
    url=jdbc:postgresql://localhost:5432/stock_management
    ```
6. Run the project by executing `Main.java`.

---

## 🏗️ Project Structure

```text
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/ksga/
│   │   │       ├── Main.java                    # Application entry point
│   │   │       ├── controller/                  # Request/flow handlers
│   │   │       │   └── ProductController.java
│   │   │       ├── model/                       # Domain and service layer
│   │   │       │   ├── entity/
│   │   │       │   │   └── Product.java
│   │   │       │   └── service/
│   │   │       │       ├── ProductService.java
│   │   │       │       └── ProductServiceImpl.java
│   │   │       ├── view/                        # Console/UI rendering
│   │   │       │   ├── View.java
│   │   │       │   └── BoxBorder.java
│   │   │       ├── utils/                       # Shared utilities
│   │   │       │   ├── DatabaseUtils.java
│   │   │       │   └── CredentialsLoader.java
│   │   │       ├── exceptions/                  # Custom exceptions/helpers
│   │   │       │   ├── NotFoundException.java
│   │   │       │   └── ProductHelper.java
│   │   │       ├── db_scripts/                  # SQL scripts
│   │   │       │   ├── 01_create_products_table.sql
│   │   │       │   ├── 02_create_settings_table.sql
│   │   │       │   └── 03_insert_products.sql
│   │   │       └── backup/                      # Backup-related files
│   │   └── resources/
│   │       └── application.properties           # App configuration
│   └── test/
│       └── java/                                # Unit/integration tests
├── target/                                      # Maven build output
│   ├── classes/
│   └── generated-sources/
├── pom.xml                                      # Maven project setup
└── .gitignore                                   # Files/folders ignored by Git
```

