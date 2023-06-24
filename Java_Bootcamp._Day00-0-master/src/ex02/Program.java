package ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int counter = 0;
        while(true){
            int number = in.nextInt();
            if(number == 42){
                break;
            }
            int sumOfDigits = sumDigits(number);
            if(isPrime(sumOfDigits)){
                ++counter;
            }
        }

        System.out.println("Count of coffee-request - " + counter);
        in.close();
    }


    private static int sumDigits(int number){
        int result = 0;

        do{
            result += number % 10;
            number /= 10;
        } while (number != 0);

        return result;
    }
    private static boolean isPrime(int number) {
        if(number < 2) return false;
        if(number == 2) return true;
        if(number % 2 == 0) return false;
        for(int i=3; (i*i)<=number; i+=2){
            if(number % i == 0 ) return false;
        }
        return true;
    }






}
