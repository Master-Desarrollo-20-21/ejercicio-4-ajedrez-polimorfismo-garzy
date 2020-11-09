package com.tangorabox.chess;

import com.tangorabox.chess.coordinate.Column;
import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.coordinate.Row;
import com.tangorabox.chess.piece.PieceFactory;
import com.tangorabox.chess.piece.PieceType;

public class WhitePlayer extends Player {


    public WhitePlayer(Board board, PieceFactory pieceFactory) {
        super(board, pieceFactory);
    }

    @Override
    public void putPieces(Board board) {
        put(PieceType.KING, new Coordinate(Column.E, Row.ONE));
        put(PieceType.QUEEN, new Coordinate(Column.D, Row.ONE));
        put(PieceType.BISHOP, new Coordinate(Column.C, Row.ONE));
        put(PieceType.BISHOP, new Coordinate(Column.F, Row.ONE));
        put(PieceType.KNIGHT, new Coordinate(Column.G, Row.ONE));
        put(PieceType.KNIGHT, new Coordinate(Column.B, Row.ONE));
        put(PieceType.ROOK, new Coordinate(Column.H, Row.ONE));
        put(PieceType.ROOK, new Coordinate(Column.A, Row.ONE));

        for (Column column : Column.values()) {
            put(PieceType.PAWN, new Coordinate(column, Row.TWO));
        }
    }

    @Override
    public String getName() {
        return "WHITES";
    }
}
