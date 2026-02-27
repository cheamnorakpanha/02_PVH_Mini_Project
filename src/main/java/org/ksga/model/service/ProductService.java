package org.ksga.model.service;

import org.ksga.model.entity.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    // Display Product List
    List<Product> displayProductList();

    // Write Product
    void writeProducts(Product product);

    // Read Product by id
    Product readProductById(Integer id);

    // Update
    void updateProduct(Product product);

    // Delete Product
    String deleteProduct(Integer id);

    // Search by product name
    List<Product> searchProductsByName(String name);

    // Set Number of Display Row
    void setDisplayRow(int rows);

    // display row
    int displayRow();

    // Save (Save insert product and update product to database)
    void saveProduct(String saveProduct);

    // Unsave (View insert product and update product)
    void unsavedProduct(Product products , String unsavedProduct);

    // Backup
    boolean backupProducts(String backupDirectory);

    // Restore
    boolean restoreProducts();

    // display product
    void displayProduct(String displayProduct);
}
