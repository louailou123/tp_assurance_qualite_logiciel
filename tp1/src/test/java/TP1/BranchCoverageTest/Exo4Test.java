package TP1.BranchCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.QuadraticEquation;

import static org.junit.jupiter.api.Assertions.*;

public class Exo4Test {

    @Test
    void zeroAShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> QuadraticEquation.solve(0, 1, 1));
    }

    @Test
    void deltaNegativeShouldReturnNull() {
        assertNull(QuadraticEquation.solve(1, 0, 1));
    }

    @Test
    void deltaZeroShouldReturnOneRoot() {
        assertArrayEquals(new double[]{-1.0}, QuadraticEquation.solve(1, 2, 1), 1e-9);
    }

    @Test
    void deltaPositiveShouldReturnTwoRoots() {
        assertArrayEquals(new double[]{2.0, 1.0}, QuadraticEquation.solve(1, -3, 2), 1e-9);
    }
}