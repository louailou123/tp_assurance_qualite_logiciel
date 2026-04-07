package TP1.LineCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.BinarySearch;

import static org.junit.jupiter.api.Assertions.*;

public class Exo3Test {

    @Test
    void shouldThrowWhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> BinarySearch.binarySearch(null, 3));
    }

    @Test
    void shouldFindExistingElement() {
        assertEquals(2, BinarySearch.binarySearch(new int[]{1, 3, 5, 7, 9}, 5));
    }

    @Test
    void shouldReturnMinusOneWhenElementNotFound() {
        assertEquals(-1, BinarySearch.binarySearch(new int[]{1, 3, 5, 7, 9}, 8));
    }
}