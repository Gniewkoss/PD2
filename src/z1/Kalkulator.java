package z1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Kalkulator {
    public void oblicz(String inputFile, String outputFile) {
        try {
            Scanner fileScanner = new Scanner(new File(inputFile));
            PrintWriter fileWriter = new PrintWriter(outputFile);
            processFile(fileScanner, fileWriter);
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

    private void processFile(Scanner fileScanner, PrintWriter fileWriter) {
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            calculateAndWrite(line, fileWriter);
        }
    }

    private void calculateAndWrite(String line, PrintWriter fileWriter) {
        try {
            if (line.contains("+")) {
                performOperation(line, "\\+", (a, b) -> a + b, fileWriter);
            } else if (line.contains("-")) {
                performOperation(line, "-", (a, b) -> a - b, fileWriter);
            } else if (line.contains("*")) {
                performOperation(line, "\\*", (a, b) -> a * b, fileWriter);
            } else if (line.contains("/")) {
                performDivision(line, fileWriter);
            } else {
                fileWriter.println("Unknown operation");
            }
        } catch (NumberFormatException e) {
            fileWriter.println("Invalid number format");
        }
    }

    private void performOperation(String line, String operator, Operation operation, PrintWriter fileWriter) {
        String[] operands = line.split(operator);
        int operand1 = Integer.parseInt(operands[0].trim());
        int operand2 = Integer.parseInt(operands[1].trim());
        fileWriter.println(operation.calculate(operand1, operand2));
    }

    private void performDivision(String line, PrintWriter fileWriter) {
        String[] operands = line.split("/");
        int operand1 = Integer.parseInt(operands[0].trim());
        int operand2 = Integer.parseInt(operands[1].trim());
        try {
            fileWriter.println(operand1 / operand2);
        } catch (ArithmeticException e) {
            fileWriter.println("Division by zero");
        }
    }

    interface Operation {
        int calculate(int a, int b);
    }
}
