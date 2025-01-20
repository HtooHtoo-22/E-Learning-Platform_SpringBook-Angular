package com.code.lms;

import com.code.lms.Caculate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculateTest {
    Caculate calculation ;
    @BeforeAll
    public void setUp(){
        calculation = new Caculate();
        System.out.println("Before All");
    }

    @BeforeEach
    public void hi(){
        System.out.println("Before each");
    };
    int sum = calculation.sum(2, 5);
    int testSum = 7;
    @Test
    public void testSum() {
        System.out.println("@Test sum(): " + sum + " = " + testSum);
        assertEquals(testSum, sum);
    }

    @AfterAll
    public void afterAll(){
        System.out.println("After All");
    }

}
