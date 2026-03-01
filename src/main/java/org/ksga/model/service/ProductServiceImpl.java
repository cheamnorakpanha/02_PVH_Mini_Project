package org.ksga.model.service;

import org.ksga.model.entity.Product;
import org.ksga.utils.DatabaseUtils;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.ksga.view.BoxBorder.*;


public class ProductServiceImpl implements ProductService {
    ArrayList<Product> insertProduct = new ArrayList<>();
    ArrayList<Product> updateProduct = new ArrayList<>();

    public static Scanner scanner = new Scanner(System.in);

    @Override
    public List<Product> displayProductList() {
        List<Product> products = new ArrayList<>();
        String displayProducts = "SELECT id, name, unit_price, quantity, imported_date FROM products";
        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(displayProducts)) {

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("unit_price"),
                        resultSet.getInt("quantity"),
                        resultSet.getDate("imported_date").toLocalDate()
                );
                products.add(product);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    @Override
    public void writeProducts(Product product) {
        insertProduct.add(product);
    }


    @Override
    public Product readProductById(Integer id) {
        return null;
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public String deleteProduct(Integer id) {
        return "";
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        return List.of();
    }

    @Override
    public void setDisplayRow(int rows) {

    }

    @Override
    public int displayRow() {
        return 0;
    }

    @Override
    public void saveProduct(String saveProduct) {
        if(saveProduct.equals("si")){
            for (Product product : insertProduct) {
                String insertQuery = "INSERT INTO products (name, unit_price, quantity, imported_date) VALUES (?, ?, ?, ?)";
                try (Connection connection = DatabaseUtils.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                    preparedStatement.setString(1, product.getName());
                    preparedStatement.setDouble(2, product.getUnitPrice());
                    preparedStatement.setInt(3, product.getQuantity());
                    preparedStatement.setDate(4, Date.valueOf(product.getImportedDate()));

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            insertProduct.clear();
        } else if(saveProduct.equals("su")){
            for (Product product : updateProduct) {
                String updateQuery = "UPDATE products SET name = ?, unit_price = ?, quantity = ?, imported_date = ? WHERE id = ?";
                try (Connection connection = DatabaseUtils.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

                    preparedStatement.setString(1, product.getName());
                    preparedStatement.setDouble(2, product.getUnitPrice());
                    preparedStatement.setInt(3, product.getQuantity());
                    preparedStatement.setDate(4, Date.valueOf(product.getImportedDate()));
                    preparedStatement.setInt(5, product.getId());

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            updateProduct.clear();
        }
    }

    @Override
    public void unsavedProduct(Product products, String unsavedProduct) {
        if(unsavedProduct.equals("ui")){
            displayProduct("insert");
        }else if(unsavedProduct.equals("uu")){
            displayProduct("update");
        }else{
            System.out.println(red + "Invalid input. Please enter 'ui' for unsaved insert products or 'uu' for unsaved update products." + reset);
        }
    }

    @Override
    public boolean backupProducts(String backupDirectory) {
        return false;
    }

    @Override
    public boolean restoreProducts() {
        return false;
    }

    @Override
    public void displayProduct(String displayProduct) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);

        String title = displayProduct.equals("insert") ? "UNSAVED INSERT PRODUCTS" : "UNSAVED UPDATE PRODUCTS";
        table.addCell(magenta + title + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 5);

        table.addCell(magenta + "ID" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + "NAME" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + "UNIT PRICE" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + "QUANTITY" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(magenta + "IMPORTED_DATE" + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));

        for (int i = 0; i < 5; i++) {
            table.setColumnWidth(i, 25, 25);
        }

        if(displayProduct.equals("insert")){
            for (Product product : insertProduct) {
                table.addCell(blue + product.getId() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(blue + product.getName() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(blue + product.getUnitPrice() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(blue + product.getQuantity() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(blue + product.getImportedDate() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            }
        } else if(displayProduct.equals("update")){
            for (Product product : updateProduct) {
                table.addCell(blue + product.getId() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(blue + product.getName() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(blue + product.getUnitPrice() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(blue + product.getQuantity() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(blue + product.getImportedDate() + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            }
        }
        System.out.println(table.render());
    }
}
