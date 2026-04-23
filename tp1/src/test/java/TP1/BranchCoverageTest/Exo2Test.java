package TP1.BranchCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.Anagram;

import static org.junit.jupiter.api.Assertions.*;

public class Exo2Test {

    @Test
    void nullShouldThrow() {
        assertThrows(NullPointerException.class, () -> Anagram.isAnagram(null, "abc"));
    }

    @Test
    void differentLengthShouldReturnFalse() {
        assertFalse(Anagram.isAnagram("abc", "ab"));
    }

    @Test
    void trueAnagramShouldReturnTrue() {
        assertTrue(Anagram.isAnagram("chien", "niche"));
    }

    @Test
    void falseAnagramShouldReturnFalse() {
        assertFalse(Anagram.isAnagram("abc", "abd"));
    }
}