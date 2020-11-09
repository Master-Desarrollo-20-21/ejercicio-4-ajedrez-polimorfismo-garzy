package com.tangorabox.chess.move;

import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.piece.PieceColor;

import java.util.*;

public class KnightJump extends Move {

    private static final List<Coordinate> VALID_MOVES = Arrays.asList(
            new Coordinate(1, 2),
            new Coordinate(2, 1),
            new Coordinate(2, -1),
            new Coordinate(1, -2),
            new Coordinate(-1, -2),
            new Coordinate(-2, -1),
            new Coordinate(-2, 1),
            new Coordinate(-1, 2)
    );

    public KnightJump(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    protected Direction createDirection() {
        return null;
    }

    @Override
    public List<Rank> calculateCoordinates(Coordinate source, int maxMovementDistance) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (Coordinate validMove : VALID_MOVES) {
            Optional<Coordinate> target = source.add(validMove);
            target.ifPresent(coordinates::add);
        }
        return Collections.singletonList(new Rank(coordinates));
    }
}
