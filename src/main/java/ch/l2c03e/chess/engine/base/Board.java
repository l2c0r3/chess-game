package ch.l2c03e.chess.engine.base;

import ch.l2c03e.chess.engine.pieces.ChessPiece;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

public class Board {

    public static int MIN_Y = 1;
    public static int MAX_Y = 8;

    public static char MIN_X = 'a';
    public static char MAX_X = 'h';

    private final List<ChessPiece> pieces = new ArrayList<>();

    public Board(List<ChessPiece> pieces) {
       this.pieces.addAll(pieces);
    }

    public void makeMove(ChessPiece chessPiece, Position position) {
        var boardPice = this.pieces.stream()
                .filter(p -> p.getColor().equals(chessPiece.getColor()))
                .filter(p -> p.getClass().equals(chessPiece.getClass()))
                .filter(p -> p.getPosition().equals(position))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No such chess piece"));
        boardPice.move(position);
    }

    public List<ChessPiece> getPieces() {
        return pieces;
    }

}
