package TP1.LineCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.FizzBuzz;

import static org.junit.jupiter.api.Assertions.*;

public class Exo6Test {

    @Test
    void shouldThrowWhenNumberIsNotPositive() {
        assertThrows(IllegalArgumentException.class, () -> FizzBuzz.fizzBuzz(0));
    }

    @Test
    void shouldReturnFizzBuzz() {
        assertEquals("FizzBuzz", FizzBuzz.fizzBuzz(15));
    }

    @Test
    void shouldReturnFizz() {
        assertEquals("Fizz", FizzBuzz.fizzBuzz(3));
    }

    @Test
    void shouldReturnBuzz() {
        assertEquals("Buzz", FizzBuzz.fizzBuzz(5));
    }

    @Test
    void shouldReturnNumberAsString() {
        assertEquals("7", FizzBuzz.fizzBuzz(7));
    }
}