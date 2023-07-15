package main.java.edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageReader {
    private final String fileName;

    public ImageReader(String fileName) {
        this.fileName = fileName;
    }

    public BufferedImage getImage() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);

        BufferedImage image = ImageIO.read(fileInputStream);
        fileInputStream.close();
        return image;
    }
}
