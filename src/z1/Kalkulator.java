package src.z1;

import java.io.PrintWriter;
import java.util.Scanner;

public class Kalkulator {
    public void oblicz(String inputFile, String outputFile) {
        try {
            Scanner fileScanner = new Scanner(new java.io.File(inputFile));
            PrintWriter fileWriter = new PrintWriter(outputFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.contains("+")) {
                    String[] operands = line.split("\\+");
                    int operand1 = Integer.parseInt(operands[0].trim());
                    int operand2 = Integer.parseInt(operands[1].trim());
                    fileWriter.println(operand1 + operand2);
                } else if (line.contains("-")) {
                    String[] operands = line.split("-");
                    int operand1 = Integer.parseInt(operands[0].trim());
                    int operand2 = Integer.parseInt(operands[1].trim());
                    fileWriter.println(operand1 - operand2);
                } else if (line.contains("*")) {
                    String[] operands = line.split("\\*");
                    int operand1 = Integer.parseInt(operands[0].trim());
                    int operand2 = Integer.parseInt(operands[1].trim());
                    fileWriter.println(operand1 * operand2);
                } else if (line.contains("/")) {
                    String[] operands = line.split("/");
                    int operand1 = Integer.parseInt(operands[0].trim());
                    int operand2 = Integer.parseInt(operands[1].trim());
                    try {
                        fileWriter.println(operand1 / operand2);
                    } catch (java.lang.ArithmeticException e) {
                        fileWriter.println("Division by zero");
                    }
                } else {
                    fileWriter.println("Unknown operation");
                }
            }
            fileScanner.close();
            fileWriter.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found: " + inputFile);
        }
    }
}
