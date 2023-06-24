package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        checkInput(number);
        isPrime(number);
        in.close();

    }

    private static void checkInput(int number) {

        if (number <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }

    }


    private static void isPrime(int number) {
        int operationCounter;
        boolean result = true;
        if (number == 2) {
            operationCounter = 1;
        } else {
            if (number % 2 == 0) {
                operationCounter = 1;
                result = false;
            } else {
                operationCounter = 2;
                for (int i = 3; (i * i) <= number; i += 2) {
                    ++operationCounter;
                    if (number % i == 0) {
                        result = false;
                    }
                }
            }
        }

        System.out.println(result + " " + operationCounter);
    }


}
