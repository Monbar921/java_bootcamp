package ex02;

public class ThreadSummator extends Thread {
    private final double[] array;
    private final int startIndex;
    private final int stopIndex;
    private final Object lock = new Object();
    private double[] sumArr;
    private final String name;
    public ThreadSummator(String name, double[] array, int startIndex, int stopIndex, double[] sumArr){
        this.array = array;
        this.startIndex = startIndex;
        this.stopIndex = stopIndex;
        this.sumArr = sumArr;
        this.name = name;
    }

    @Override
    public void run() {
        double localSum = 0;
        for(int i=startIndex; i < stopIndex; ++i){
            localSum+=  array[i];
        }

        synchronized (lock){
            sumArr[0] += localSum;
        }
        System.out.printf("%s: from %d to %d sum is %.0f\n", name, startIndex, stopIndex - 1, localSum);
    }
}
