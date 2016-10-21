package com.ihealth.sdk;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        byte a = 0;
        a |= (1 << 1);
        System.out.println(a);
        a &= 0xfd;
        System.out.print(a);
    }
}