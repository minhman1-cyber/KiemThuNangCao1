package org.example;

import org.testng.Assert;
import org.testng.annotations.*;

public class CalculatorGroupTest {
    private Calculator calculator;
    @BeforeClass(alwaysRun = true)
    public void setup() {
        calculator = new Calculator();

    }

    @Test(groups = "math")
    public void testAdd() {
        Assert.assertEquals(calculator.add(4, 2), 6);
    }

    @Test(groups = "math")
    public void testSubtract() {
        Assert.assertEquals(calculator.sub(4, 2), 2);
    }

    @Test(groups = "math")
    public void testMultiply() {
        Assert.assertEquals(calculator.mul(2,2), 4);
    }


    @Test(groups = "division")
    public void testDivisio2() {
        Assert.assertEquals(calculator.div(2,2), 1);
    }

    @Test(groups = "division", expectedExceptions = ArithmeticException.class)
    public void testDivision() {
        calculator.div(4, 0);
    }


    @AfterMethod
    public void tearDown() {
        calculator = null;
    }
}
