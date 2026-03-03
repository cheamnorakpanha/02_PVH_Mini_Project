package org.ksga.view;

import org.ksga.controller.ProductController;
import org.ksga.exceptions.ProductHelper;
import org.ksga.model.entity.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.ksga.view.BoxBorder.*;

public class View {
    private final ProductController productController;
    static Scanner scanner = new Scanner(System.in);

    private static List<Product> products = new ArrayList<>();
    private int currentPage = 1;
    private int rowPerPage = 10;

    public View(ProductController productController){
        this.productController = productController;
        int savedRow = productController.displayRow();
        if (savedRow > 0) {
            this.rowPerPage = savedRow;
        }
    }

    public void displayAllProducts(){
        displayPage(currentPage);
        do {

            Table table2 = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);

            table2.addCell(" ");
            table2.addCell(" ", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table2.addCell(magenta + "APPLICATION MENU"+ reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table2.addCell(" ", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table2.addCell(" ");
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(25));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(25));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(25));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(25));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(25));

            table2.addCell(cyan + "  (F) First Page");
            table2.addCell(cyan + "  (N) Next Page");
            table2.addCell(cyan + "  (P) Previous Page");
            table2.addCell(cyan + "  (L) Last Page");
            table2.addCell(cyan + "  (G) Goto" + reset);


            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(25));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(25));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(25));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(25));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(25));

            table2.addCell(cyan + "  (W) Write");
            table2.addCell(cyan + "  (R) Read (id)");
            table2.addCell(cyan + "  (U) Update");
            table2.addCell(cyan + "  (D) Delete");
            table2.addCell(cyan + "  (S) Search (name)");
            table2.addCell(cyan + "  (Se) Set rows");
            table2.addCell(cyan + "  (Sa) Save");
            table2.addCell(cyan + "  (Un) Unsaved");
            table2.addCell(cyan + "  (Ba) Backup");
            table2.addCell(cyan + "  (Re) Restore");
            table2.addCell(cyan + "  (E)Exit" + reset);

            System.out.println(table2.render());

            System.out.print(blue + "Choose Option: " +reset);
            String option = scanner.nextLine().trim().toLowerCase();

            switch (option) {
                case "n":
                    displayPage(currentPage+1);
                    break;
                case "p":
                    displayPage(currentPage-1);
                    break;
                case "f":
                    displayPage(1);
                    break;
                case "l":
                    int totalPages = (int) Math.ceil((double) products.size() / rowPerPage);
                    displayPage(totalPages);
                    break;
                case "g":
                    System.out.print("page number :");
                    try {
                        int page = Integer.parseInt(scanner.nextLine().trim());
                        displayPage(page);
                    } catch (NumberFormatException e) {
                        System.out.println(red + "Invalid page number!" + reset);
                    }
                    break;

                case "w":
                    writeProduct();
                    break;
                case "r":
                    searchById();
                    break;
                case "u":
                    updateProduct();
                    break;
                case "d":
                    deleteProduct();
                    break;
                case "s":
                    searchProductByName();
                    break;
                case "se":
                    setRow();
                    break;
                case "sa":
                    saveProduct();
                    break;
                case "un":
                    unsavedProduct();
                    break;
                case "ba":
                    handleBackup();
                    break;
                case "re":
                    handleRestore();
                    break;
                case "e":
                    System.out.println(green + "Exiting application. Goodbye!" + reset);
                    break;

                default:
                    System.out.println(red+"invalid option, please choose valid option."+ reset);
            }
        } while (true);
    }

    public void setRow() {
        System.out.println(cyan + "Current rows per page: " + yellow + rowPerPage + reset);
        System.out.println(cyan + "1. Set new row count");
        System.out.println("2. Reset to default (10)");
        System.out.println("3. Back to menu" + reset);
        System.out.print(blue + "Choose an option: " + reset);
        String option = scanner.nextLine().trim();

        switch (option) {
            case "1":
                System.out.print("Enter number of rows per page: ");
                try {
                    int newRows = Integer.parseInt(scanner.nextLine().trim());
                    if (newRows < 1) {
                        System.out.println(red + "Row count must be at least 1!" + reset);
                        break;
                    }
                    rowPerPage = newRows;
                    productController.setRow(rowPerPage);
                    System.out.println(green + "Rows per page set to " + rowPerPage + " and saved!" + reset);
                    displayPage(currentPage);
                } catch (NumberFormatException e) {
                    System.out.println(red + "Invalid input! Please enter a valid number." + reset);
                }
                break;
            case "2":
                rowPerPage = 10;
                productController.setRow(rowPerPage);
                System.out.println(green + "Rows per page reset to default (10) and saved!" + reset);
                displayPage(currentPage);
                break;
            case "3":
                break;
            default:
                System.out.println(red + "Invalid option! Please choose 1-3." + reset);
                break;
        }
    }
    public  void searchById() {
        int id;
        Product product;
        while (true) {
            try {
                System.out.print("Enter product ID: ");
                id = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(red + "Invalid ID! Please enter a number." + reset);
                continue;
            }
            product = productController.getProductById(id);

            if (product == null) {
                System.out.println(red + "No product found with ID: " + id + reset);
                continue;
            }
            break;
        }
        Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);

        table.addCell(magenta + "PRODUCT DETAILS" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 5);

        table.addCell(magenta + "ID" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + "NAME" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + "UNIT PRICE" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + "QUANTITY" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + "IMPORTED_DATE" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));

        for (int i = 0; i < 5; i++) {
            table.setColumnWidth(i, 25, 25);
        }

        table.addCell(blue + product.getId() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(product.getName() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyan + product.getUnitPrice() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(green + product.getQuantity() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + product.getImportedDate() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));

        System.out.println(table.render());
    }

    public void handleBackup(){
        String backupDirectory = "C:\\Users\\MSI\\Desktop\\02_PVH_Mini_Project\\src\\main\\java\\org\\ksga\\backup";

        BackupData(backupDirectory);
    }
    public void BackupData(String backupDirectory) {
        boolean success = productController.backupProduct(backupDirectory);

        if (success) {
            System.out.println(green + "Backup completed successfully." + reset);
        } else {
            System.out.println(red + "Backup failed. Please check the console for errors." + reset);
        }

    }

    public List<String> listBackupFiles(String backupDirectory) {
        List<String> sqlFiles = new ArrayList<>();
        File folder = new File(backupDirectory);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".sql")) {
                        sqlFiles.add(file.getName());
                    }
                }
            }
        }
        return sqlFiles;
    }

    public void handleRestore(){
        String backupDirectory = "C:\\Users\\MSI\\Desktop\\02_PVH_Mini_Project\\src\\main\\java\\org\\ksga\\backup";
        RestoreData(backupDirectory);
    }
    public void RestoreData(String backupDirectory) {
        List<String> files = listBackupFiles(backupDirectory);

        if (files == null || files.isEmpty()) {
            System.out.println(red + "No backup files found in: " + backupDirectory + reset);
            return;
        }

        Table table = new Table(2, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
        table.setColumnWidth(0, 5, 5);
        table.setColumnWidth(1, 50, 50);
        table.addCell(magenta +"List of Backup Data" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 2);

        for (int i = 0; i < files.size(); i++) {
            table.addCell(yellow + String.valueOf(i + 1) + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(pink + files.get(i) + reset, new CellStyle(CellStyle.HorizontalAlign.LEFT));
        }

        System.out.println(table.render());

        while (true) {
            System.out.print(cyan + "Enter backup_id to restore: " + reset);
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);

                if (choice > 0 && choice <= files.size()) {
                    String selectedFile = files.get(choice - 1);
                    String fullPath = backupDirectory + File.separator + selectedFile;

                    boolean success = productController.restoreProduct(fullPath);

                    if (success) {
                        System.out.println(green + "Database restored successfully!" + reset);
                        displayAllProducts();
                    } else {
                        System.out.println(red + "Database restore failed!" + reset);
                    }

                    break;

                } else {
                    System.out.println(red + "Invalid choice! Please enter a number between 1 and " + files.size() + "." + reset);
                }

            } catch (NumberFormatException e) {
                System.out.println(red + "Invalid input! Please enter a valid number." + reset);
            }
        }
    }

    public void searchProductByName() {
        System.out.print("Input Product Name For Search: ");
        String inputName = scanner.nextLine();

        if (inputName.trim().isEmpty()) {
            System.out.println(red + "Search term cannot be empty." + reset);
            return;
        }

        List<Product> searchResults = productController.getProductByName(inputName);

        if (searchResults.isEmpty()) {
            System.out.println(red + "No products found matching: " + inputName + reset);
            return;
        }

        Table searchTable = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
        searchTable.addCell(magenta + "SEARCH BY NAME" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 5);
        searchTable.addCell(red + "ID" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        searchTable.addCell(red + "NAME" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        searchTable.addCell(red + "UNIT PRICE" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        searchTable.addCell(red + "QUANTITY" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        searchTable.addCell(red + "IMPORTED DATE" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        for (int i = 0; i < 5; i++) {
            searchTable.setColumnWidth(i, 25, 25);
        }
        for (Product product : searchResults) {
            searchTable.addCell(blue + String.valueOf(product.getId()) + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            searchTable.addCell(product.getName(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            searchTable.addCell(cyan + String.format("%.2f", product.getUnitPrice()) + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            searchTable.addCell(green + String.valueOf(product.getQuantity()) + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            searchTable.addCell(magenta + String.valueOf(product.getImportedDate()) + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        }
        System.out.println(searchTable.render());
    }

    public void writeProduct() {
        List<Product> existingProducts = productController.displayAllProducts();

        Integer id = existingProducts.size() + 1;
        System.out.println("ID: " + id);

        String name = "";
        Double unitPrice = 0.0;
        Integer quantity = 0;

        while (true) {
            System.out.print("Enter the product name: ");
            name = scanner.nextLine().trim();

            if (!ProductHelper.validateProductName(name)) {
                System.out.println(red + "Invalid input! Product name must be 4-250 characters, cannot start with a number, and cannot contain spaces." + reset);
                continue;
            }

            boolean isDuplicate = false;
            for (Product p : existingProducts) {
                if (p.getName().equalsIgnoreCase(name)) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                System.out.println(red + "Invalid input! This product name already exists." + reset);
                continue;
            }

            break;
        }

        while (true) {
            System.out.print("Enter the unit price: ");
            String priceStr = scanner.nextLine().trim();

            if (!ProductHelper.validateUnitPrice(priceStr)) {
                System.out.println(red + "Invalid input! Please enter a valid number for the price (e.g., 10 or 10.50)." + reset);
                continue;
            }

            unitPrice = Double.parseDouble(priceStr);
            if (unitPrice <= 0) {
                System.out.println(red + "Invalid input! Unit price must be greater than 0." + reset);
                continue;
            }

            break;
        }

        while (true) {
            System.out.print("Enter the quantity: ");
            String qtyStr = scanner.nextLine().trim();

            if (!ProductHelper.validateQuantity(qtyStr)) {
                System.out.println(red + "Invalid input! Please enter a valid whole number for quantity." + reset);
                continue;
            }

            quantity = Integer.parseInt(qtyStr);
            if (quantity < 0) {
                System.out.println(red + "Invalid input! Quantity cannot be negative." + reset);
                continue;
            }

            break;
        }

        try {
            Product product = new Product(id, name, unitPrice, quantity, LocalDate.now());
            productController.insertProduct(product);

            System.out.println(green + "Product created successfully!" + reset);
            displayAllProducts();

            System.out.print(yellow + "Press Enter to continue..." + reset);
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(red + "Error saving product: " + e.getMessage() + reset);
        }
    }

    public void unsavedProduct() {
        while (true) {
            System.out.println("\n" +
                    green + "ui" + reset + " for viewing insert products and " +
                    green + "uu" + reset + " for viewing update products or " +
                    red + "b" + reset + " for back to menu"
            );
            System.out.print("Enter the option: ");
            String opt = scanner.nextLine().trim().toLowerCase();

            if (opt.equals("ui") || opt.equals("uu")) {
                System.out.println(green + "Displaying unsaved products:" + reset);
                productController.unSaveProduct(null, opt);
                break;
            } else if (opt.equals("b")) {
                return;
            } else {
                System.out.println(red + "Invalid option! Please enter 'ui', 'uu' or 'b'." + reset);
            }
        }
    }

    public void saveProduct() {
        while (true) {
            System.out.println(
                    "\n" +
                            green + "si" + reset + " for saving insert products and " +
                            green + "su" + reset + " for saving update products or " +
                            green + "b" + reset + " for back to menu"
            );
            System.out.print("Enter option : ");
            String opt = scanner.nextLine().trim().toLowerCase();

            if (opt.equals("si") || opt.equals("su")) {
                System.out.println(green + "Saving products successfully" + reset);
                productController.saveProduct(opt);
                displayAllProducts();
                break;
            } else if (opt.equals("b")) {
                return;
            } else {
                System.out.println(red + "Invalid option! Please enter 'si', 'su', or 'b'." + reset);
            }
        }
    }
    private void displayPage(int page){
        products = productController.displayAllProducts();
        int totalRecords = products.size();
        int totalPage = (int) Math.ceil((double) totalRecords / rowPerPage);

        if (totalPage == 0) {
            totalPage = 1;
        }
        if(page < 1) page = 1;
        if(page > totalPage) page = totalPage;

        currentPage = page;

        int start = (currentPage - 1)*rowPerPage;
        int end = Math.min(start + rowPerPage,totalRecords);

        Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
        table.addCell(magenta +"ALL PRODUCTS INFO" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 5);
        table.addCell(red + "ID" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell( red+"NAME" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(red + "UNIT PRICE" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(red + "QUANTITY" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(red + "IMPORTED_DATE" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        for (int i = 0; i < 5; i++) {
            table.setColumnWidth(i, 25, 25);
        }
        for (int i =start;i<end;i++){
            Product p = products.get(i);
            table.addCell(blue + String.valueOf(p.getId()) + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(  p.getName() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(cyan + String.valueOf(p.getUnitPrice()) + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(green + String.valueOf(p.getQuantity()) + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(magenta + String.valueOf(p.getImportedDate()) + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        }
        table.addCell(magenta + "Page : "+currentPage+" of "+totalPage + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER),2);
        table.addCell(magenta + "Total Record : "+totalRecords+ reset, new CellStyle(CellStyle.HorizontalAlign.CENTER),3);
        System.out.println(table.render());
    }


    public void updateProduct() {
        try {
            System.out.print("Enter the product ID to update: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            List<Product> all = productController.displayAllProducts();
            Product existingProduct = null;
            for (Product p : all) {
                if (p.getId() == id) {
                    existingProduct = p;
                    break;
                }
            }

            if (existingProduct == null) {
                System.out.println(red + "Product with ID " + id + " not found!" + reset);
                return;
            }

            System.out.println(green + "Existing Product Details:" + reset);
            displayProductTable(existingProduct);

            String name = existingProduct.getName();
            double unitPrice = existingProduct.getUnitPrice();
            int quantity = existingProduct.getQuantity();

            boolean editing = true;
            while (editing) {
                System.out.println(cyan + "1. Name   2. Unit Price   3. Qty   4. All Field   5. Exit" + reset);
                System.out.print("Choose an option to update : ");
                String option = scanner.nextLine().trim();

                switch (option) {

                    case "1":
                        while (true) {
                            System.out.print("Input Product Name : ");
                            String newName = scanner.nextLine().trim();

                            if (!ProductHelper.validateProductName(newName)) {
                                System.out.println(red +
                                        "Invalid input! Product name must be 4-250 characters and cannot start with space or number."
                                        + reset);
                                continue;
                            }

                            name = newName;
                            break;
                        }
                        break;

                    case "2":
                        while (true) {
                            System.out.print("Input Unit Price : ");
                            String priceStr = scanner.nextLine().trim();

                            if (!ProductHelper.validateUnitPrice(priceStr)) {
                                System.out.println(red +
                                        "Invalid input! Please enter a valid number (e.g., 10 or 10.50)."
                                        + reset);
                                continue;
                            }

                            double newPrice = Double.parseDouble(priceStr);
                            if (newPrice <= 0) {
                                System.out.println(red +
                                        "Invalid input! Unit price must be greater than 0."
                                        + reset);
                                continue;
                            }

                            unitPrice = newPrice;
                            break;
                        }
                        break;

                    case "3":
                        while (true) {
                            System.out.print("Input Quantity : ");
                            String qtyStr = scanner.nextLine().trim();

                            if (!ProductHelper.validateQuantity(qtyStr)) {
                                System.out.println(red +
                                        "Invalid input! Please enter a valid whole number."
                                        + reset);
                                continue;
                            }

                            int newQty = Integer.parseInt(qtyStr);
                            if (newQty < 0) {
                                System.out.println(red +
                                        "Invalid input! Quantity cannot be negative."
                                        + reset);
                                continue;
                            }

                            quantity = newQty;
                            break;
                        }
                        break;

                    case "4":
                        while (true) {
                            System.out.print("Input Product Name : ");
                            String allName = scanner.nextLine().trim();

                            if (!ProductHelper.validateProductName(allName)) {
                                System.out.println(red +
                                        "Invalid input! Product name must be 4-250 characters and cannot start with space or number."
                                        + reset);
                                continue;
                            }

                            name = allName;
                            break;
                        }

                        while (true) {
                            System.out.print("Input Unit Price : ");
                            String allPriceStr = scanner.nextLine().trim();

                            if (!ProductHelper.validateUnitPrice(allPriceStr)) {
                                System.out.println(red +
                                        "Invalid input! Please enter a valid number (e.g., 10 or 10.50)."
                                        + reset);
                                continue;
                            }

                            double allPrice = Double.parseDouble(allPriceStr);
                            if (allPrice <= 0) {
                                System.out.println(red +
                                        "Invalid input! Unit price must be greater than 0."
                                        + reset);
                                continue;
                            }

                            unitPrice = allPrice;
                            break;
                        }

                        while (true) {
                            System.out.print("Input Quantity : ");
                            String allQtyStr = scanner.nextLine().trim();

                            if (!ProductHelper.validateQuantity(allQtyStr)) {
                                System.out.println(red +
                                        "Invalid input! Please enter a valid whole number."
                                        + reset);
                                continue;
                            }

                            int allQty = Integer.parseInt(allQtyStr);
                            if (allQty < 0) {
                                System.out.println(red +
                                        "Invalid input! Quantity cannot be negative."
                                        + reset);
                                continue;
                            }

                            quantity = allQty;
                            break;
                        }
                        break;

                    case "5":
                        editing = false;
                        break;

                    default:
                        System.out.println(red + "Invalid option! Please choose 1-5." + reset);
                        break;
                }
            }

            Product updatedProduct = new Product(
                    existingProduct.getId(),
                    name,
                    unitPrice,
                    quantity,
                    existingProduct.getImportedDate()
            );

            productController.updateProduct(updatedProduct);

            System.out.println(green + "Product staged for update! Use Sa -> su to save to database." + reset);
            displayProductTable(updatedProduct);

        } catch (NumberFormatException e) {
            System.out.println(red + "Invalid input! Please enter valid numbers." + reset);
        } catch (Exception e) {
            System.out.println(red + "Error: " + e.getMessage() + reset);
        }
    }

    private void displayProductTable(Product product) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
        table.addCell(magenta + "ID" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + "NAME" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + "UNIT PRICE" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + "QUANTITY" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + "IMPORTED_DATE" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        for (int i = 0; i < 5; i++) {
            table.setColumnWidth(i, 25, 25);
        }
        table.addCell(blue + String.valueOf(product.getId()) + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(product.getName() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyan + String.valueOf(product.getUnitPrice()) + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(green + String.valueOf(product.getQuantity()) + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + String.valueOf(product.getImportedDate()) + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        System.out.println(table.render());
    }

    public void deleteProduct() {
        try {
            System.out.print(yellow + "Please input id to delete record : " + reset);
            int id = Integer.parseInt(scanner.nextLine().trim());

            List<Product> all = productController.displayAllProducts();
            Product target = null;
            for (Product p : all) {
                if (p.getId() == id) { target = p; break; }
            }

            if (target == null) {
                System.out.println(red + "Product with ID " + id + " not found!" + reset);
                System.out.print(yellow + "Enter to continue..." + reset);
                scanner.nextLine();
                return;
            }

            displayProductTable(target);

            System.out.print(yellow + "Are you sure to delete product id : " + red + id + yellow + " ? (y/n) : " + reset);
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("y")) {
                String result = productController.deleteProduct(id);
                System.out.println(green + result + reset);
            } else {
                System.out.println(yellow + "Delete cancelled." + reset);
            }

            System.out.print(yellow + "Enter to continue..." + reset);
            scanner.nextLine();

        } catch (NumberFormatException e) {
            System.out.println(red + "Invalid input! Please enter a valid number." + reset);
        } catch (Exception e) {
            System.out.println(red + "Error: " + e.getMessage() + reset);
        }
    }
}
