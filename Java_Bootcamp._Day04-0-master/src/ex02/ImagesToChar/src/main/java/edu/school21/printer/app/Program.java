package main.java.edu.school21.printer.app;
import main.java.edu.school21.printer.logic.ImagesToCharConverter;
import com.beust.jcommander.JCommander;

public class Program {
    public static void main(String[] args) {
        ImagesToCharConverter converter = new ImagesToCharConverter();

        JCommander.newBuilder()
                .addObject(converter)
                .build()
                .parse(args);

        converter.run();
    }
}

