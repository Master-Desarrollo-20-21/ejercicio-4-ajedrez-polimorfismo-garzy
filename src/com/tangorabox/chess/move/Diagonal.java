package com.tangorabox.chess.move;

import com.tangorabox.chess.piece.PieceColor;

public class Diagonal extends Move {

    public Diagonal(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    protected Direction createDirection() {
        return new Direction(1, 1);
    }
}
