package com.tangorabox.chess.piece;

public class WhitePieceFactory extends PieceFactory {

    @Override
    protected String getRepresentation(PieceType pieceType) {
        return pieceType.getWhite();
    }

    @Override
    protected PieceColor getColor() {
        return PieceColor.WHITE;
    }
}
