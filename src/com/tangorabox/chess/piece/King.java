package com.tangorabox.chess.piece;

import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.move.*;

import java.util.Arrays;

public class King extends Piece {


    protected King(Coordinate initialCoordinate, PieceType pieceType, String representation, PieceColor pieceColor) {
        super(initialCoordinate, pieceType, representation, pieceColor);
    }

    @Override
    protected Iterable<Move> getMoves() {
        return Arrays.asList(new Diagonal(getColor()), new OppositeDiagonal(getColor()), new Vertical(getColor()), new Horizontal(getColor()));
    }

    @Override
    protected Iterable<Move> getAttackMoves() {
        return getMoves();
    }

    @Override
    protected int getMaxDistanceOfMovement() {
        return 1;
    }
}
