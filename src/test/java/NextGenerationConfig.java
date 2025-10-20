import org.example.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NextGenerationConfig {
    Calculator calculator = new Calculator();

    @Test
    public void nextGeneration() {
        Assert.assertEquals(calculator.add(2, 3), 5);
    }
}
