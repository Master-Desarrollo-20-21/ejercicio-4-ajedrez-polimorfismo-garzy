package com.tangorabox.chess.piece;

public class BlackPieceFactory extends PieceFactory {
    @Override
    protected String getRepresentation(PieceType pieceType) {
        return pieceType.getBlack();
    }

    @Override
    protected PieceColor getColor() {
        return PieceColor.BLACK;
    }
}
