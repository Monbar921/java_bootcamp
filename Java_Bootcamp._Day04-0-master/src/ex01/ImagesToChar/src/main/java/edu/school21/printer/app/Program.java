package main.java.edu.school21.printer.app;

import main.java.edu.school21.printer.logic.ImagesToCharConverter;


public class Program {
    public static void main(String[] args) {
        ImagesToCharConverter converter = new ImagesToCharConverter(args);
        converter.run();
    }
}

