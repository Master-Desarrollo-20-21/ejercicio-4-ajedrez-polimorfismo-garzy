package com.tangorabox.chess.piece;

import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.move.Diagonal;
import com.tangorabox.chess.move.Move;
import com.tangorabox.chess.move.OppositeDiagonal;
import com.tangorabox.chess.move.Vertical;

import java.util.Arrays;
import java.util.Collections;

public class Pawn extends Piece {


    protected Pawn(Coordinate initialCoordinate, PieceType pieceType, String representation, PieceColor pieceColor) {
        super(initialCoordinate, pieceType, representation, pieceColor);
    }

    @Override
    protected Iterable<Move> getMoves() {
        return Collections.singletonList(new Vertical(getColor()).noInverse());
    }

    @Override
    protected Iterable<Move> getAttackMoves() {
        return Arrays.asList(new Diagonal(getColor()).noInverse(), new OppositeDiagonal(getColor()).noInverse());
    }

    @Override
    protected int getMaxDistanceOfMovement() {
        return hasMoved() ? 1 : 2;
    }
}
