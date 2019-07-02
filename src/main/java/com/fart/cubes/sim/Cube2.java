package com.fart.cubes.sim;

import static com.fart.cubes.sim.BlockBy4.*;
import static com.fart.cubes.sim.JavaCubeSimApplication.*;

/**
 * Competition rules of Green: front and White: top.
 *
 *        +-----+
 *        |0A|0B|
 *        +-----+
 *        |0D|0C|
 *        +-----+
 * +--------------------------+
 * |1A|1B||2A|2B||3A|3B||4A|4B|
 * +--------------------------+
 * |1D|1C||2D|2C||3C|3D||4D|4C|
 * +--------------------------+
 *        +-----+
 *        |5A|5B|
 *        +-----+
 *        |5D|5C|
 *        +-----+
 *
 *         +---+
 *         |   |
 *         | W |
 *         |   |
 *     +-----------+---+
 *     |   |   |   |   |
 *     | o | G | R | B |
 *     |   |   |   |   |
 *     +-----------+---+
 *         |   |
 *         | Y |
 *         |   |
 *         +---+
 *
 */
public class Cube2 {

    public static final int rotateCW(int faceVal) {
        return faceVal << 8 | faceVal >> 24;
    }

    public static final int rotateCCW(int faceVal) {
        return faceVal >> 8 | faceVal << 24;
    }

    public static final int face(int color) {
        return color << BR.offset
                | color << BL.offset
                | color << TR.offset
                | color << TL.offset;
    }

    public static final int ALL_WHITE = face(W);
    public static final int ALL_GREEN = face(G);
    public static final int ALL_ORANGE = face(o);
    public static final int ALL_RED = face(R);
    public static final int ALL_BLUE = face(B);
    public static final int ALL_YELLOW = face(Y);

    public static final int[] SOLVED_FACES = new int[] {
            ALL_WHITE, ALL_GREEN, ALL_ORANGE, ALL_RED, ALL_BLUE, ALL_YELLOW
    };

    final int[] sides = new int[] {
            ALL_WHITE, ALL_ORANGE, ALL_GREEN, ALL_RED, ALL_BLUE, ALL_YELLOW
    };

    public boolean isSolved() {
        return SOLVED_FACES[sides[0] % 8] == sides[0]
                && SOLVED_FACES[sides[1] % 8] == sides[1]
                && SOLVED_FACES[sides[2] % 8] == sides[2]
                && SOLVED_FACES[sides[3] % 8] == sides[3]
                && SOLVED_FACES[sides[4] % 8] == sides[4]
                && SOLVED_FACES[sides[5] % 8] == sides[5];
    }

    public void applyRotateCW(int face) {
        sides[face] = rotateCW(sides[face]);
    }

    public void applyRotateCCW(int face) {
        sides[face] = rotateCCW(sides[face]);
    }

    public void transfer(int srcSideVal, BlockBy4 srcBlock, int destSide) {
        transfer(srcSideVal, srcBlock, destSide, srcBlock);
    }

    public void transfer(int srcSideVal, BlockBy4 srcBlock, int destSide, BlockBy4 destBlock) {
        sides[destSide] = destBlock.at(srcBlock.get(srcSideVal))
                | destBlock.getCleared(sides[destSide]);
    }

    public Cube2 right() {
        int finalSide = sides[0];
        transfer(sides[2], TR, 0);
        transfer(sides[2], BR, 0);
        transfer(sides[5], TR, 2);
        transfer(sides[5], BR, 2);
        transfer(sides[4], TL, 5, BR);
        transfer(sides[4], BL, 5, TR);
        transfer(finalSide, TR, 4, BL);
        transfer(finalSide, BR, 4, TL);
        applyRotateCW(3);
        return this;
    }

    public Cube2 rightP() {
        int finalSide = sides[5];
        transfer(sides[2], TR, 5);
        transfer(sides[2], BR, 5);
        transfer(sides[0], TR, 2);
        transfer(sides[0], BR, 2);
        transfer(sides[4], TL, 0, BR);
        transfer(sides[4], BL, 0, TR);
        transfer(finalSide, TR, 4, BL);
        transfer(finalSide, BR, 4, TL);
        applyRotateCCW(3);
        return this;
    }

    public Cube2 left() {
        int finalSide = sides[5];
        transfer(sides[2], TL, 5);
        transfer(sides[2], BL, 5);
        transfer(sides[0], TL, 2);
        transfer(sides[0], BL, 2);
        transfer(sides[4], TR, 0, BL);
        transfer(sides[4], BR, 0, TL);
        transfer(finalSide, BL, 4, TR);
        transfer(finalSide, TL, 4, BR);
        applyRotateCW(1);
        return this;
    }

    public Cube2 leftP() {
        int finalSide = sides[0];
        transfer(sides[2], TL, 0);
        transfer(sides[2], BL, 0);
        transfer(sides[5], TL, 2);
        transfer(sides[5], BL, 2);
        transfer(sides[4], TR, 5, BL);
        transfer(sides[4], BR, 5, TL);
        transfer(finalSide, BL, 4, TR);
        transfer(finalSide, TL, 4, BR);
        applyRotateCCW(1);
        return this;
    }

    public Cube2 up() {
        int finalSide = sides[1];
        transfer(sides[2], TL, 1);
        transfer(sides[2], TR, 1);
        transfer(sides[3], TL, 2);
        transfer(sides[3], TR, 2);
        transfer(sides[4], TL, 3);
        transfer(sides[4], TR, 3);
        transfer(finalSide, TL, 4);
        transfer(finalSide, TR, 4);
        applyRotateCW(0);
        return this;
    }

    public Cube2 upP() {
        int finalSide = sides[1];
        transfer(sides[4], TL, 1);
        transfer(sides[4], TR, 1);
        transfer(sides[3], TL, 4);
        transfer(sides[3], TR, 4);
        transfer(sides[2], TL, 3);
        transfer(sides[2], TR, 3);
        transfer(finalSide, TL, 2);
        transfer(finalSide, TR, 2);
        applyRotateCCW(0);
        return this;
    }

    public Cube2 down() {
        int finalSide = sides[4];
        transfer(sides[3], BL, 4);
        transfer(sides[3], BR, 4);
        transfer(sides[2], BL, 3);
        transfer(sides[2], BR, 3);
        transfer(sides[1], BL, 2);
        transfer(sides[1], BR, 2);
        transfer(finalSide, BL, 1);
        transfer(finalSide, BR, 1);
        applyRotateCW(5);
        return this;
    }

    public Cube2 downP() {
        int finalSide = sides[4];
        transfer(sides[1], BL, 4);
        transfer(sides[1], BR, 4);
        transfer(sides[2], BL, 1);
        transfer(sides[2], BR, 1);
        transfer(sides[3], BL, 2);
        transfer(sides[3], BR, 2);
        transfer(finalSide, BL, 3);
        transfer(finalSide, BR, 3);
        applyRotateCCW(5);
        return this;
    }

    public Cube2 front() {
        int finalSide = sides[0];
        transfer(sides[1], TR, 0, BR);
        transfer(sides[1], BR, 0, BL);
        transfer(sides[5], TL, 1, TR);
        transfer(sides[5], TR, 1, BR);
        transfer(sides[3], BL, 5, TL);
        transfer(sides[3], TL, 5, TR);
        transfer(finalSide, BR, 3, BL);
        transfer(finalSide, BL, 3, TL);
        applyRotateCW(2);
        return this;
    }

    public Cube2 frontP() {
        int finalSide = sides[0];
        transfer(sides[3], TL, 0, BL);
        transfer(sides[3], BL, 0, BR);
        transfer(sides[5], TL, 3, BL);
        transfer(sides[5], TR, 3, TL);
        transfer(sides[1], TR, 5, TL);
        transfer(sides[1], BR, 5, TR);
        transfer(finalSide, BL, 1, BR);
        transfer(finalSide, BR, 1, TR);
        applyRotateCCW(2);
        return this;
    }

    public Cube2 back() {
        int finalSide = sides[0];
        transfer(sides[3], TR, 0, TL);
        transfer(sides[3], BR, 0, TR);
        transfer(sides[5], BR, 3, TR);
        transfer(sides[5], BL, 3, BR);
        transfer(sides[1], BL, 5, BR);
        transfer(sides[1], TL, 5, BL);
        transfer(finalSide, TL, 1, BL);
        transfer(finalSide, TR, 1, TL);
        applyRotateCW(4);
        return this;
    }

    public Cube2 backP() {
        int finalSide = sides[0];
        transfer(sides[1], TL, 0, TR);
        transfer(sides[1], BL, 0, TL);
        transfer(sides[5], BL, 1, TL);
        transfer(sides[5], BR, 1, BL);
        transfer(sides[3], BR, 5, BL);
        transfer(sides[3], TR, 5, BR);
        transfer(finalSide, TL, 3, TR);
        transfer(finalSide, TR, 3, BR);
        applyRotateCCW(4);
        return this;
    }

    public Cube2 algo(CubeMoves...moves) {
        for (CubeMoves move : moves) {
            this.move(move);
        }
        return this;
    }

    public Cube2 move(CubeMoves move) {
        switch (move) {
            case RIGHT: this.right(); break;
            case RIGHT_P: this.rightP(); break;
            case LEFT: this.left(); break;
            case LEFT_P: this.leftP(); break;
            case UP: this.up(); break;
            case UP_P: this.upP(); break;
            case DOWN: this.down(); break;
            case DOWN_P: this.downP(); break;
            case FRONT: this.front(); break;
            case FRONT_P: this.frontP(); break;
            case BACK: this.back(); break;
            case BACK_P: this.backP(); break;
        }
        return this;
    }

    private String rst(int face) {
        return rs(face, TL, TR);
    }

    private String rst(int face, String end) {
        return rs(face, TL, TR, end);
    }

    private String rsb(int face) {
        return rs(face, BL, BR);
    }

    private String rsb(int face, String end) {
        return rs(face, BL, BR, end);
    }

    private String rs(int face, BlockBy4 left, BlockBy4 right) {
        return rs(face, left, right, "|");
    }

    private String rs(int face, BlockBy4 left, BlockBy4 right, String end) {
        return String.format("%s|%s%s", left.c(sides[face]), right.c(sides[face]), end);
    }

    public String pretty() {
        int face = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("    +-+-+\n");
        sb.append("    |").append(rst(face, "|\n"));
        sb.append("    +-+-+\n");
        sb.append("    |").append(rsb(face, "|\n"));
        sb.append("+-+-+-+-+-+-+-+-+\n");
        sb.append("|").append(rst(++face)).append(rst(face + 1)).append(rst(face + 2)).append(rst(face + 3, "|\n"));
        sb.append("+-+-+-+-+-+-+-+-+\n");
        sb.append("|").append(rsb(face)).append(rsb(++face)).append(rsb(++face)).append(rsb(++face, "|\n"));
        sb.append("+-+-+-+-+-+-+-+-+\n");
        sb.append("    |").append(rst(++face, "|\n"));
        sb.append("    +-+-+\n");
        sb.append("    |").append(rsb(face, "|\n"));
        sb.append("    +-+-+\n");
        return sb.toString();
    }
}

