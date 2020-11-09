package com.tangorabox.chess.piece;

import com.tangorabox.chess.coordinate.Column;
import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.move.Horizontal;
import com.tangorabox.chess.move.Move;
import com.tangorabox.chess.move.Vertical;

import java.util.Arrays;

public class Rook extends Piece {


    protected Rook(Coordinate initialCoordinate, PieceType pieceType, String representation, PieceColor pieceColor) {
        super(initialCoordinate, pieceType, representation, pieceColor);
    }

    @Override
    protected Iterable<Move> getMoves() {
        return Arrays.asList(new Horizontal(getColor()), new Vertical(getColor()));

    }

    @Override
    protected Iterable<Move> getAttackMoves() {
        return getMoves();
    }

    @Override
    protected int getMaxDistanceOfMovement() {
        return Column.values().length;
    }
}
