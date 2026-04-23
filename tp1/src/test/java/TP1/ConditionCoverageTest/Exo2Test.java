package TP1.ConditionCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.Anagram;

import static org.junit.jupiter.api.Assertions.*;

public class Exo2Test {

    @Test
    void firstNullShouldThrow() {
        assertThrows(NullPointerException.class, () -> Anagram.isAnagram(null, "abc"));
    }

    @Test
    void secondNullShouldThrow() {
        assertThrows(NullPointerException.class, () -> Anagram.isAnagram("abc", null));
    }

    @Test
    void differentLengthsShouldReturnFalse() {
        assertFalse(Anagram.isAnagram("abc", "ab"));
    }

    @Test
    void validAnagramShouldReturnTrue() {
        assertTrue(Anagram.isAnagram("chien", "niche"));
    }

    @Test
    void invalidAnagramShouldReturnFalse() {
        assertFalse(Anagram.isAnagram("abc", "abd"));
    }
}