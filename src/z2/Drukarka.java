package z2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Drukarka {
    private List<Integer> numericValues = new ArrayList<>();

    public void start(String inputFile, String outputFile) {
        try {
            File inFile = new File(inputFile);
            Scanner fileScanner = new Scanner(inFile);
            PrintWriter fileWriter = new PrintWriter(outputFile);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.equals("koniec")) {
                    break;
                } else if (line.equals("drukuj")) {
                    printLargestValue(fileWriter);
                } else {
                    processNumericValue(line);
                }
            }

            for (int value : numericValues) {
                fileWriter.println(value);
            }

            fileScanner.close();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            try (PrintWriter fileWriter = new PrintWriter(outputFile)) {
                fileWriter.println("File not found: " + inputFile);
            } catch (FileNotFoundException ex) {
                System.out.println("Error writing to the output file: " + ex.getMessage());
            }
        }
    }

    private void processNumericValue(String line) {
        try {
            int value = Integer.parseInt(line.trim());
            numericValues.add(value);
        } catch (NumberFormatException e) {
        }
    }

    private void printLargestValue(PrintWriter fileWriter) {
        if (!numericValues.isEmpty()) {
            int max = numericValues.stream().max(Integer::compare).get();
            fileWriter.println(max);
            numericValues.remove(Integer.valueOf(max));
        } else {
            fileWriter.println("none");
        }
    }
}
