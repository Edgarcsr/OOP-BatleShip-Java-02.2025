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
                System.out.println("Opção inválida, só pode escolher opções 1, 2 ou 3");
            }
        } catch (InputMismatchException e) {
            System.out.println("Dado inserido inválida, ele deve ser um número");
        }
        System.out.println("Insira uma dificuldade válida (1, 2 o 3):");
        input.nextLine();
        return intSelectOptionsGameValidator(input);
    }
}
