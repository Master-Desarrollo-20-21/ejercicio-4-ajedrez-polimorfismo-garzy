package com.tangorabox.chess.move;

import com.tangorabox.chess.coordinate.Coordinate;

import java.util.List;

public class Rank {

    private final List<Coordinate> coordinates;

    public Rank(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
}
