package org.ksga.model.service;

import org.ksga.model.entity.Product;
import org.ksga.utils.DatabaseUtils;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
        // write product
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
    public void saveProduct(String operation) {

    }

    @Override
    public void unsavedProduct(Product products, String operation) {

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

    }
}
