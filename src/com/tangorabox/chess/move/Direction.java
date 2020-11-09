package com.tangorabox.chess.move;

import com.tangorabox.chess.coordinate.Coordinate;

import java.util.Optional;

public class Direction {

    private final int column;
    private final int row;

    public Direction(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public Direction inverse() {
        return new Direction(column * -1, row * -1);
    }

    public Optional<Coordinate> calculateCoordinate(Coordinate source, int distance) {
        return source.add(column * distance, row * distance);
    }
}
