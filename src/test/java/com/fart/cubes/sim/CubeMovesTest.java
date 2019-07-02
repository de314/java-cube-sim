package com.fart.cubes.sim;

import org.junit.Test;

import java.util.Arrays;

import static com.fart.cubes.sim.CubeMoves.*;
import static com.fart.cubes.sim.CubeMoves.FRONT_P;
import static org.junit.Assert.*;

public class CubeMovesTest {

    @Test
    public void parse_tPerm() {
        CubeMoves[] expected = new CubeMoves[] {
                RIGHT, UP, RIGHT_P, UP_P, RIGHT_P, FRONT, RIGHT, RIGHT, UP_P, RIGHT_P, UP_P, RIGHT, UP, RIGHT_P, FRONT_P
        };
        CubeMoves[] actual = CubeMoves.parse("[R U R' U'] [R' F] [R2 U' R'] U' [R U R' F']");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void parse_yPerm() {
        CubeMoves[] expected = new CubeMoves[] {
                FRONT, RIGHT, UP_P, RIGHT_P, UP_P, RIGHT, UP, RIGHT_P, FRONT_P, RIGHT, UP, RIGHT_P, UP_P, RIGHT_P, FRONT, RIGHT, FRONT_P
        };
        CubeMoves[] actual = CubeMoves.parse("F R U' R' U' [R U R' F'] {[R U R' U'] [R' F R F']}");

        assertArrayEquals(expected, actual);
    }
}