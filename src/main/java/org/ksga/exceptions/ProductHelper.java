package org.ksga.exceptions;


import java.util.Scanner;
import java.util.regex.Pattern;

public class ProductHelper {
    static Scanner scanner = new Scanner(System.in);

    public static boolean validateProductName(String name) {
        if (name == null) {
            return false;
        }

        name = name.trim();

        if (name.length() < 4 || name.length() > 250) {
            return false;
        }

        if (Character.isDigit(name.charAt(0))) {
            return false;
        }

        if (name.contains(" ")) {
            return false;
        }

        return true;
    }

    public static boolean validateUnitPrice(String unitPrice) {
        if (unitPrice == null) {
            return false;
        }

        try {
            double price = Double.parseDouble(unitPrice);
            return price > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateQuantity(String qtyProducts) {
        if (qtyProducts == null){
            return false;
        }

        try {
            int qty = Integer.parseInt(qtyProducts);
            return qty >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}