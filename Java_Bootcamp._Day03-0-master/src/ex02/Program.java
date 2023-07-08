package ex02;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    private static int arraySize;
    private static int threadsCount;
    private static List<ThreadSummator> threads = new ArrayList<>();
    private static double[] array;
    private static final double[] sumArr = new double[1];

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 2) {
            if (args[0].matches("^--arraySize=.+") && args[1].matches("^--threadsCount=.+")) {
                try {
                    arraySize = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
                    threadsCount = Integer.parseInt(args[1].substring(args[1].indexOf('=') + 1));

                    initArray();
                    countPlainSum();
                    createThreads();
                    for(ThreadSummator threadSummator : threads){
                        threadSummator.start();
                    }

                    for(ThreadSummator threadSummator : threads){
                        threadSummator.join();
                    }


                    System.out.printf("Sum by threads: %.0f\n", sumArr[0]);

                } catch (NumberFormatException e) {
                    System.out.println("Wrong format of params");
                }
            } else {
                System.out.println("Give me right argument");
            }
        } else {
            System.out.println("Give me only 2 argument");
        }
    }


    private static void initArray() {
        array = new double[arraySize];
        Arrays.fill(array, 1);
    }

    private static void countPlainSum(){
        System.out.println("Sum: " + Arrays.stream(array).count());
    }

    private static void createThreads() {
        int sizeOfOneThread = arraySize / threadsCount;
        int threadIndex = 1;
        for(int i = 0; i < arraySize; i += sizeOfOneThread ){
            int finishIndex = i + sizeOfOneThread;
            if(threadIndex == threadsCount){
                finishIndex = arraySize;
            }
            threads.add(new ThreadSummator("Thread " + threadIndex++, array, i, finishIndex, sumArr));
            if(threadIndex == threadsCount + 1){
                i = arraySize;
            }
        }
    }
}
