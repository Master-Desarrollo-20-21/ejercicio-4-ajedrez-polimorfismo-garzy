package com.tangorabox.chess.move;

import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.piece.PieceColor;

import java.util.ArrayList;
import java.util.List;

public abstract class Move {

    private final Direction direction;
    private boolean canBeInverse = true;

    public Move(PieceColor pieceColor) {
        Direction directionAux = createDirection();
        if (pieceColor == PieceColor.BLACK) {
            directionAux = directionAux.inverse();
        }
        this.direction = directionAux;
    }

    public Move noInverse() {
        this.canBeInverse = false;
        return this;
    }

    public boolean isCanBeInverse() {
        return canBeInverse;
    }

    protected abstract Direction createDirection();

    public List<Rank> calculateCoordinates(Coordinate source, int maxMovementDistance) {
        List<Rank> result = new ArrayList<>();
        result.add(calculateCoordinates(source, maxMovementDistance, direction));
        if (canBeInverse) {
            result.add(calculateCoordinates(source, maxMovementDistance, direction.inverse()));
        }
        return result;
    }

    private Rank calculateCoordinates(Coordinate source, int maxMovementDistance, Direction direction) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 1; i <= maxMovementDistance; i++) {
            direction.calculateCoordinate(source, i).ifPresent(coordinates::add);
        }
        return new Rank(coordinates);
    }
}
