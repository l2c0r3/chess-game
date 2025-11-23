package ch.l2c03e.chess.engine.pieces.pawn;

import ch.l2c03e.chess.engine.base.Color;
import ch.l2c03e.chess.engine.base.Position;
import ch.l2c03e.chess.engine.pieces.ChessPiece;
import ch.l2c03e.chess.engine.util.PositionUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WhitePawnTest {

    @Test
    void getPossibleMoves_isOnTop_cannotMove() {
        // Arrange
        var testee = new WhitePawn(new Position('a', 8));

        // Act
        var result = testee.getPossibleMoves(Collections.emptyList());

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getPossibleMoves_OneInFront_cannotMove() {
        // Arrange
        var testee = new WhitePawn(new Position('a', 1));
        var otherPiece = new WhitePawn(new Position('a', 2));

        // Act
        var result = testee.getPossibleMoves(List.of(otherPiece));

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getPossibleMoves_isNotOnStartLine_can1Move() {
        // Arrange
        var testee = new WhitePawn(new Position('a', 4));

        // Act
        var result = testee.getPossibleMoves(Collections.emptyList());

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(new Position('a', 5)));
    }

    @Test
    void getPossibleMoves_isOnStartLine_canDo2Moves() {
        // Arrange
        var testee = new WhitePawn(new Position('a', 2));

        // Act
        var result = testee.getPossibleMoves(Collections.emptyList());

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(new Position('a', 3)));
        assertTrue(result.contains(new Position('a', 4)));
    }

    @Test
    void getPossibleMoves_isOnStartLine_onePieceInFront_cannotMove() {
        // Arrange
        var testee = new WhitePawn(new Position('a', 2));
        var otherPiece = new WhitePawn(new Position('a', 3));

        // Act
        var result = testee.getPossibleMoves(List.of(otherPiece));

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getPossibleMoves_isOnStartLine_onePiece2FieldsInFront_Do1Moves() {
        // Arrange
        var testee = new WhitePawn(new Position('a', 2));
        var otherPiece = new WhitePawn(new Position('a', 4));

        // Act
        var result = testee.getPossibleMoves(List.of(otherPiece));

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(new Position('a', 3)));
    }

    @Test
    void getPossibleMoves_canAttack_and_onStartline_can3Moves() {
        // Arrange
        var testee = new WhitePawn(new Position('a', 2));
        var otherPiece = new BlackPawn(new Position('b', 3));

        // Act
        var result = testee.getPossibleMoves(List.of(otherPiece));

        // Assert
        assertEquals(3, result.size());
        assertTrue(result.contains(new Position('a', 3)));
        assertTrue(result.contains(new Position('a', 4)));
        assertTrue(result.contains(new Position('b', 3)));
    }

    @Test
    void getPossibleMoves_canAttack_2ppieces_and_onStartline_can4Moves() {
        // Arrange
        var testee = new WhitePawn(new Position('b', 2));
        var otherPiece = new BlackPawn(new Position('a', 3));
        var otherPiece2 = new BlackPawn(new Position('c', 3));

        // Act
        var result = testee.getPossibleMoves(List.of(otherPiece, otherPiece2));

        // Assert
        assertEquals(4, result.size());
        assertTrue(result.contains(new Position('b', 3)));
        assertTrue(result.contains(new Position('b', 4)));
        assertTrue(result.contains(new Position('a', 3)));
        assertTrue(result.contains(new Position('c', 3)));
    }

    @Test
    void canAttack_topLeftBlack_returnsTrue() {
        // Arrange
        var testee = new WhitePawn(new Position('b', 2));
        var otherPiece = new BlackPawn(new Position('a', 3));

        // Act
        var result = testee.canAttack(otherPiece);

        // Assert
        assertTrue(result);
    }


    private static Stream<Arguments> canAttackPieceProvider() {
        List<Pawn> whitePawns = PositionUtil.getAllPositions().stream()
                .filter(p -> !p.equals(new Position('a', 3)))
                .filter(p -> !p.equals(new Position('c', 3)))
                .map(p -> new Pawn(p, Color.WHITE))
                .toList();

        List<Pawn> blackPawns = new ArrayList<>() {
            {
                add(new Pawn(new Position('a', 3), Color.BLACK));
                add(new Pawn(new Position('c', 3), Color.BLACK));
            }
        };

        return Stream.of(whitePawns, blackPawns)
                .flatMap(Collection::stream)
                .map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("canAttackPieceProvider")
    void canAttack_returnsTrue(Pawn piece) {
        // Arrange
        var testee = new WhitePawn(new Position('b', 2));

        // Act
        var result = testee.canAttack(piece);

        // Assert
        assertTrue(result);
    }

    private static Stream<Arguments> cannotAttackPieceProvider() {
        List<Pawn> blackPawns = PositionUtil.getAllPositions().stream()
                .filter(p -> !p.equals(new Position('a', 3)))
                .filter(p -> !p.equals(new Position('c', 3)))
                .map(p -> new Pawn(p, Color.BLACK))
                .toList();

        List<Pawn> whitePawns = new ArrayList<>() {
            {
                add(new Pawn(new Position('a', 3), Color.WHITE));
                add(new Pawn(new Position('c', 3), Color.BLACK));
            }
        };

        return Stream.of(whitePawns, blackPawns)
                .flatMap(Collection::stream)
                .map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("cannotAttackPieceProvider")
    void canAttack_returnsFalse(Pawn piece) {
        // Arrange
        var testee = new WhitePawn(new Position('b', 2));

        // Act
        var result = testee.canAttack(piece);

        // Assert
        assertFalse(result);
    }

}