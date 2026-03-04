package org.ksga.controller;

import org.ksga.model.entity.Product;
import org.ksga.model.service.ProductService;
import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> displayAllProducts() {
        return productService.displayProductList();
    }

    public void insertProduct(Product product) {
        productService.writeProducts(product);
    }

    public Product getProductById(int id) {
        return productService.readProductById(id);
    }

    public void updateProduct(Product product) {
        productService.updateProduct(product);
    }

    public String deleteProduct(Integer id) {
        return productService.deleteProduct(id);
    }

    public List<Product> getProductByName(String name) {
        return productService.searchProductsByName(name);
    }

    public void setRow(int rows){
        productService.setDisplayRow(rows);
    }

    public int displayRow() {
        return productService.displayRow();
    }

    public void saveProduct( String saveProduct) {
        productService.saveProduct(saveProduct);
    }

    public  void unSaveProduct(Product product, String unsavedProduct) {
        productService.unsavedProduct(product , unsavedProduct);
    }

    public boolean backupProduct(String fileName) {
        return productService.backupProducts(fileName);
    }

    public boolean restoreProduct(String backupFilePath) {
        return productService.restoreProducts(backupFilePath);
    }

}
