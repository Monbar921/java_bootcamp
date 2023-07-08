package ex03;

import java.util.ArrayList;
import java.util.List;


public class Program {
    private static final List<FileDownloader> threads = new ArrayList<>();

    public static void main(String[] args) {
        String fileName = System.getProperty("user.dir") + "/ex03/files_urls.txt";
        URLStorage fileReader = new URLStorage(fileName);


        if (args.length == 1) {
            if (args[0].matches("^--threadsCount=.+")) {
                try {
                    int threadsCount = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));

                    for (int i = 0; i < threadsCount; ++i) {
                        FileDownloader fileDownloader = new FileDownloader("Thread-"+(++i), fileReader);
                        fileDownloader.start();
                        threads.add(fileDownloader);
                    }
                    try{
                        for (FileDownloader fileDownloader : threads) {
                            fileDownloader.join();
                        }
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Wrong format of params");
                }
            } else {
                System.out.println("Give me right argument");
            }
        } else {
            System.out.println("Give me only 1 argument");
        }

    }
}
