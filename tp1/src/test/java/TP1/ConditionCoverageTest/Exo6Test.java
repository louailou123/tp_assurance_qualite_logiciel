package TP1.ConditionCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.FizzBuzz;

import static org.junit.jupiter.api.Assertions.*;

public class Exo6Test {

    @Test
    void zeroShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> FizzBuzz.fizzBuzz(0));
    }

    @Test
    void multipleOf15ShouldReturnFizzBuzz() {
        assertEquals("FizzBuzz", FizzBuzz.fizzBuzz(15));
    }

    @Test
    void multipleOf3OnlyShouldReturnFizz() {
        assertEquals("Fizz", FizzBuzz.fizzBuzz(9));
    }

    @Test
    void multipleOf5OnlyShouldReturnBuzz() {
        assertEquals("Buzz", FizzBuzz.fizzBuzz(10));
    }

    @Test
    void nonMultipleShouldReturnNumber() {
        assertEquals("7", FizzBuzz.fizzBuzz(7));
    }

    @Test
    void oneShouldReturnOne() {
        assertEquals("1", FizzBuzz.fizzBuzz(1));
    }
}