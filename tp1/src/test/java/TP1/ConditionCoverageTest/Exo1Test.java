package TP1.ConditionCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.Palindrome;

import static org.junit.jupiter.api.Assertions.*;

public class Exo1Test {

    @Test
    void nullShouldThrow() {
        assertThrows(NullPointerException.class, () -> Palindrome.isPalindrome(null));
    }

    @Test
    void mismatchShouldReturnFalse() {
        assertFalse(Palindrome.isPalindrome("ab"));
    }

    @Test
    void simplePalindromeShouldReturnTrue() {
        assertTrue(Palindrome.isPalindrome("kayak"));
    }

    @Test
    void palindromeWithSpacesAndCaseShouldReturnTrue() {
        assertTrue(Palindrome.isPalindrome("Esope reste ici et se repose"));
    }
}