package ch.l2c03e.chess.engine.util;

import ch.l2c03e.chess.engine.base.Position;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;

public class PositionUtil {

    private PositionUtil() {}

    public static List<Position> getAllPositions() {
        var positions = new ArrayList<Position>();
        for (char c : "abcdefgh".toCharArray()) {
            for (int i = 1; i < 9 ; i++) {
                positions.add(new Position(c, i));
            }
        }
        return positions;
    }
}
