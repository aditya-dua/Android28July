package com.adityadua.testingdeploymentdemo12;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    // you need to learn about these methods :: in your Junit Library
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void userNameComparision() throws Exception{
        assertEquals("UserName error Comparision","aditya.dua","aditya.dua");
    }

    @Before
    public void substraction(){
        assertEquals("Not Equal and it will run first","Aditya","Aditya");
    }

}