public class FunctionCalculator {

    public double calculateA(double a, double b, double c, double d) {
        if (c + d == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return (a + b) / (c + d);
    }

}
