package com.tangorabox.chess;

import com.tangorabox.chess.coordinate.Column;
import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.coordinate.Row;
import com.tangorabox.chess.move.Rank;
import com.tangorabox.chess.piece.Piece;
import com.tangorabox.chess.piece.PieceColor;

import java.util.*;
import java.util.stream.Collectors;

public class Board {

    private final Square[][] squares = new Square[Column.values().length][Row.values().length];

    private final Map<PieceColor, List<Piece>> colorPieces = new EnumMap<>(PieceColor.class);


    public Board() {
        initSquares();
    }

    private void initSquares() {
        for (Column column : Column.values()) {
            for (Row row : Row.values()) {
                squares[column.ordinal()][row.ordinal()] = Square.of(column, row);
            }
        }
    }

    public void add(Piece piece) {
        squares[piece.getCoordinate().getColumn()][piece.getCoordinate().getRow()].put(piece);
        getPiecesOfColor(piece.getColor()).add(piece);
    }

    private List<Piece> getPiecesOfColor(PieceColor pieceColor) {
        return colorPieces.computeIfAbsent(pieceColor, k -> new ArrayList<>());
    }

    public void remove(Piece piece) {
        squares[piece.getCoordinate().getColumn()][piece.getCoordinate().getRow()].clear();
        getPiecesOfColor(piece.getColor()).remove(piece);
    }

    public Square getSquare(Coordinate coordinate) {
        return squares[coordinate.getColumn()][coordinate.getRow()];
    }

    public Square getSquare(Column column, Row row) {
        return squares[column.ordinal()][row.ordinal()];
    }

    public Piece move(Piece piece, Coordinate target) {
        getSquare(piece.getCoordinate()).clear();
        Square square = getSquare(target);
        Piece capturedPiece = square.getPiece();
        square.put(piece);
        return capturedPiece;
    }

    public boolean isValidMovement(Piece piece, Coordinate coordinate) {
        Set<Coordinate> validCoordinates = new HashSet<>(getValidMovementCoordinates(piece));
        validCoordinates.addAll(getValidAttackCoordinates(piece));
        return validCoordinates.contains(coordinate);
    }

    public Collection<Coordinate> getValidMovementCoordinates(Piece piece) {
        if (piece.canJump()) {
            Set<Coordinate> allMovementCoordinates = piece.getAllMovementRanks().stream().flatMap(rank -> rank.getCoordinates().stream()).collect(Collectors.toSet());
            allMovementCoordinates.removeAll(getAllPieceCoordinates());
            return allMovementCoordinates;
        }

        return getNonBlockingCoordinates(piece, piece.getAllMovementRanks(), getAllPieceCoordinates());
    }

    public Collection<Coordinate> getValidAttackCoordinates(Piece piece) {
        if (piece.canJump()) {
            Set<Coordinate> allAttackCoordinates = piece.getAllAttackRanks().stream().flatMap(rank -> rank.getCoordinates().stream()).collect(Collectors.toSet());
            Set<Coordinate> targetPieces = getAllPieceCoordinatesOfAnotherColor(piece.getColor());
            targetPieces.retainAll(allAttackCoordinates);
            return targetPieces;
        }

        Set<Coordinate> attackCoordinates = getNonBlockingCoordinates(piece, piece.getAllAttackRanks(), getAllPieceCoordinatesOfColor(piece.getColor()));
        Set<Coordinate> targetPieces = getAllPieceCoordinatesOfAnotherColor(piece.getColor());
        targetPieces.retainAll(attackCoordinates);
        return targetPieces;
    }

    private Set<Coordinate> getNonBlockingCoordinates(Piece piece, List<Rank> ranks, Set<Coordinate> blockingCoordinates) {
        blockingCoordinates.remove(piece.getCoordinate());
        Set<Coordinate> result = new HashSet<>();
        for (Rank rank : ranks) {
            int index = getBlockedCoordinateIndex(rank, blockingCoordinates);
            result.addAll(rank.getCoordinates().subList(0, index));
        }
        return result;
    }

    private int getBlockedCoordinateIndex(Rank rank, Set<Coordinate> blockedCoordinates) {
        for (int i = 0; i < rank.getCoordinates().size(); i++) {
            if (blockedCoordinates.contains(rank.getCoordinates().get(i))) {
                return i;
            }
        }
        return rank.getCoordinates().size();
    }

    private Set<Coordinate> getAllPieceCoordinates() {
        Set<Coordinate> result = new HashSet<>();
        for (PieceColor pieceColor : colorPieces.keySet()) {
            result.addAll(getAllPieceCoordinatesOfColor(pieceColor));
        }
        return result;
    }

    private Set<Coordinate> getAllPieceCoordinatesOfColor(PieceColor pieceColor) {
        return colorPieces.get(pieceColor).stream().map(Piece::getCoordinate).collect(Collectors.toSet());
    }

    private Set<Coordinate> getAllPieceCoordinatesOfAnotherColor(PieceColor pieceColor) {
        return getAllPieceCoordinatesOfColor(pieceColor == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE);
    }
}
