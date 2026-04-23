package TP1.ConditionCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.RomanNumeral;

import static org.junit.jupiter.api.Assertions.*;

public class Exo5Test {

    @Test
    void lowerBoundShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.toRoman(0));
    }

    @Test
    void upperBoundShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.toRoman(4000));
    }

    @Test
    void shouldConvertWithoutSubtraction() {
        assertEquals("VIII", RomanNumeral.toRoman(8));
    }

    @Test
    void shouldConvertWithSubtraction() {
        assertEquals("XL", RomanNumeral.toRoman(40));
    }

    @Test
    void shouldConvertComplexRoman() {
        assertEquals("MCMXCIV", RomanNumeral.toRoman(1994));
    }
}