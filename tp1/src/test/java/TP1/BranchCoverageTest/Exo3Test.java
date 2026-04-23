package TP1.BranchCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.BinarySearch;

import static org.junit.jupiter.api.Assertions.*;

public class Exo3Test {

    @Test
    void nullArrayShouldThrow() {
        assertThrows(NullPointerException.class, () -> BinarySearch.binarySearch(null, 1));
    }

    @Test
    void shouldFindMiddleElement() {
        assertEquals(2, BinarySearch.binarySearch(new int[]{1, 3, 5, 7, 9}, 5));
    }

    @Test
    void shouldGoRightBranch() {
        assertEquals(4, BinarySearch.binarySearch(new int[]{1, 3, 5, 7, 9}, 9));
    }

    @Test
    void shouldGoLeftBranchAndReturnMinusOne() {
        assertEquals(-1, BinarySearch.binarySearch(new int[]{1, 3, 5, 7, 9}, 0));
    }
}