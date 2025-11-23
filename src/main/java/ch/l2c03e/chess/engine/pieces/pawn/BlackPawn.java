package ch.l2c03e.chess.engine.pieces.pawn;

import ch.l2c03e.chess.engine.base.Board;
import ch.l2c03e.chess.engine.base.Color;
import ch.l2c03e.chess.engine.base.Position;
import ch.l2c03e.chess.engine.pieces.ChessPiece;

import java.util.Objects;
import java.util.function.Predicate;

class BlackPawn extends PawnAbstr {

    private BlackPawn(Position startPosition, Color color) {
        super(startPosition, color);
    }

    protected BlackPawn(Position startPosition) {
        super(startPosition, Color.BLACK);
    }

    @Override
    protected boolean isOnStartLine() {
        return getPosition().y() == 7;
    }

    @Override
    protected boolean isOnTopOfBoard() {
        return getPosition().y() == Board.MIN_Y;
    }

    @Override
    protected Position nextFieldForward() {
        return new Position(getPosition().x(), getPosition().y() - 1);
    }

    @Override
    protected Position next2FieldsForward() {
        return new Position(getPosition().x(), getPosition().y() - 2);
    }

    @Override
    protected boolean isOtherPieceIsLeftDiagonal(ChessPiece piece) {
        char possibleXAche = (char) (getPosition().x() - 1);
        int possibleYAche = getPosition().y() - 1;
        var possiblePos = new Position(possibleXAche, possibleYAche);
        return possiblePos.equals(piece.getPosition());
    }

    @Override
    protected boolean isOtherPieceIsRightDiagonal(ChessPiece piece) {
        char possibleXAche = (char) (getPosition().x() + 1);
        int possibleYAche = getPosition().y() - 1;
        var possiblePos = new Position(possibleXAche, possibleYAche);
        return possiblePos.equals(piece.getPosition());
    }
}
