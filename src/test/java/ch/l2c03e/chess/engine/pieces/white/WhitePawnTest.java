package ch.l2c03e.chess.engine.pieces.white;

import ch.l2c03e.chess.engine.base.Position;
import ch.l2c03e.chess.engine.pieces.pawn.WhitePawn;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

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

    }

    @Test
    void getPossibleMoves_isOnStartLine_onePiece2FieldsInFront_Do1Moves() {

    }

    @Test
    void getPossibleMoves_canAttack_and_onStartline_can3Moves() {

    }

    @Test
    void canAttack_topLeftBlack_returnsTrue() {

    }

    @Test
    void canAttack_topLeftWhite_returnsFalse() {

    }

    @Test
    void canAttack_topRightBlack_returnsTrue() {

    }

    @Test
    void canAttack_topRightWhite_returnsFalse() {

    }

}