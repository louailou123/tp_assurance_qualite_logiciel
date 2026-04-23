package TP1.BranchCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.RomanNumeral;

import static org.junit.jupiter.api.Assertions.*;

public class Exo5Test {

    @Test
    void invalidLowShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.toRoman(0));
    }

    @Test
    void invalidHighShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.toRoman(5000));
    }

    @Test
    void shouldConvertRepeatedRoman() {
        assertEquals("III", RomanNumeral.toRoman(3));
    }

    @Test
    void shouldConvertSubtractiveRoman() {
        assertEquals("IX", RomanNumeral.toRoman(9));
    }
}