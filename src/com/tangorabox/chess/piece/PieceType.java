package com.tangorabox.chess.piece;

public enum PieceType {

    KING(King.class, "♔", "♚"),
    QUEEN(Queen.class, "♕", "♛"),
    ROOK(Rook.class, "♖", "♜"),
    BISHOP(Bishop.class, "♗", "♝"),
    KNIGHT(Knight.class, "♘", "♞"),
    PAWN(Pawn.class, "♙", "♟");

    private final Class<? extends Piece> pieceClass;
    private final String white;
    private final String black;

    PieceType(Class<? extends Piece> pieceClass, String white, String black) {
        this.pieceClass = pieceClass;
        this.white = white;
        this.black = black;
    }

    public Class<? extends Piece> getPieceClass() {
        return pieceClass;
    }

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }
}
