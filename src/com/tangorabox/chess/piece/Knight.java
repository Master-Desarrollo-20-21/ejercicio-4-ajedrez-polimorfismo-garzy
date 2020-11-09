package com.tangorabox.chess.piece;

import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.move.KnightJump;
import com.tangorabox.chess.move.Move;

import java.util.Collections;

public class Knight extends Piece {


    protected Knight(Coordinate initialCoordinate, PieceType pieceType, String representation, PieceColor pieceColor) {
        super(initialCoordinate, pieceType, representation, pieceColor);
    }

    @Override
    protected Iterable<Move> getMoves() {
        return Collections.singletonList(new KnightJump(getColor()));
    }

    @Override
    protected Iterable<Move> getAttackMoves() {
        return getMoves();
    }

    @Override
    protected int getMaxDistanceOfMovement() {
        return 0; //not necessary
    }

    @Override
    public boolean canJump() {
        return true;
    }
}
