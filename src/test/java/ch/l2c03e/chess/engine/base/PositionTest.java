package ch.l2c03e.chess.engine.base;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @ParameterizedTest
    @MethodSource("illegalYPositionsProvider")
    void createPosition_illegalYPosition_throwsIllegalArgument(Long yPosition) {
        var msg = assertThrows(IllegalArgumentException.class, () -> new Position('a', yPosition.intValue()));
        assertEquals(msg.getMessage(), "Invalid y Position");
    }

    @ParameterizedTest
    @MethodSource("illegalXPositionsProvider")
    void createPosition_illegalXPosition_throwsIllegalArgument(char xPosition) {
        var msg = assertThrows(IllegalArgumentException.class, () -> new Position(xPosition, 1));
        assertEquals(msg.getMessage(), "Invalid x Position");
    }
    
    @ParameterizedTest
    @MethodSource("validPositionProvider")
    void createPoistion_validPosition_works(char xPosition, int yPosition) {
        assertDoesNotThrow(() -> new Position(xPosition, yPosition));
    }

    @ParameterizedTest
    @MethodSource("illegalYPositionsProvider")
    void isValidPosition_illegalYPosition_returnFalse(Long yPosition) {
        assertFalse(Position.isValidPosition('a', yPosition.intValue()));
    }

    @ParameterizedTest
    @MethodSource("illegalXPositionsProvider")
    void isValidPosition_illegalXPosition_returnFalse(char xPosition) {
        assertFalse(Position.isValidPosition(xPosition, 1));
    }

    @ParameterizedTest
    @MethodSource("validPositionProvider")
    void isValidPosition_validPosition_returnTrue(char yPosition, int xPosition) {
        assertTrue(Position.isValidPosition(yPosition, xPosition));
    }
    
    private static Stream<Arguments> illegalYPositionsProvider() {
        return Stream.iterate(9L, x -> x + 1L)
                .limit(10)
                .map(Arguments::of);
    }

    private static Stream<Arguments> illegalXPositionsProvider() {
        List<Arguments> arguments = new ArrayList<>();
        for (char c : "ijklmnopqrstuvwxyz".toCharArray()) {
            arguments.add(Arguments.of(c));
        }
        return arguments.stream();
    }

    private static Stream<Arguments> validPositionProvider() {
        List<Arguments> arguments = new ArrayList<>();
        for (char c : "abcdefgh".toCharArray()) {
            for (int i = 1; i < 9 ; i++) {
                arguments.add(Arguments.of(c, i));
            }
        }
        return arguments.stream();
    }
}