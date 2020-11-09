package com.tangorabox.chess.move;

import com.tangorabox.chess.piece.PieceColor;

public class OppositeDiagonal extends Move {

    public OppositeDiagonal(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    protected Direction createDirection() {
        return new Direction(-1, 1);
    }
}
