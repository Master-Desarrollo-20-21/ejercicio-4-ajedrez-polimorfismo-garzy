package com.tangorabox.chess;

import com.tangorabox.chess.coordinate.Column;
import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.coordinate.Row;
import com.tangorabox.chess.piece.Piece;

import java.util.Set;

public class Square {

    private final Coordinate coordinate;

    private Piece piece = null;

    public Square(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    public static Square of(Column column, Row row) {
        return new Square(new Coordinate(column, row));
    }

    public void put(Piece piece) {
        this.piece = piece;
        piece.move(this);
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public Piece getPiece() {
        return piece;
    }

    public String getRepresentation(Set<Coordinate> validMovementCoordinates, Set<Coordinate> validAttackCoordinates) {
        String result = getSelectionRepresentation(validMovementCoordinates, validAttackCoordinates);
        if (result != null) {
            return result;
        }
        if (isEmpty()) {
            return getEmptySquareRepresentation();
        }
        return String.format("\u2005\u2009%s\u2005\u2009", getPiece().getRepresentation());
    }

    private String getSelectionRepresentation(Set<Coordinate> validMovementCoordinates, Set<Coordinate> validAttackCoordinates) {
        if (isEmpty() && validMovementCoordinates.contains(getCoordinate())) {
            return " o ";
        }
        if (!isEmpty() && validAttackCoordinates.contains(getCoordinate())) {
            return String.format("<%s\u2005", getPiece().getRepresentation());
        }
        return null;
    }

    private String getEmptySquareRepresentation() {
        if ((coordinate.getRow() + coordinate.getColumn()) % 2 == 0) {
            return "░░░";
        }
        return "   ";
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void clear() {
        this.piece = null;
    }
}
