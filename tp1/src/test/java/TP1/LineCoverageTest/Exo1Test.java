package TP1.LineCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.Palindrome;

import static org.junit.jupiter.api.Assertions.*;

public class Exo1Test {

    @Test
    void shouldThrowWhenStringIsNull() {
        assertThrows(NullPointerException.class, () -> Palindrome.isPalindrome(null));
    }

    @Test
    void shouldReturnFalseForNonPalindrome() {
        assertFalse(Palindrome.isPalindrome("bonjour"));
    }

    @Test
    void shouldReturnTrueForPalindrome() {
        assertTrue(Palindrome.isPalindrome("kayak"));
    }
}