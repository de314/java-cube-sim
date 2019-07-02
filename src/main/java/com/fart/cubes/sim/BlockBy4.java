package com.fart.cubes.sim;

import static com.fart.cubes.sim.JavaCubeSimApplication.LETTER_MAP;

public enum BlockBy4 {

    TL(0),
    TR(8),
    BR(16),
    BL(24);

    final int offset;
    final int mask;
    final int notMask;

    BlockBy4(int offset) {
        this.offset = offset;
        this.mask = 255 << offset;
        this.notMask = ~this.mask;
    }

    public int get(int faceVal) {
        return (faceVal & mask) >> offset;
    }

    public int getExtracted(int faceValue) {
        return faceValue & mask;
    }

    public int getCleared(int faceValue) {
        return faceValue & notMask;
    }

    public int at(int color) {
        return color << this.offset;
    }

    /**
     * COLOR
     */
    public String c(int faceVal) {
        return LETTER_MAP.getOrDefault(get(faceVal), '?').toString();
    }

    public int transfer(int src, int dest) {
        return (src & mask) | (dest & notMask);
    }
}
