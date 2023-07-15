package ex00.ImagesToChar.src.java.edu.school21.printer.logic;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImagesToCharConverter {
    private String fileName;
    private char whiteColorSign;
    private char blackColorSign;
    private final String[] args;

    public ImagesToCharConverter(String[] args) {
        this.args = args;
        if (args == null || args.length != 3) {
            System.out.println("Give me only 3 command arguments!");
        } else {
            if(checkInput()){
                run();
            }
        }
    }

    private boolean checkInput() {
        boolean res = false;

        if (!args[0].startsWith("--white=")) {
            System.out.println("1st arg can not be empty!");
        } else if (!args[1].startsWith("--black=")) {
            System.out.println("2nd arg can not be empty!");
        } else if (!args[2].startsWith("--file=")) {
            System.out.println("3nd arg can not be empty!");
        } else {

            String whiteArg = getSubStrAfterDelim(args[0], "=");
            String blackArg = getSubStrAfterDelim(args[1], "=");
            fileName = getSubStrAfterDelim(args[2], "=");

            if (whiteArg == null || blackArg == null || fileName == null) {
                System.out.println("Give me correct args!");
            } else if (whiteArg.length() != 1 || blackArg.length() != 1) {
                System.out.println("1st and 2nd argument must be a char");
            } else {
                whiteColorSign = whiteArg.charAt(0);
                blackColorSign = blackArg.charAt(0);
                res = true;
            }
        }
        return res;
    }

    private String getSubStrAfterDelim(String str, String delim) {
        String[] splitStr = str.split(delim);
        return splitStr.length == 2 ? splitStr[1] : "";
    }

    public void run() {
        try {
            ImageReader imageReader = new ImageReader(fileName);
            BufferedImage image = imageReader.getImage();
            ImagePrinter printer = new ImagePrinter(whiteColorSign, blackColorSign, image);
            printer.print();
        } catch (IOException e) {
            System.err.println("Get right filename");
        }
    }
}
