package com.mysteryticking;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MysteryTickingTest {

    // To capture the output of System.out.println for testing purposes
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach //before each test, we redirect the system output to our ByteArrayOutputStream
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach //after each test, we restore the system output to its original state
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testStateFailFastValidation() {
        assertThrows(IllegalArgumentException.class, () -> new State(null, 5));
        assertThrows(IllegalArgumentException.class, () -> new State("Snape", 0));
        assertThrows(IllegalArgumentException.class, () -> new State("Snape", -3));
        
        assertDoesNotThrow(() -> new State("Dumbledore", 1));
    }

    @Test
    public void testPipeBombFailFastValidation() {
        assertThrows(IllegalArgumentException.class, () -> new PipeBomb(0));

        assertDoesNotThrow(() -> new PipeBomb(10));
    }

    @Test
    public void testFiniteEntityUnregistersProperly() {
        PipeBomb pb = new PipeBomb(5);
        
        StateMachineEntity sme = new StateMachineEntity("Test", 1, new State("Fire", 1));
        pb.register(new FiniteEntity(sme, pb, 2));
        
        pb.start();
        
        String output = outContent.toString();
        
        long fireCount = output.lines().filter(line -> line.equals("Fire")).count();
        assertEquals(2, fireCount);
    }

    @Test
    public void testConditionalEntityAndBoomOutput() {
        PipeBomb pb = new PipeBomb(3);
        
        pb.register(new ConditionalEntity(tick -> tick % 2 == 0, "Even Tick!"));
        pb.start();
        
        String output = outContent.toString().trim();
        
        assertTrue(output.contains("Even Tick!"));
        assertTrue(output.endsWith("BOOM!"));
    }
}
