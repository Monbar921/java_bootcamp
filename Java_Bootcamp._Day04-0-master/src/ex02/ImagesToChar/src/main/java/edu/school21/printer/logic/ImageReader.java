package main.java.edu.school21.printer.logic;

import main.java.edu.school21.printer.app.Program;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageReader {
    private static final String fileName = "/resources/it.bmp";
    public BufferedImage getImage() throws IOException {
        BufferedImage image;
        try(InputStream inputStream = Program.class.getResourceAsStream(fileName)) {

            if (inputStream == null) {
                throw new IOException("property file not found in the classpath");
            }

            image = ImageIO.read(inputStream);
        }
        return image;
    }
}
