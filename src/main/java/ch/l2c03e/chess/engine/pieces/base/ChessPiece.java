package ch.l2c03e.chess.engine.pieces.base;

import ch.l2c03e.chess.engine.base.Color;
import ch.l2c03e.chess.engine.base.Position;

import java.util.List;

public abstract class ChessPiece {

    private Position position;

    public ChessPiece(Position startPosition) {
        this.position = startPosition;
    }

    public void move(Position pos) {
        this.position = pos;
    }

    public Position getPosition() {
        return this.position;
    }

    public abstract int getPoint();

    public abstract Color getColor();

    public abstract List<Position> getPossibleMoves(List<ChessPiece> otherPieces);

    public abstract boolean canAttack(ChessPiece otherPieces);


    boolean isFieldNoOne(List<ChessPiece> otherPieces, Position pos) {
        return otherPieces.stream()
                .map(ChessPiece::getPosition)
                .noneMatch(p -> p.equals(pos));
    }
}
