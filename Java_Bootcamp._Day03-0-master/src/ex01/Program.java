package ex01;

import java.util.concurrent.atomic.AtomicBoolean;

public class Program {
    public static void main(String[] args) {
        if(args.length == 1){
            if(args[0].matches("^--count=.+")){
                try {
                    int count = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
                    Object lock = new Object();
                    AtomicBoolean state = new AtomicBoolean(false);
                    Thread eggThread = new Thread(() -> {
                        synchronized (lock){
                            for(int i = 0; i < count; ++i){
                                if(state.get()){
                                    try {
                                        lock.wait();
                                    } catch (InterruptedException e) {
                                        System.exit(-1);
                                    }
                                }
                                System.out.println("Egg");
                                state.set(true);
                                lock.notify();
                            }
                        }
                    });
                    Thread henThread = new Thread(() -> {
                        synchronized (lock) {
                            for(int i = 0; i < count; ++i){
                                if(!state.get()){
                                    try {
                                        lock.wait();
                                    } catch (InterruptedException e) {
                                        System.exit(-1);
                                    }
                                }
                                System.out.println("Hen");
                                state.set(false);
                                lock.notify();
                            }
                        }
                    });
                    eggThread.start();
                    henThread.start();


                }catch (NumberFormatException e){
                    System.out.println("Wrong format of count");
                }
            } else {
                System.out.println("Give me right argument");
            }
        } else {
            System.out.println("Give me only 1 argument");
        }
    }
}
