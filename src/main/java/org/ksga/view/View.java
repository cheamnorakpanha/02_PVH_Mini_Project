package org.ksga.view;

import org.ksga.controller.ProductController;
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

    public void menu(){

        do {
            Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
            table.addCell(magenta +"ALL PRODUCTS INFO" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 5);
            table.addCell(magenta +"ID"+ reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(magenta +"NAME"+ reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(magenta +"UNIT PRICE"+ reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(magenta +"QUANTITY"+ reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(magenta +"IMPORTED_DATE"+ reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));

            for (int i = 0; i < 5; i++) {
                table.setColumnWidth(i, 25, 25);
            }


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
            ////////////////////////////////////////////////////////////////
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

                    break;
                case "p":

                    break;
                case "f":

                    break;
                case "l":
                    break;
                case "g":

                    break;

                case "w":

                    break;
                case "r":
                    productController.displayAllProducts();
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

                    break;
                case "un":

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
}
