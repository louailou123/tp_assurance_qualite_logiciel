package TP1.LineCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.Anagram;

import static org.junit.jupiter.api.Assertions.*;

public class Exo2Test {

    @Test
    void shouldThrowWhenStringIsNull() {
        assertThrows(NullPointerException.class, () -> Anagram.isAnagram(null, "abc"));
    }

    @Test
    void shouldReturnFalseWhenLengthsDiffer() {
        assertFalse(Anagram.isAnagram("abc", "ab"));
    }

    @Test
    void shouldReturnTrueForValidAnagram() {
        assertTrue(Anagram.isAnagram("chien", "niche"));
    }
}