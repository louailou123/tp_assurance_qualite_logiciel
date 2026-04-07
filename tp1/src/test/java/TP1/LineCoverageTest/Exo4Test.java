package TP1.LineCoverageTest;

import org.junit.jupiter.api.Test;
import tpaql.QuadraticEquation;

import static org.junit.jupiter.api.Assertions.*;

public class Exo4Test {

    @Test
    void shouldThrowWhenAIsZero() {
        assertThrows(IllegalArgumentException.class, () -> QuadraticEquation.solve(0, 2, 1));
    }

    @Test
    void shouldReturnNullWhenDeltaNegative() {
        assertNull(QuadraticEquation.solve(1, 0, 1));
    }

    @Test
    void shouldReturnOneRootWhenDeltaZero() {
        assertArrayEquals(new double[]{-1.0}, QuadraticEquation.solve(1, 2, 1), 1e-9);
    }

    @Test
    void shouldReturnTwoRootsWhenDeltaPositive() {
        assertArrayEquals(new double[]{2.0, 1.0}, QuadraticEquation.solve(1, -3, 2), 1e-9);
    }
}