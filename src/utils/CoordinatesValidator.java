package utils;

import java.util.Scanner;

public class CoordinatesValidator {

    public static int checkInt(Scanner scanner, String message) {
        System.out.printf("%s", message);

        int row;
        String inputTemporary = scanner.nextLine().trim();

        if (inputTemporary.isEmpty()) {
            System.out.println("Não pode deixar a cordenada vazia. Por favor, insira um valor inteiro válido para a fila.");
            System.out.println();
            return checkInt(scanner, "Insira a fila (0-9): ");
        } else {
            try {
                row = Integer.parseInt(inputTemporary);
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Error: Deve inserir um número inteiro válido para a fila ");
                return checkInt(scanner, "Insira a fila (0-9): ");
            }
        }
        return row >= 0 && row <= 9 ? row : checkInt(scanner, "Não seja pirata, insira um número entre 0 e 9: ");
    }

    public static int checkLetter(Scanner scanner, String message) {
        System.out.printf("%s", message);

        String temporaryInput = scanner.nextLine().trim();
        String column;

        if (temporaryInput.isEmpty()) {
            System.out.println("Error: Nao pode deixar a coordenada vazia");
            System.out.println();
            return checkLetter(scanner, "Insira a coluna (A-J): ");
        } else if (temporaryInput.matches("\\d+|[!|·#$~%&/()=?¿'{¨}.,:;_]")) {
            System.out.println("Error: Numeros ou carácteres especiais não são válidos.");
            System.out.println();
            return checkLetter(scanner, "Insira a coluna (A-J): ");
        }

        column = temporaryInput.toUpperCase();

        return (column.length() == 1 && column.charAt(0) >= 'A' && column.charAt(0) <= 'J') ? column.charAt(0) - 'A' : checkLetter(scanner, "Nao seja pirata, insira uma letra so entre A e J: ");
    }
}

