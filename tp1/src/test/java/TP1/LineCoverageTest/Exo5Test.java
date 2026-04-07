package TP1.LineCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.RomanNumeral;

import static org.junit.jupiter.api.Assertions.*;

public class Exo5Test {

    @Test
    void shouldThrowWhenTooSmall() {
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.toRoman(0));
    }

    @Test
    void shouldThrowWhenTooLarge() {
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.toRoman(4000));
    }

    @Test
    void shouldConvertSimpleNumber() {
        assertEquals("III", RomanNumeral.toRoman(3));
    }

    @Test
    void shouldConvertSubtractiveNumber() {
        assertEquals("IV", RomanNumeral.toRoman(4));
    }
}