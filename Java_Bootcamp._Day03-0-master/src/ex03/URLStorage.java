package ex03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class URLStorage {
    private final String fileName;
    private List<URL> urls;
    private static int lastGetURL = -1;

    public URLStorage(String fileName) {
        this.fileName = fileName;
        getURLs();
    }

    private void getURLs() {
        List<URL> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            while (reader.ready()) {
                String[] numberAndURL = reader.readLine().split(" ");
                if (numberAndURL.length == 2) {
                    if (numberAndURL[0].matches("^\\d$") && numberAndURL[1].matches("^http.+")) {
                        result.add(new URL(numberAndURL[1]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        urls = result;
    }

    public synchronized URL getNextUrl(){
        return lastGetURL < (urls.size() - 1) ? urls.get(++lastGetURL) : null;
    }

    public int getLastGetURL(){
        return lastGetURL;
    }

}
