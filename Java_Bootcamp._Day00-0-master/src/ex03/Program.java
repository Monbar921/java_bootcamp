package ex03;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int weekCounter = 0;
        boolean isStrWithWeek = true;
        long storingOfGrades = 0;
        while (in.hasNext()) {
            if (isStrWithWeek) {
                String inputLine = in.nextLine();
                if (inputLine.equals("42")) {

                    break;
                }
                ++weekCounter;
                isStrWithWeek = false;
                checkInputWeek(inputLine, weekCounter);
            } else {
                isStrWithWeek = true;

                int minOfWeek = checkGradesAndFindMin(in);


                storingOfGrades = makeWeekMinimumGrade(minOfWeek, storingOfGrades);
            }

        }
        in.close();
        makeResultOfGrades(weekCounter, storingOfGrades);
    }


    private static void checkInputWeek(String input, int weekCounter) {
        if (weekCounter > 18 || !input.matches("Week \\d+$") || Integer.parseInt(input.substring(5)) < weekCounter) {
            exitProgram();
        }
    }

    private static int checkGradesAndFindMin(Scanner scanner) {
        int min = 10;

        for (int i = 0; i < 5; ++i) {
            int current = scanner.nextInt();
            if (current < 1 || current > 9) {
                exitProgram();
            }
            if (current < min) {
                min = current;
            }
        }
        scanner.nextLine();
        return min;
    }

    private static void exitProgram() {
        System.err.println("Illegal Argument");
        System.exit(-1);
    }

    private static long makeWeekMinimumGrade(int minOfWeek, long storingOfGrades) {
        return storingOfGrades * 10 + minOfWeek;
    }

    private static void makeResultOfGrades(int weeksCounter, long storingOfGrades) {
        for (int i = 0; i < weeksCounter; ++i) {
            System.out.printf("Week %d ", i + 1);
            int minGradeOfWeek = (int) (storingOfGrades / Math.pow(10, weeksCounter - i - 1));
            for (int j = 0; j < minGradeOfWeek; ++j) {
                System.out.print("=");
            }
            System.out.println(">");
            storingOfGrades -= (Math.pow(10, weeksCounter - i - 1) * minGradeOfWeek);
        }
    }
}
