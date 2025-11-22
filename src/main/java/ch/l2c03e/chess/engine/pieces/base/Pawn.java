package ch.l2c03e.chess.engine.pieces.base;

import ch.l2c03e.chess.engine.base.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Pawn extends ChessPiece {

    public Pawn(Position startPosition) {
        super(startPosition);
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


    public boolean canPromote() {
        return isOnTopOfBoard();
    }

    protected abstract boolean isOnStartLine();
    protected abstract boolean isOnTopOfBoard();
    protected abstract Position nextFieldForward();
    protected abstract Position next2FieldsForward();

}
