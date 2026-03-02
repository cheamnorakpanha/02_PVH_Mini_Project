package org.ksga.view;

import org.ksga.controller.ProductController;
import org.ksga.exceptions.ProductHelper;
import org.ksga.model.entity.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.ksga.view.BoxBorder.*;

public class View {
    private final ProductController productController;
    static Scanner scanner = new Scanner(System.in);

    private static List<Product> products = new ArrayList<>();
    private int currentPage = 1;
    private int rowPerPage = 10;
    private List<Product> cachedProducts = new ArrayList<>();

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
            CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
            table2.setColumnWidth(0, 20, 25);
            table2.setColumnWidth(1, 20, 25);
            table2.setColumnWidth(2, 20, 25);
            table2.setColumnWidth(3, 20, 25);
            table2.setColumnWidth(4, 20, 25);

            table2.addCell(" ");
            table2.addCell(" ", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table2.addCell(magenta + "APPLICATION MENU"+ reset, new CellStyle(CellStyle.HorizontalAlign.CENTER)); // Set colspan to 5
            table2.addCell(" ", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table2.addCell(" ");
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(20));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(20));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(20));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(20));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(20));

            table2.addCell(cyan + "  (F) First Page");
            table2.addCell(cyan + "  (N) Next Page");
            table2.addCell(cyan + "  (P) Previous Page");
            table2.addCell(cyan + "  (L) Last Page");
            table2.addCell(cyan + "  (G) GOTO" + reset);


            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(20));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(20));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(20));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(20));
            table2.addCell(HORIZONTAL_CONNECTOR_BORDER.repeat(20));

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
            table2.addCell(cyan + "  (E)EXIT" + reset);

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
                    int totalPages = (int) Math.ceil((double) cachedProducts.size() / rowPerPage);
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
                    System.out.print("Enter product ID: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        getProductById(id);
                    } catch (NumberFormatException e) {
                        System.out.println(red + "Invalid ID! Please enter a number." + reset);
                        System.out.println(yellow+"Press Enter to continue..."+reset);
                        scanner.nextLine();
                    }
                    break;
                case "u":
                    updateProduct();
                    break;
                case "d":
                    deleteProduct();
                    break;
                case "s":

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

                    break;
                case "re":

                    break;
                case "e":

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
    public static void searchById() {

    }

    public static void BackupDate(String backupDirectory) {

    }

    public List<String> listBackupFiles(String backupDirectory) {
        return null;
    }

    public static void RestoreDate() {

    }

    public static void SearchProductByName() throws SQLException {

    }
    public static void DeleteProductByID(){

    }

    public void writeProduct() {
        try {
            Integer id = productController.displayAllProducts().size() + 1;
            System.out.println("ID: " + id);

            System.out.print("Enter the product name: ");
            String name = scanner.nextLine().trim();
            ProductHelper.validateProductName(name);

            System.out.print("Enter the unit price: ");
            Double unitPrice = Double.parseDouble(scanner.nextLine().trim());
            ProductHelper.validateUnitPrice(unitPrice);

            System.out.print("Enter the quantity: ");
            Integer quantity = Integer.parseInt(scanner.nextLine().trim());
            ProductHelper.validateQuantity(quantity);

            Product product = new Product(id, name, unitPrice, quantity, LocalDate.now());

            productController.insertProduct(product);

            System.out.println(green + "Product create successfully! Press Enter to continue..." + reset);
            displayAllProducts();
            scanner.nextLine();

        } catch (NumberFormatException e) {
            System.out.println(red + "Invalid input! Please enter a valid number." + reset);
        } catch (Exception e) {
            System.out.println(red + "Validation Error: " + e.getMessage() + reset);
        }
    }

    public void unsavedProduct() {
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
        } else if (opt.equals("b")) {
            return;
        } else {
            System.out.println(red + "Invalid option! Please enter 'ui', 'uu' or 'b'." + reset);
        }
    }

    public void saveProduct() {
        System.out.println(
                "\n" +
                        green + "si" + reset + " for saving insert products and " +
                        green + "su" + reset + " for saving update products or " +
                        green + "b" + reset + " for back to menu"
        );
        System.out.print("Enter option : ");
        String opt = scanner.nextLine().trim().toLowerCase();

        if (opt.equals("si") || opt.equals("su")) {
            System.out.println(green + "Saving products to successfully" + reset);
            productController.saveProduct(opt);
            displayAllProducts();
        } else if (opt.equals("b")) {
            return;
        } else {
            System.out.println(red + "Invalid option! Please enter 'si' or 'su'." + reset);
        }
    }
    private void displayPage(int page){
        cachedProducts = productController.displayAllProducts();
        int totalRecords = cachedProducts.size();
        int totalPage = (int) Math.ceil((double) totalRecords / rowPerPage);

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
            Product p = cachedProducts.get(i);
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

    private void getProductById(int id) {
        Product product = productController.getProductById(id);

        if (product == null) {
            System.out.println(red + "No product found with ID: " + id + reset);
            System.out.println(yellow+"Press Enter to continue..."+reset);
            scanner.nextLine();
            return;
        }

        Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);

        table.addCell(magenta + "Product Details (ID=" + id + ")" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 5);

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
                        System.out.print("Input Product Name : ");
                        String newName = scanner.nextLine().trim();
                        ProductHelper.validateProductName(newName);
                        name = newName;
                        break;
                    case "2":
                        System.out.print("Input Unit Price : ");
                        double newPrice = Double.parseDouble(scanner.nextLine().trim());
                        ProductHelper.validateUnitPrice(newPrice);
                        unitPrice = newPrice;
                        break;
                    case "3":
                        System.out.print("Input Quantity : ");
                        int newQty = Integer.parseInt(scanner.nextLine().trim());
                        ProductHelper.validateQuantity(newQty);
                        quantity = newQty;
                        break;
                    case "4":
                        System.out.print("Input Product Name : ");
                        String allName = scanner.nextLine().trim();
                        ProductHelper.validateProductName(allName);
                        name = allName;

                        System.out.print("Input Unit Price : ");
                        double allPrice = Double.parseDouble(scanner.nextLine().trim());
                        ProductHelper.validateUnitPrice(allPrice);
                        unitPrice = allPrice;

                        System.out.print("Input Quantity : ");
                        int allQty = Integer.parseInt(scanner.nextLine().trim());
                        ProductHelper.validateQuantity(allQty);
                        quantity = allQty;
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
