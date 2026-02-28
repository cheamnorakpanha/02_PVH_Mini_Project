package org.ksga.exceptions;


import java.util.Scanner;
import java.util.regex.Pattern;

public class ProductHelper {
    static Scanner scanner = new Scanner(System.in);

    // regex pattern
    private static final String NAME_REGEX = "^(?!\\s+$).{1,250}$";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private static final String QTY_REGEX = "^[0-9]+$";

    public static void validateProductName(String name) throws IllegalArgumentException {
        if (name == null || !NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("Product name must be between 1 and 250 characters and cannot be empty or just spaces.");
        }
    }
    public static void validateUnitPrice(double unitPrice) {
        if (unitPrice <= 0) {
            throw new IllegalArgumentException("Unit price must be greater than 0.");
        }
    }
    public static void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
    }
}