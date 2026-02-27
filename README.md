# Group2_PVH_Mini_Project_Java

## Git Rules

### Commit Message Naming
```
1. Start commit messages with a capital letter.
2. Use clear, short, descriptive wording.
```

Examples:
- `First commit`
- `Add product display page`
- `Fix login validation bug`

### Branch Naming
```
1. Branch names must be all lowercase.
2. Use hyphens (`-`) to separate words.
```

Examples:
- `display-products`
- `fix-login-validation`
- `update-readme`

## Project Structure

```text
Group2_PVH_Mini_Project_Java/
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
