package org.ksga.model.service;

import org.ksga.model.entity.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> displayProductList();

    void writeProducts(Product product);

    Product readProductById(Integer id);

    void updateProduct(Product product);

    String deleteProduct(Integer id);

    List<Product> searchProductsByName(String name);

    void setDisplayRow(int rows);

    int displayRow();

    void saveProduct(String saveProduct);

    void unsavedProduct(Product products , String unsavedProduct);

    boolean backupProducts(String backupDirectory);

    String generateBackupFile(String backupDirectory);

    boolean restoreProducts(String backupFilePath);

    void deleteProductTableForRestore();

    void displayProduct(String displayProduct);
}
