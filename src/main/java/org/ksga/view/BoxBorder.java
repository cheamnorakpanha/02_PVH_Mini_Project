package org.ksga.view;

public interface BoxBorder {
    static final String TOP_LEFT_CORNER =   "┌";
    static final String TOP_RIGHT_CORNER = "┐";
    static final String BOTTOM_LEFT_CORNER = "└";
    static final String BOTTOM_RIGHT_CORNER = "┘";
    static final String HORIZONTAL_BORDER = "─";
    static final String VERTICAL_BORDER = "│";
    static final String SPACE = " ";

    static final String LEFT_CONNECTOR_BORDER = "╠";
    static final String RIGHT_CONNECTOR_BORDER = "╣";
    static final String TOP_CONNECTOR_BORDER = "╦";
    static final String BOTTOM_CONNECTOR_BORDER = "╩";
    static final String CROSS_BORDER = "╬";
    static final String HORIZONTAL_CONNECTOR_BORDER = "═";
    static final String VERTICAL_CONNECTOR_BORDER = "║";
    static final String TOP_LEFT_CONNECTOR_CORNER =   "╔";
    static final String TOP_RIGHT_CONNECTOR_CORNER = "╗";
    static final String BOTTOM_LEFT_CONNECTOR_CORNER = "╚";
    static final String BOTTOM_RIGHT_CONNECTOR_CORNER = "╝";




    //color code
    public static String red = "\u001B[31m"; // Red ANSI escape code

    public static String green = "\u001B[32m"; // Green ANSI escape code

    public static String yellow = "\u001B[33m"; // Yellow ANSI escape code

    public static String blue = "\u001B[34m"; // Blue ANSI escape code

    public static String magenta = "\u001B[35m"; // Magenta ANSI escape code

    public static String cyan = "\u001B[36m"; // Cyan ANSI escape code

    public static String white = "\u001B[37m"; // White ANSI escape code


    // ANSI escape codes for additional colors
    public static String brightBlack = "\u001B[90m"; // Bright Black (Gray)

    public static String brightRed = "\u001B[91m"; // Bright Red

    public static String brightGreen = "\u001B[92m"; // Bright Green

    public static String brightYellow = "\u001B[93m"; // Bright Yellow

    public static String brightBlue = "\u001B[94m"; // Bright Blue

    public static String brightMagenta = "\u001B[95m"; // Bright Magenta

    public static String brightCyan = "\u001B[96m"; // Bright Cyan

    public static String brightWhite = "\u001B[97m"; // Bright White



    public static String backgroundRed = "\u001B[41m"; // Red

    public static String backgroundGreen = "\u001B[42m"; // Green background

    public static String backgroundYellow = "\u001B[43m"; // Yellow background

    public static String backgroundBlue = "\u001B[44m"; // Blue background

    public static String backgroundMagenta = "\u001B[45m"; // Magenta background

    public static String backgroundCyan = "\u001B[46m"; // Cyan background

    public static String backgroundWhite = "\u001B[47m"; // White background


    public static String brightBackgroundBlack = "\u001B[100m"; // Bright Black (Gray) background

    public static String brightBackgroundRed = "\u001B[101m"; // Bright Red background


    public static String brightBackgroundGreen = "\u001B[102m"; // Bright Green background

    public static String brightBackgroundYellow = "\u001B[103m"; // Bright Yellow background

    public static String brightBackgroundBlue = "\u001B[104m"; // Bright Blue background

    public static String brightBackgroundMagenta = "\u001B[105m"; // Bright Magenta background

    public static String brightBackgroundCyan = "\u001B[106m"; // Bright Cyan background

    public static String brightBackgroundWhite = "\u001B[107m"; // Bright White background


    // Additional colors
    public static String purple = "\u001B[35m"; // Purple

    public static String pink = "\u001B[38;5;206m"; // Pink

    public static String darkRed = "\u001B[38;5;88m"; // Dark Red

    public static String darkGreen = "\u001B[38;5;22m"; // Dark Green


    public static String darkBlue = "\u001B[38;5;19m"; // Dark Blue

    public static String darkMagenta = "\u001B[38;5;90m"; // Dark Magenta

    public static String darkCyan = "\u001B[38;5;23m"; // Dark Cyan

    public static String darkYellow = "\u001B[38;5;130m"; // Dark Yellow


    // Reset ANSI escape code
    public static String reset = "\u001B[0m"; // Reset


}
