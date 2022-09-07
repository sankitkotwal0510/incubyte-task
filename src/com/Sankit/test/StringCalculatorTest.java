package com.ajinz.test;
import com.ajinz.main.StringCalculator;
import com.ajinz.main.HandleNegativeValues;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Test
public class StringCalculatorTest {
    private StringCalculator sc;

    @org.testng.annotations.BeforeMethod
    public void setUp() {
        System.out.println("Starting Test...");
        sc = new StringCalculator();
    }

    public void emptyString(){
        assertEquals(sc.Add(""), 0);
    }
    public void emptyStringWithSpaces(){
        assertEquals(sc.Add(" "), 0);
    }
    public void oneNumber(){
        assertEquals(sc.Add("10"), 10);
    }
    public void multipleNumbers(){
        assertEquals(sc.Add("10, 50, 90, 100"), 250);
    }
    public void handleNewLines(){
        assertEquals(sc.Add("10, 20\n 30"), 60);
    }
    public void customDelimiter(){
        assertEquals(sc.Add("//;\\n1;2"), 3);
    }
    public void checkNegative() {
        try {
            sc.Add("-17,-18,10");
        } catch (HandleNegativeValues.NegativesNotAllowed e) {
            assertEquals(e.getMessage(), "Negatives Not Allowed. Numbers are: -17 -18 ");
        }
    }
    public void checkNegativeInCustomDelimiter() {
        try {
            sc.Add("//%\\n1%-8%-1%-12%26");
        } catch (HandleNegativeValues.NegativesNotAllowed e) {
            assertEquals(e.getMessage(), "Negatives Not Allowed. Numbers are: -8 -1 -12 ");
        }
    }
    public void greaterThan1000(){
        assertEquals(sc.Add("//-\\n2000-6-3-1-1001"), 10);
    }
    public void longLengthDelimiterWithoutBraces(){
        assertEquals(sc.Add("//%%%\\n1%%%3"), 4);
    }
    public void longLengthDelimiterWithBraces(){
        assertEquals(sc.Add("//[^^]\\n1^^2^^3"), 6);
    }
    public void longLengthMultipleDelimiters(){
        assertEquals(sc.Add("//[**][%%%][^^]\\n1**2%%%3^^6"), 12);
    }
    @org.testng.annotations.AfterMethod
    public void tearDown() {
        System.out.println("Completed");
    }
}