package com.tangorabox.chess.piece;

import com.tangorabox.chess.coordinate.Coordinate;

public abstract class PieceFactory {

    public Piece create(PieceType pieceType, Coordinate coordinate) {
        try {
            return pieceType.getPieceClass()
                    .getDeclaredConstructor(Coordinate.class, PieceType.class, String.class, PieceColor.class)
                    .newInstance(coordinate, pieceType, getRepresentation(pieceType), getColor());
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    protected abstract String getRepresentation(PieceType pieceType);

    protected abstract PieceColor getColor();

}
