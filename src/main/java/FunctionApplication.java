import java.util.InputMismatchException;
import java.util.Scanner;

public class FunctionApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean repeat = true;
        while (repeat) {
            try {
                System.out.println("Enter value for 'a':");
                double a = getValidInput(scanner);

                System.out.println("Enter value for 'b':");
                double b = getValidInput(scanner);

                System.out.println("Enter value for 'c':");
                double c = getValidInput(scanner);

                System.out.println("Enter value for 'd':");
                double d = getValidInput(scanner);

                FunctionCalculator calculator = new FunctionCalculator();
                double result = calculator.calculateA(a, b, c, d);
                System.out.println("Result of (a+b) / (c+d): " + result);
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }

            repeat = askForRepeat(scanner);
        }

        scanner.close();
        System.out.println("Program ended.");
    }

    public static double getValidInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid numeric input.");
                scanner.next(); // Consume invalid input to avoid infinite loop
            }
        }
    }

    private static boolean askForRepeat(Scanner scanner) {
        while (true) {
            System.out.println("Do you want to calculate again? (yes/no):");
            String response = scanner.next().toLowerCase();
            if (response.equals("yes")) {
                return true;
            } else if (response.equals("no")) {
                return false;
            } else {
                System.out.println("Error: Please enter either 'yes' or 'no'.");
            }
        }
    }

}
