package ex00;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        String SIGNATURES_FILENAME = System.getProperty("user.dir") + "/ex00/signatures.txt";
        String RESULTS_FILENAME = System.getProperty("user.dir") + "/ex00/results.txt";
        List<String> definedSignatures = readDefinedSignatures(SIGNATURES_FILENAME);
        if (definedSignatures.size() != 0) {
            try(Scanner scanner = new Scanner(System.in)){
                while(scanner.hasNext()){
                    String fileName = scanner.nextLine();
                    if(fileName.equals("42")){
                        break;
                    }
                    try {
                        List<Byte> signatureBytes = readSignature(fileName);

                        byte[] signatureArray = listToArray(signatureBytes);
                        String signature = bytesToHex(signatureArray);
                        System.out.println(signature);
                        String signatureName = tryRecognizeSignature(definedSignatures, signature);
                        appendFileFormatToResults(RESULTS_FILENAME, signatureName);
                        System.out.println(signatureName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static List<String> readDefinedSignatures(String fileName) {
        List<String> definedSignatures = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(fileName); Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNext()) {
                definedSignatures.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return definedSignatures;
    }

    private static List<Byte> readSignature(String fileName) throws IOException {
        List<Byte> signatureBytes = new ArrayList<>();
        int BYTES_FOR_READ = 24;
        try (InputStream inputStream = new FileInputStream(fileName)) {
            int nextByte;
            int counter = 0;
            while ((nextByte = inputStream.read()) != -1 && counter < BYTES_FOR_READ) {
                signatureBytes.add((byte) nextByte);
                ++counter;
            }
        }
        return signatureBytes;
    }

    private static byte[] listToArray(List<Byte> signatureBytes) {
        byte[] signatureArray = new byte[signatureBytes.size()];
        for (int i = 0; i < signatureBytes.size(); ++i) {
            signatureArray[i] = signatureBytes.get(i);
        }
        return signatureArray;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x ", b).toUpperCase());
        }
        return sb.toString();
    }

    private static String tryRecognizeSignature(List<String> definedSignatures, String signature){
        String result = "UNDEFINED";

        for(String definedSignature : definedSignatures){
            String[] signatureAndItsName = definedSignature.split(", ");
            char[] definedSignatureChars  = signatureAndItsName[1].toCharArray();
            char[] signatureChars  = signature.toCharArray();
            for(int i = 0; i < definedSignatureChars.length; ++i){
                if(definedSignatureChars[i] != signatureChars[i]){
                    break;
                }
                if (i == definedSignatureChars.length - 1) {
                    result = signatureAndItsName[0];
                    break;
                }
            }
            if(!result.equals("UNDEFINED")){
                break;
            }
        }
        return result;
    }

    private static void appendFileFormatToResults(String filename, String formatName){
        try(OutputStream out = new FileOutputStream(filename, true)){
            out.write(formatName.getBytes());
            out.write("\n".getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
