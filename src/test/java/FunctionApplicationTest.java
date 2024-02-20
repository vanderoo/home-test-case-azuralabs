import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class FunctionApplicationTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeMethod
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString().replaceAll("\\r\\n", "\n");
    }

    @AfterMethod
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testFunctionCalculation() {
        String input = "5\n3\n1\n1\nno\n";
        provideInput(input);

        FunctionApplication.main(new String[0]);

        String expectedOutput = "Enter value for 'a':\n" +
                "Enter value for 'b':\n" +
                "Enter value for 'c':\n" +
                "Enter value for 'd':\n" +
                "Result of (a+b) / (c+d): 4.0\n" +
                "Do you want to calculate again? (yes/no):\n" +
                "Program ended.\n";
        Assert.assertEquals(getOutput(), expectedOutput);
    }

    @Test
    public void testInvalidInput() {
        String input = "invalid\n5\n3\n1\n1\nno\n";
        provideInput(input);

        FunctionApplication.main(new String[0]);

        String expectedOutput = "Enter value for 'a':\n" +
                "Error: Please enter a valid numeric input.\n" +
                "Enter value for 'b':\n" +
                "Enter value for 'c':\n" +
                "Enter value for 'd':\n" +
                "Result of (a+b) / (c+d): 4.0\n" +
                "Do you want to calculate again? (yes/no):\n" +
                "Program ended.\n";
        Assert.assertEquals(getOutput(), expectedOutput);
    }

    @Test
    public void testRepeatCalculation() {
        // Input values for a, b, c, d, then 'yes' for repeat
        String input = "5\n3\n1\n1\nyes\n5\n1\n2\n1\nno\n";
        provideInput(input);

        // Perform test
        FunctionApplication.main(new String[0]);

        // Validate output
        String expectedOutput = "Enter value for 'a':\n" +
                "Enter value for 'b':\n" +
                "Enter value for 'c':\n" +
                "Enter value for 'd':\n" +
                "Result of (a+b) / (c+d): 4.0\n" +
                "Do you want to calculate again? (yes/no):\n" +
                "Enter value for 'a':\n" +
                "Enter value for 'b':\n" +
                "Enter value for 'c':\n" +
                "Enter value for 'd':\n" +
                "Result of (a+b) / (c+d): 2.0\n" +
                "Do you want to calculate again? (yes/no):\n" +
                "Program ended.\n";
        Assert.assertEquals(getOutput(), expectedOutput);
    }

    @Test
    public void testInvalidRepeatInput() {
        String input = "5\n3\n1\n1\ninvalid\nyes\n5\n1\n2\n1\nno\n";
        provideInput(input);

        FunctionApplication.main(new String[0]);

        String expectedOutput = "Enter value for 'a':\n" +
                "Enter value for 'b':\n" +
                "Enter value for 'c':\n" +
                "Enter value for 'd':\n" +
                "Result of (a+b) / (c+d): 4.0\n" +
                "Do you want to calculate again? (yes/no):\n" +
                "Error: Please enter either 'yes' or 'no'.\n" +
                "Do you want to calculate again? (yes/no):\n" +
                "Enter value for 'a':\n" +
                "Enter value for 'b':\n" +
                "Enter value for 'c':\n" +
                "Enter value for 'd':\n" +
                "Result of (a+b) / (c+d): 2.0\n" +
                "Do you want to calculate again? (yes/no):\n" +
                "Program ended.\n";
        Assert.assertEquals(getOutput(), expectedOutput);
    }
}
