package pl.rejurhf.support;

public class PositionPair {
    public int X;
    public int Y;
    public PositionPair(int x, int y) {
        X = x;
        Y = y;
    }

    @Override
    public String toString() {
        return "(" + X + "," + Y + ")";
    }
}
