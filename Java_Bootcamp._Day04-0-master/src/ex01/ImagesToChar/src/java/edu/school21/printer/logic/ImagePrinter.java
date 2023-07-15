package ex01.ImagesToChar.src.java.edu.school21.printer.logic;

import java.awt.image.BufferedImage;

public class ImagePrinter {
    private final char whiteColorSign;
    private final char blackColorSign;
    private final int WHITE = -1;
    private final int BLACK = -16777216;
    private final BufferedImage image;

    public ImagePrinter(char whiteColorSign, char blackColorSign, BufferedImage image) {
        this.whiteColorSign = whiteColorSign;
        this.blackColorSign = blackColorSign;
        this.image = image;
    }

    public void print(){
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth() ; j++) {
                if (image.getRGB(j, i) == WHITE) {
                    System.out.print(whiteColorSign);
                } else {
                    System.out.print(blackColorSign);
                }
            }
            System.out.println();
        }
    }
}
