package ch.l2c03e.chess.engine.base;

/**
 *
 * has always lower y pos!
 *
 * @param y
 * @param x
 */
public record Position(char x, int y) {

    public Position(char x, int y) {
        if (!Position.xPositionIsValid(x)) {
            throw new IllegalArgumentException("Invalid x Position");
        }
        if (!xPositionIsValid(y)) {
            throw new IllegalArgumentException("Invalid y Position");
        }
        this.y = Character.toLowerCase(y);
        this.x = x;
    }

    public static boolean isValidPosition(char y, int x) {
        return xPositionIsValid(y) && xPositionIsValid(x);
    }

    private static boolean xPositionIsValid(char pos) {
        var lowerY = Character.toLowerCase(pos);
        return lowerY >= Board.MIN_X && lowerY <= Board.MAX_X;
    }

    private static boolean xPositionIsValid(int pos) {
        return pos >= Board.MIN_Y && pos <= Board.MAX_Y;
    }
}
