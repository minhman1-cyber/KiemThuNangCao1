package org.example;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalculatorMathTest {
    private Calculator calculator;

    @BeforeClass
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(calculator.add(4, 2), 6);
    }

    @Test
    public void testSubtract() {
        Assert.assertEquals(calculator.sub(4, 2), 2);
    }

    @Test
    public void testMultiply() {
        Assert.assertEquals(calculator.mul(2,2), 4);
    }

    @AfterClass
    public void tearDown() {
        calculator = null;
    }
}
