package com.tangorabox.chess.move;

import com.tangorabox.chess.piece.PieceColor;

public class Horizontal extends Move {

    public Horizontal(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    protected Direction createDirection() {
        return new Direction(1, 0);
    }
}
