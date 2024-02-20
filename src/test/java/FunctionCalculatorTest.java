import org.testng.Assert;
import org.testng.annotations.Test;

public class FunctionCalculatorTest {
    FunctionCalculator calculator = new FunctionCalculator();

    @Test
    public void testValidInput() {
        double result = calculator.calculateA(1, 2, 3, 4);
        Assert.assertEquals(result, 3.0/7.0);
    }

    @Test
    public void testPositiveAndNegativeValues() {
        double result = calculator.calculateA(-5, 3, 2, -4);
        Assert.assertEquals(result, 1.0);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivisionByZero() {
        calculator.calculateA(1, 2, 0, 0);
    }

    @Test
    public void testZeroDivision() {
        double result = calculator.calculateA(0, 0, 1, 2);
        Assert.assertEquals(result, 0.0);
    }

    @Test
    public void testMaximumValue() {
        FunctionCalculator calculator = new FunctionCalculator();
        double result = calculator.calculateA(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        Assert.assertTrue(Double.isNaN(result));
    }

    @Test
    public void testMinimumValue() {
        FunctionCalculator calculator = new FunctionCalculator();
        double result = calculator.calculateA(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
        Assert.assertEquals(result, 1.0);
    }

}
