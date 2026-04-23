package TP1.BranchCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.Palindrome;

import static org.junit.jupiter.api.Assertions.*;

public class Exo1Test {

    @Test
    void nullStringShouldThrow() {
        assertThrows(NullPointerException.class, () -> Palindrome.isPalindrome(null));
    }

    @Test
    void nonPalindromeShouldReturnFalse() {
        assertFalse(Palindrome.isPalindrome("hello"));
    }

    @Test
    void palindromeShouldReturnTrue() {
        assertTrue(Palindrome.isPalindrome("kayak"));
    }
}