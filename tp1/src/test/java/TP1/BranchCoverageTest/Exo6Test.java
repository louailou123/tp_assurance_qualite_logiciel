package TP1.BranchCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.FizzBuzz;

import static org.junit.jupiter.api.Assertions.*;

public class Exo6Test {

    @Test
    void invalidValueShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> FizzBuzz.fizzBuzz(0));
    }

    @Test
    void divisibleBy15ShouldReturnFizzBuzz() {
        assertEquals("FizzBuzz", FizzBuzz.fizzBuzz(30));
    }

    @Test
    void divisibleBy3OnlyShouldReturnFizz() {
        assertEquals("Fizz", FizzBuzz.fizzBuzz(6));
    }

    @Test
    void divisibleBy5OnlyShouldReturnBuzz() {
        assertEquals("Buzz", FizzBuzz.fizzBuzz(10));
    }

    @Test
    void otherShouldReturnNumber() {
        assertEquals("8", FizzBuzz.fizzBuzz(8));
    }
}