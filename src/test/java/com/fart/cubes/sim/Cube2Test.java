package com.fart.cubes.sim;

import org.junit.Test;

import static com.fart.cubes.sim.CubeMoves.*;
import static org.junit.Assert.*;

public class Cube2Test {

    private int getFace(int tl, int tr, int br, int bl) {
        return tl << 0 | tr << 8 | br << 16 | bl << 24;
    }

    @Test
    public void rotateCW() {
        Cube2 cube = new Cube2();
        cube.sides[2] = getFace(1, 2, 3, 4);
        cube.applyRotateCW(2);
        int expected = getFace(4, 1, 2, 3);
        assertEquals(expected, cube.sides[2]);
        cube.applyRotateCW(2);
        expected = getFace(3, 4, 1, 2);
        assertEquals(expected, cube.sides[2]);
        cube.applyRotateCW(2);
        expected = getFace(2, 3, 4, 1);
        assertEquals(expected, cube.sides[2]);
        cube.applyRotateCW(2);
        expected = getFace(1, 2, 3, 4);
        assertEquals(expected, cube.sides[2]);
    }

    @Test
    public void rotateCCW() {
        Cube2 cube = new Cube2();
        cube.sides[2] = getFace(1, 2, 3, 4);
        cube.applyRotateCCW(2);
        int expected = getFace(2, 3, 4, 1);
        assertEquals(expected, cube.sides[2]);
        cube.applyRotateCCW(2);
        expected = getFace(3, 4, 1, 2);
        assertEquals(expected, cube.sides[2]);
        cube.applyRotateCCW(2);
        expected = getFace(4, 1, 2, 3);
        assertEquals(expected, cube.sides[2]);
        cube.applyRotateCCW(2);
        expected = getFace(1, 2, 3, 4);
        assertEquals(expected, cube.sides[2]);
    }

    @Test
    public void right() {
        testMoveCycle(RIGHT);
    }

    private void testMoveCycle(CubeMoves move) {
        Cube2 cube = new Cube2();
        assertTrue(cube.isSolved());
        cube.algo(move);
        assertFalse(cube.isSolved());
        cube.algo(move, move, move);
        assertTrue(cube.isSolved());
    }

    @Test
    public void rightP() {
        testMoveCycle(RIGHT_P);
    }

    @Test
    public void left() {
        testMoveCycle(CubeMoves.LEFT);
    }

    @Test
    public void leftP() {
        testMoveCycle(CubeMoves.LEFT_P);
    }

    @Test
    public void up() {
        testMoveCycle(UP);
    }

    @Test
    public void upP() {
        testMoveCycle(UP_P);
    }

    @Test
    public void down() {
        testMoveCycle(CubeMoves.DOWN);
    }

    @Test
    public void downP() {
        testMoveCycle(CubeMoves.DOWN_P);
    }

    @Test
    public void front() {
        testMoveCycle(CubeMoves.FRONT);
    }

    @Test
    public void frontP() {
        testMoveCycle(CubeMoves.FRONT_P);
    }

    @Test
    public void back() {
        testMoveCycle(CubeMoves.BACK);
    }

    @Test
    public void backP() {
        testMoveCycle(CubeMoves.BACK_P);
    }

    @Test
    public void algo_fidgit() {
        Cube2 cube2 = new Cube2();
        assertTrue(cube2.isSolved());
        cube2.algo(UP, RIGHT_P, UP_P, RIGHT,
                UP, RIGHT_P, UP_P, RIGHT,
                UP, RIGHT_P, UP_P, RIGHT);
        assertFalse(cube2.isSolved());
        cube2.algo(UP, RIGHT_P, UP_P, RIGHT,
                UP, RIGHT_P, UP_P, RIGHT,
                UP, RIGHT_P, UP_P, RIGHT);
        assertTrue(cube2.isSolved());
    }

    @Test
    public void algo_tPerm() {
        Cube2 cube2 = new Cube2();
        CubeMoves.parse("");
        assertTrue(cube2.isSolved());
        cube2.algo(CubeMoves.parse("[R U R' U'] [R' F] [R2 U' R'] U' [R U R' F']"));
        assertFalse(cube2.isSolved());
        cube2.algo(CubeMoves.parse("[R U R' U'] [R' F] [R2 U' R'] U' [R U R' F']"));
        assertTrue(cube2.isSolved());
    }

    @Test
    public void algo_yPerm() {
        Cube2 cube2 = new Cube2();
        CubeMoves.parse("");
        assertTrue(cube2.isSolved());
        cube2.algo(CubeMoves.parse("F R U' R' U' [R U R' F'] {[R U R' U'] [R' F R F']}"));
        assertFalse(cube2.isSolved());
        cube2.algo(CubeMoves.parse("F R U' R' U' [R U R' F'] {[R U R' U'] [R' F R F']}"));
        assertTrue(cube2.isSolved());
    }
}