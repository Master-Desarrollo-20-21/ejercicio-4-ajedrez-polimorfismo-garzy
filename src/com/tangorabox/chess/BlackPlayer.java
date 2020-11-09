package com.tangorabox.chess;

import com.tangorabox.chess.coordinate.Column;
import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.coordinate.Row;
import com.tangorabox.chess.piece.PieceFactory;
import com.tangorabox.chess.piece.PieceType;

public class BlackPlayer extends Player {


    public BlackPlayer(Board board, PieceFactory pieceFactory) {
        super(board, pieceFactory);
    }

    @Override
    public void putPieces(Board board) {
        put(PieceType.KING, new Coordinate(Column.E, Row.EIGHT));
        put(PieceType.QUEEN, new Coordinate(Column.D, Row.EIGHT));
        put(PieceType.BISHOP, new Coordinate(Column.C, Row.EIGHT));
        put(PieceType.BISHOP, new Coordinate(Column.F, Row.EIGHT));
        put(PieceType.KNIGHT, new Coordinate(Column.G, Row.EIGHT));
        put(PieceType.KNIGHT, new Coordinate(Column.B, Row.EIGHT));
        put(PieceType.ROOK, new Coordinate(Column.H, Row.EIGHT));
        put(PieceType.ROOK, new Coordinate(Column.A, Row.EIGHT));

        for (Column column : Column.values()) {
            put(PieceType.PAWN, new Coordinate(column, Row.SEVEN));
        }
    }

    @Override
    public String getName() {
        return "BLACKS";
    }
}
