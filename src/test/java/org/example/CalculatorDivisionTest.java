package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.testng.Assert;
import org.testng.IExpectedExceptionsHolder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorDivisionTest {
    private Calculator calculator;
    @BeforeClass
    public void setup() {
        calculator = new Calculator();

    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivision() {
        calculator.div(4, 0);
    }

    @Test
    public void testDivisio2() {
        Assert.assertEquals(calculator.div(2,2), 1);
    }


    @AfterClass
    public void tearDown() {
        calculator = null;
    }
}
