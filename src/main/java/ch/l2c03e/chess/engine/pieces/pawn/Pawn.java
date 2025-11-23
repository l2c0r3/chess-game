package ch.l2c03e.chess.engine.pieces.pawn;

import ch.l2c03e.chess.engine.base.Color;
import ch.l2c03e.chess.engine.base.Position;
import ch.l2c03e.chess.engine.pieces.ChessPiece;

import java.util.List;

public class Pawn extends ChessPiece {

    private final PawnAbstr pawn;

    public Pawn(Position startPosition, Color color) {
        super(startPosition, color);
        this.pawn = switch (color) {
            case WHITE -> new WhitePawn(startPosition);
            case BLACK -> new BlackPawn(startPosition);
        };
    }

    @Override
    public int getPoint() {
        return pawn.getPoint();
    }

    @Override
    public List<Position> getPossibleMoves(List<ChessPiece> otherPieces) {
        return pawn.getPossibleMoves(otherPieces);
    }
}
