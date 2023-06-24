package ex04;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int maxCharInOccurrences = 999;
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        in.close();
        exitProgram(input, maxCharInOccurrences);
        int[] frequency = countFrequency(input);
        makeDiagram(frequency, input);
    }

    private static int[] countFrequency(String input) {
        int[] frequency = new int[65535 + 1];
        char[] chars = input.toCharArray();

        for (char c : chars) {
            ++frequency[c];
        }

        return frequency;
    }

    private static void exitProgram(String input, int maxCharInOccurrences) {
        if (input.length() > maxCharInOccurrences) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
    }

    private static void makeDiagram(int[] frequency, String input) {
        int lineCounter = 0;
        int currentIndex = 0;
        int maxCount = 0;
        char[] letters = new char[input.length()];
        int lettersArrayCounter = 0;
        for (int i = 0; i < input.length(); ++i) {
            int max = 0;
            int max_index = 0;
            for (int j = 0; j < frequency.length; ++j) {
                if (frequency[j] > max) {
                    max = frequency[j];
                    max_index = j;
                }
            }
            if (max != 0) {
                if (currentIndex == 10) {
                    break;
                }
                letters[lettersArrayCounter++] = (char) max_index;
                if (maxCount == 0) {
                    maxCount = frequency[max_index];
                }
                lineCounter = print(max, lineCounter, maxCount, currentIndex);
                frequency[max_index] = -1;
                ++currentIndex;
            }
        }
        fixPrint(lineCounter, currentIndex, letters);
    }

    private static int print(int counter, int lineCounter, int maxCount, int currentIndex) {
        if (lineCounter <= 11) {
            if (lineCounter == 0) {
                ++lineCounter;
                System.out.printf("%3s", counter);
            } else {
                int maxHeight = (maxCount <= 10) ? maxCount : 10;
                int heightDiff = maxHeight - getRelevantHeight(counter, maxCount);
                if (heightDiff > 0) {
                    if (heightDiff == 1) {
                        System.out.println();
                        ++lineCounter;
                        for (int i = 0; i < currentIndex; ++i) {
                            System.out.printf("%3s", "#");
                        }
                        System.out.printf("%3s", counter);
                    } else {
                        int limit = lineCounter;
                        for (int i = 0; i <= heightDiff - limit && lineCounter != 11; ++i) {
                            System.out.println();
                            ++lineCounter;
                            for (int j = 0; j < currentIndex; ++j) {
                                System.out.printf("%3s", "#");
                            }
                        }
                        System.out.printf("%3s", counter);
                    }
                } else {
                    System.out.printf("%3s", counter);
                }
            }
        }
        return lineCounter;
    }


    private static int getRelevantHeight(int counter, int maxCount) {
        return counter * 10 / maxCount;
    }

    private static void fixPrint(int lineCounter, int currentIndex, char[] letters){
        if(lineCounter != 11){
            for (int j = 0; j < currentIndex; ++j) {
                System.out.printf("%3s", "#");
            }
        }
        System.out.println();
        for(char l : letters){
            System.out.printf("%3s", l);
        }
        System.out.println();
    }

}
