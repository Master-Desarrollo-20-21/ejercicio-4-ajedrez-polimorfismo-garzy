package com.tangorabox.chess;

import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.piece.Piece;
import com.tangorabox.chess.piece.PieceFactory;
import com.tangorabox.chess.piece.PieceType;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private final List<Piece> pieces = new ArrayList<>();
    private final List<Piece> capturedPieces = new ArrayList<>();

    private final PieceFactory pieceFactory;
    private final Board board;

    public Player(Board board, PieceFactory pieceFactory) {
        this.pieceFactory = pieceFactory;
        this.board = board;
    }

    protected void put(PieceType pieceType, Coordinate coordinate) {
        Piece piece = pieceFactory.create(pieceType, coordinate);
        pieces.add(piece);
        board.add(piece);
    }

    public abstract void putPieces(Board board);

    public Piece getPieceAtCoordinate(Coordinate coordinate) {
        assert coordinate != null;
        for (Piece piece : pieces) {
            if (piece.getCoordinate().equals(coordinate)) {
                return piece;
            }
        }
        return null;
    }

    public abstract String getName();

    public void addCapturedPiece(Piece capturedPiece) {
        capturedPieces.add(capturedPiece);
    }

    public void removePiece(Piece capturedPiece) {
        pieces.remove(capturedPiece);
    }

    public List<Piece> getCapturedPieces() {
        return capturedPieces;
    }
}
