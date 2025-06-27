package utils;

import java.util.Scanner;

public class CoordinatesValidator {

    public static int checkRow(Scanner scanner) {

        if(scanner.nextLine().trim().isEmpty()) {
            System.out.println("Error: No puedes dejar la coordenada vacia.");
            return checkRow(scanner);
        }

        int row = Integer.parseInt(scanner.nextLine().trim());

        if(row >= 0 && row <= 9) {
            return row;
        }
        System.out.println("Error: Debes introducir un número entero válido para la fila.");
        return checkRow(scanner);
    }

    public static int checkColumn(Scanner scanner) {

        if(scanner.nextLine().trim().isEmpty()) {
            System.out.println("Error: No puedes dejar la coordenada vacia.");
            return checkColumn(scanner);
        }

        String column = scanner.nextLine().trim().toUpperCase();

        if(column.length() == 1 && column.charAt(0) >= 'A' && column.charAt(0) <= 'J') {
            return column.charAt(0) - 'A';
        }
        System.out.println("Error: Debes introducir una letra válida para la columna (A-J).");
        return checkColumn(scanner);
    }
}

