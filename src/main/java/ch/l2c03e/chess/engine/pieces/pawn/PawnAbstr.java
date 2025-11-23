package ch.l2c03e.chess.engine.pieces.pawn;

import ch.l2c03e.chess.engine.base.Board;
import ch.l2c03e.chess.engine.base.Color;
import ch.l2c03e.chess.engine.base.Position;
import ch.l2c03e.chess.engine.pieces.ChessPiece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class PawnAbstr extends ChessPiece {

    public PawnAbstr(Position startPosition, Color color) {
        super(startPosition, color);
    }

    @Override
    public int getPoint() {
        return 1;
    }

    @Override
    public List<Position> getPossibleMoves(List<ChessPiece> otherPieces) {
        final var possibleMoves = new ArrayList<Position>();

        // is top of board -> cannot do any move
        if (isOnTopOfBoard()) {
            return Collections.emptyList();
        }

        final var move1FieldForward = nextFieldForward();
        final var move2FieldsForward = next2FieldsForward();

        // standard - can move forward when no other piece is there
        if (isFieldNoOne(otherPieces, move1FieldForward)) {
            possibleMoves.add(move1FieldForward);
        }

        // case - is on stat line
        if (isOnStartLine()) {
            // check if other piece is on the way
            if (isFieldNoOne(otherPieces, move1FieldForward) && isFieldNoOne(otherPieces, move2FieldsForward)) {
                possibleMoves.add(move2FieldsForward);
            }
        }

        // case - can attack other pieces
        otherPieces.stream()
                .filter(this::canAttack)
                .forEach(p -> possibleMoves.add(p.getPosition()));

        return possibleMoves;
    }

    @Override
    public boolean canAttack(ChessPiece otherPiece) {
        if (getColor().equals(otherPiece.getColor())) {
            return false;
        }

        if (Objects.equals(getPosition().x(), Board.MIN_X)) {
            return isOtherPieceIsRightDiagonal(otherPiece);
        } else if (Objects.equals(getPosition().x(), Board.MAX_X)) {
            return isOtherPieceIsLeftDiagonal(otherPiece);
        } else {
            return isOtherPieceIsRightDiagonal(otherPiece) || isOtherPieceIsLeftDiagonal(otherPiece);
        }
    }

    public boolean canPromote() {
        return isOnTopOfBoard();
    }

    protected abstract boolean isOnStartLine();
    protected abstract boolean isOnTopOfBoard();
    protected abstract Position nextFieldForward();
    protected abstract Position next2FieldsForward();
    protected abstract boolean isOtherPieceIsLeftDiagonal(ChessPiece piece);
    protected abstract boolean isOtherPieceIsRightDiagonal(ChessPiece piece);
}
