package com.tangorabox.chess.piece;

import com.tangorabox.chess.Square;
import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.move.Move;
import com.tangorabox.chess.move.Rank;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private final Coordinate initialCoordinate;
    private Coordinate coordinate;
    private final String representation;
    private final PieceColor pieceColor;
    private final PieceType pieceType;

    protected Piece(Coordinate initialCoordinate, PieceType pieceType, String representation, PieceColor pieceColor) {
        this.initialCoordinate = initialCoordinate;
        this.coordinate = initialCoordinate;
        this.representation = representation;
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public boolean hasMoved() {
        return !coordinate.equals(initialCoordinate);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    protected abstract Iterable<Move> getMoves();

    protected abstract Iterable<Move> getAttackMoves();

    protected abstract int getMaxDistanceOfMovement();

    public String getRepresentation() {
        return representation;
    }


    public List<Rank> getAllMovementRanks() {
        return calculateCoordinatesOfMoves(getMoves());
    }

    public List<Rank> getAllAttackRanks() {
        return calculateCoordinatesOfMoves(getAttackMoves());
    }

    private List<Rank> calculateCoordinatesOfMoves(Iterable<Move> moves) {
        List<Rank> result = new ArrayList<>();
        for (Move move : moves) {
            result.addAll(move.calculateCoordinates(getCoordinate(), getMaxDistanceOfMovement()));
        }
        return result;
    }

    @Override
    public String toString() {
        return representation;
    }

    public void move(Square square) {
        this.coordinate = square.getCoordinate();
    }

    public PieceColor getColor() {
        return pieceColor;
    }

    public boolean canJump() {
        return false;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
