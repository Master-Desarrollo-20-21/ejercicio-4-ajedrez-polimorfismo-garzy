package com.tangorabox.chess.piece;

import com.tangorabox.chess.coordinate.Column;
import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.move.Diagonal;
import com.tangorabox.chess.move.Move;
import com.tangorabox.chess.move.OppositeDiagonal;

import java.util.Arrays;

public class Bishop extends Piece {
    
    protected Bishop(Coordinate initialCoordinate, PieceType pieceType, String representation, PieceColor pieceColor) {
        super(initialCoordinate, pieceType, representation, pieceColor);
    }

    @Override
    protected Iterable<Move> getMoves() {
        return Arrays.asList(new Diagonal(getColor()), new OppositeDiagonal(getColor()));
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
