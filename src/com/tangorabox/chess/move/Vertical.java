package com.tangorabox.chess.move;

import com.tangorabox.chess.piece.PieceColor;

public class Vertical extends Move {

    public Vertical(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    protected Direction createDirection() {
        return new Direction(0, 1);
    }
}
