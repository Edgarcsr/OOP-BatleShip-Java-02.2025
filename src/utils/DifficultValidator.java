package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DifficultValidator {
    public  static int intSelectOptionsGameValidator(Scanner input){
        try {
            int difficult = input.nextInt();
            if (difficult >= 1 && difficult <= 3){
                return difficult;
            }else{
                System.out.println("Opción no válida, solo puedes elegir entre 1, 2 o 3");
            }
        } catch (InputMismatchException e) {
            System.out.println("Dato introducido no válido, solo puede ser numérico");
        }
        System.out.println("Introduce una dificultad válida (1, 2 o 3):");
        input.nextLine();
        return intSelectOptionsGameValidator(input);
    }
}
