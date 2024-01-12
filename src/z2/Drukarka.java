package src.z2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Drukarka {
    public void start(String inputFile, String outputFile) {
        try {
            File inFile = new File(inputFile);
            Scanner fileScanner = new Scanner(inFile);
            PrintWriter fileWriter = new PrintWriter(outputFile);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.equals("drukuj")) {
                    printResults(fileWriter);
                } else {
                    processNumericValue(line);
                }
            }

            fileScanner.close();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputFile);
        }
    }

    private void processNumericValue(String line) {
        try {
            int value = Integer.parseInt(line.trim());
            System.out.println(value);
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric value: " + line);
        }
    }

    private void printResults(PrintWriter writer) {
        System.out.println("Printing results...");
    }
}
