package ch.l2c03e.chess.engine.pieces.pawn;

import ch.l2c03e.chess.engine.base.Board;
import ch.l2c03e.chess.engine.base.Color;
import ch.l2c03e.chess.engine.base.Position;
import ch.l2c03e.chess.engine.pieces.ChessPiece;

public class WhitePawn extends PawnAbstr {

    private WhitePawn(Position startPosition, Color color) {
        super(startPosition, color);
    }

    public WhitePawn(Position startPosition) {
        super(startPosition, Color.WHITE);
    }

    @Override
    public boolean canAttack(ChessPiece otherPieces) {
        return false;
    }

    @Override
    protected boolean isOnStartLine() {
        return getPosition().y() == 2;
    }

    @Override
    protected boolean isOnTopOfBoard() {
        return getPosition().y() == Board.MAX_Y;
    }

    @Override
    protected Position nextFieldForward() {
        return new Position(getPosition().x(), getPosition().y() + 1);
    }

    @Override
    protected Position next2FieldsForward() {
        return new Position(getPosition().x(), getPosition().y() + 2);
    }
}
