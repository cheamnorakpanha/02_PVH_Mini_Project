package org.ksga.exceptions;

import java.util.Scanner;

public class ProductHelper {
    static Scanner scanner = new Scanner(System.in);

    private static final String PRODUCT_NAME_REGEX = "^[A-Za-z][A-Za-z0-9]{1,249}$";
    private static final String UNIT_PRICE_REGEX = "^(?:0|[1-9]\\d*)(?:\\.\\d+)?$";
    private static final String QUANTITY_REGEX = "^\\d+$";

    public static boolean validateProductName(String name) {
        if (name == null) {
            return false;
        }

        name = name.trim();
        return name.matches(PRODUCT_NAME_REGEX);
    }

    public static boolean validateUnitPrice(String unitPrice) {
        if (unitPrice == null) {
            return false;
        }

        unitPrice = unitPrice.trim();

        if (!unitPrice.matches(UNIT_PRICE_REGEX)) {
            return false;
        }

        double price = Double.parseDouble(unitPrice);
        return price > 0;
    }

    public static boolean validateQuantity(String qtyProducts) {
        if (qtyProducts == null) {
            return false;
        }

        qtyProducts = qtyProducts.trim();

        if (!qtyProducts.matches(QUANTITY_REGEX)) {
            return false;
        }

        int qty = Integer.parseInt(qtyProducts);
        return qty >= 0;
    }
}