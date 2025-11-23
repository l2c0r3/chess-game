package ch.l2c03e.chess.engine.pieces;

import ch.l2c03e.chess.engine.base.Color;
import ch.l2c03e.chess.engine.base.Position;

import java.util.List;

public abstract class ChessPiece {

    private Position position;
    private Color color;

    public ChessPiece(Position startPosition, Color color) {
        this.position = startPosition;
        this.color = color;
    }

    public void move(Position pos) {
        this.position = pos;
    }

    public Position getPosition() {
        return this.position;
    }

    public Color getColor() {
        return this.color;
    };

    public abstract int getPoint();

    public abstract List<Position> getPossibleMoves(List<ChessPiece> otherPieces);

    public abstract boolean canAttack(ChessPiece otherPieces);

    protected boolean isFieldNoOne(List<ChessPiece> otherPieces, Position pos) {
        return otherPieces.stream()
                .map(ChessPiece::getPosition)
                .noneMatch(p -> p.equals(pos));
    }
}
