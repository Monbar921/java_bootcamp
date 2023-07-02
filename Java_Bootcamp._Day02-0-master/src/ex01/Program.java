package ex01;

import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        if (args.length == 2) {

            try {
                List<String> firstFile = readWordsForm_file(args[0]);
                List<String> secondFile = readWordsForm_file(args[1]);

                Set<String> dictionary = new TreeSet<String>(firstFile);
                dictionary.addAll(secondFile);

                int[] firstVector = fillCreatedVector(dictionary, firstFile);
                int[] secondVector = fillCreatedVector(dictionary, secondFile);

                double similarity = calculateSimilarity(firstVector, secondVector);
                System.out.printf("Similarity = %.2f\n", similarity);
                writeDictionaryToFile(dictionary);
            } catch (IOException e) {
                System.err.println("Wrong files!");
            }
        } else {
            System.err.println("Give me command line arguments!");
        }
    }

    private static List<String> readWordsForm_file(String fileName) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            while (in.ready()) {
                String[] wordsArray = in.readLine().split(" ");
                words.addAll(Arrays.asList(wordsArray));
            }
            return words;
        }
    }

    private static int[] fillCreatedVector(Set<String> dictionary, List<String> file) {
        int[] vector = new int[dictionary.size()];
        int iterator = 0;
        for (String str : dictionary) {
            int counter = 0;
            for (String word : file) {
                if (word.equals(str)) {
                    ++counter;
                }
            }
            vector[iterator++] = counter;
        }
        return vector;
    }

    private static double calculateSimilarity(int[] firstVector, int[] secondVector) {
        double numerator = 0;
        for (int i = 0; i < firstVector.length; ++i) {
            numerator += firstVector[i] * secondVector[i];
        }

        double denominator = calculateSqrtOfSumVector(firstVector) * calculateSqrtOfSumVector(secondVector);
        return numerator / denominator;
    }

    private static double calculateSqrtOfSumVector(int[] vector) {
        double result = 0;
        for (int elem : vector) {
            result += elem * elem;
        }
        return Math.sqrt(result);
    }

    private static void writeDictionaryToFile(Set<String> dictionary){

        String resultFileName = System.getProperty("user.dir") + "/ex01/dictionary.txt";

        try(Writer writer = new BufferedWriter(new FileWriter(resultFileName))){
            boolean isBeginnig = true;
            for (String word : dictionary){
                if(!isBeginnig){
                    writer.write(", ");
                }
                writer.write(word);
                isBeginnig = false;
            }
        } catch (IOException e){

        }

    }

}
