package main.java.edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.diogonunes.jcdp.color.api.Ansi.BColor;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Parameters(separators = "=")
public class ImagesToCharConverter {
    private BColor whiteColorSign;
    private BColor blackColorSign;
    @Parameter(names = {"--white"})
    private String whiteArg;
    @Parameter(names = {"--black"})
    private String blackArg;


    private boolean checkInput() {
        boolean res = false;

        if (whiteArg == null || blackArg == null) {
            System.out.println("Give me 2 args with colors!");
        } else {
            whiteColorSign = determineColor(whiteArg);
            blackColorSign = determineColor(blackArg);
            if(whiteColorSign == null || blackColorSign == null){
                System.out.println("Give me right colors!");
            } else {
                res = true;
            }
        }
        return res;
    }

    private BColor determineColor(String str) {
        BColor color = null;
        if(str.equals("RED")){
            color = BColor.RED;
        } else if (str.equals("GREEN")) {
            color = BColor.GREEN;
        } else if (str.equals("BLUE")) {
            color = BColor.BLUE;
        } else if (str.equals("WHITE")) {
            color = BColor.WHITE;
        } else if (str.equals("YELLOW")) {
            color = BColor.YELLOW;
        }
        return color;
    }

    public void run() {
        if (checkInput()) {
            try {
                ImageReader imageReader = new ImageReader();
                BufferedImage image = imageReader.getImage();
                ImagePrinter printer = new ImagePrinter(whiteColorSign, blackColorSign, image);
                printer.print();
            } catch (IOException e) {
                System.err.println("Give right file");
            }
        }
    }
}
