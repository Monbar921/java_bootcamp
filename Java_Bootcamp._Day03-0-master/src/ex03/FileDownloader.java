package ex03;

import java.io.*;
import java.net.URL;

public class FileDownloader extends Thread {
    private final String name;

    private final URLStorage fileReader;

    public FileDownloader(String name, URLStorage fileReader) {
        this.name = name;
        this.fileReader = fileReader;
    }

    @Override
    public void run() {

        while (true) {
            URL url = fileReader.getNextUrl();
            if (url == null) {
                break;
            }
            int fileNumber = fileReader.getLastGetURL() + 1;
            System.out.printf("%s start download file number %d\n", name, fileNumber);
            try (InputStream in = new BufferedInputStream(url.openStream()); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                byte[] buf = new byte[1024];
                int n = 0;
                while (-1 != (n = in.read(buf))) {
                    out.write(buf, 0, n);
                }

                byte[] response = out.toByteArray();
                saveToFile(response, url);
            } catch (IOException e) {
                System.err.println("Can not fetch URL - " + url);
            }

            System.out.printf("%s finish download file number %d\n", name, fileNumber);


        }
    }

        private void saveToFile(byte[] response, URL url){
            String userDir = System.getProperty("user.dir") + "/ex03/";
            String stringURLRepresentation = url.toString();
            int lastSlashIndex = stringURLRepresentation.lastIndexOf('/');
            String downloadFileName = stringURLRepresentation.substring(lastSlashIndex + 1);


            try (FileOutputStream fos = new FileOutputStream(userDir + downloadFileName)) {
                fos.write(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
