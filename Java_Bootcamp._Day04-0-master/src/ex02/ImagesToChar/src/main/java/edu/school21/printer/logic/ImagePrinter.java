package main.java.edu.school21.printer.logic;

import java.awt.image.BufferedImage;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.Attribute;


public class ImagePrinter {
    private final BColor whiteColorSign;
    private final BColor blackColorSign;
    private final int WHITE = -1;
    private final int BLACK = -16777216;
    private final BufferedImage image;
    private final ColoredPrinter printer;

    public ImagePrinter(BColor whiteColorSign, BColor blackColorSign, BufferedImage image) {
        this.whiteColorSign = whiteColorSign;
        this.blackColorSign = blackColorSign;
        this.image = image;
        this.printer = new ColoredPrinter.Builder(1, false).build();

    }

    public void print() {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                printer.print(" ", Attribute.NONE, Ansi.FColor.NONE, image.getRGB(j, i) == WHITE ? whiteColorSign : blackColorSign);
            }
                System.out.println();
            }
        }
    }
