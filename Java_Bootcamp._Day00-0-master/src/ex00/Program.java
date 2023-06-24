package ex00;

public class Program {
    public static void main(String[] args) {
        int number = 479598;
        System.out.println(sumDigits(number));
    }

    private static int sumDigits(int number){
        int result = 0;

        do{
            result += number % 10;
            number /= 10;
        } while (number != 0);

        return result;
    }
}
