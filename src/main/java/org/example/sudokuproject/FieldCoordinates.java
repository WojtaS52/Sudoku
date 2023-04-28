package org.example.sudokuproject;

public class FieldCoordinates {
    private int x1;
    private int y1;

    public FieldCoordinates(int x, int y) {
        this.x1 = x;
        this.y1 = y;
    }

    public int getX() {
        return x1;
    }

    public int getY() {
        return y1;
    }

    @Override
    public String toString() {
        return "FieldCoordinates{"
                + "x="
                + x1
                + ", y="
                + y1
                + '}';
    }
}