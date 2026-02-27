package org.ksga.controller;


import org.ksga.model.entity.Product;
import org.ksga.model.service.ProductService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Display Product List
    public List<Product> displayAllProducts() {
        return productService.displayProductList();
    }

    // Write Product
    public void insertProduct(Product product) {
        productService.writeProducts(product);
    }

    // Read Product by id
    public Product getProductById(int id) {
        return productService.readProductById(id);
    }
    // Update
    public void updateProduct(Product product) {
        productService.updateProduct(product);
    }
    // Delete Product
    public String deleteProduct(Integer id) {
        return productService.deleteProduct(id);
    }

    // Search by product name
    public List<Product> getProductByName(String name) {
        return productService.searchProductsByName(name);
    }
    // Set Number of Display Row
    public void setRow(int rows){
        productService.setDisplayRow(rows);
    }
    // display row
    public int displayRow() {
        return productService.displayRow();
    }
    // Save (Save insert product and update product to database)
    public void saveProduct( String saveProduct) {
        productService.saveProduct(saveProduct);
    }
    // Unsave (View insert product and update unsavedProduct)
    public  void unSaveProduct(Product product, String unsavedProduct) {
        productService.unsavedProduct(product , unsavedProduct);
    }
    // Backup
    public boolean backupProduct(String fileName) {
        return productService.backupProducts(fileName);
    }
    // Restore
    public boolean restoreProduct(String backupFilePath) {
        return productService.restoreProducts();
    }
    // display product
    public void displayProduct(String displayProduct) {
        productService.displayProduct(displayProduct);
    }
}
