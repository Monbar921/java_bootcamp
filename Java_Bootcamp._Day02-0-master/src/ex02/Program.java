package ex02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) {
        Path folder = checkArgs(args);

        if (folder != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                boolean isStop = false;
                do {
                    String line = br.readLine();
                    if (line.equals("ls")) {
                        lsCommand(folder);
                    } else if (line.matches("^cd.+")) {
                        folder = cdCommand(folder, line);
                    } else if (line.matches("^mv.+")) {
                        mvCommand(folder, line);
                    } else if (line.equals("exit")) {
                        isStop = true;
                    } else {
                        System.out.println("Command does not support");
                    }

                } while (!isStop);
            } catch (IOException e) {
                System.err.println("Problem with console");
            }
        } else {
            System.err.println("Give me correct argument");
        }
    }

    private static Path checkArgs(String[] args) {
        Path folder = null;
        if (args.length == 1) {
            if (args[0].matches("^--current-folder=.+")) {
                String folderArgument = args[0].substring(args[0].indexOf('=') + 1);

                folder = Paths.get(folderArgument);
                if (!folder.toFile().isDirectory()) {
                    folder = null;
                }
            }
        }
        return folder;
    }

    private static void lsCommand(Path folder) {
        try (Stream<Path> filesStream = Files.walk(folder, 1)) {
            filesStream.filter(p -> !p.equals(folder)).sorted()
                    .forEach(p -> {
                        try {
                            System.out.printf("%s %.0f KB\n", p.getFileName(), Files.size(p) / 1024.0);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {

        }
    }

    private static Path cdCommand(Path folder, String newFolder) {
        newFolder = newFolder.substring(newFolder.indexOf(' ') + 1);
        Path tempFolder = folder.toAbsolutePath();
        tempFolder = tempFolder.resolve(newFolder).normalize();

        if (!tempFolder.toFile().isDirectory()) {
            tempFolder = null;
            System.err.println("Wrong path");
        }

        if (tempFolder != null) {
            folder = tempFolder;
        }
        return folder;
    }

    private static void mvCommand(Path folder, String moveLine) {
        moveLine = moveLine.substring(moveLine.indexOf(' ') + 1);
        String[] moveArgs = moveLine.split(" ");

        if (moveArgs.length == 2) {
            try (Stream<Path> filesStream = Files.walk(folder, 1)) {
                Optional<Path> tempPath = filesStream.filter(p -> !p.equals(folder))
                        .filter(p -> p.getFileName().toString().equals(moveArgs[0])).findFirst();
                if (tempPath.isPresent()) {
                    Path found = tempPath.get();

                    Path newFile = found.getParent().toAbsolutePath();
                    newFile = newFile.resolve(moveArgs[1]).normalize();

                    if (newFile.toFile().exists()) {
                        System.out.println("Such name already exists");
                    } else {
                        boolean isRenamed = found.toFile().renameTo(new File(newFile.toString()));
                        if (!isRenamed) {
                            System.out.println("Wrong move path");
                        }
                    }
                } else {
                    System.out.println("File does not exist");
                }
            } catch (IOException e) {

            }
        }
    }
}
