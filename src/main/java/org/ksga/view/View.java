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
    public View(ProductController productController){
        this.productController = productController;
    }

    private static List<Product> products = new ArrayList<>();
    private int currentPage = 1;
    private int rowPerPage = 10;
    private List<Product> cachedProducts = new ArrayList<>();
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

                    break;
                case "u":
                    break;
                case "d":

                    break;
                case "s":

                    break;
                case "se":

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

    public static void setRow() {

    }

    public static void updateProduct() {

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
}
