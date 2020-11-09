package com.tangorabox.chess.view;

import com.tangorabox.chess.piece.Piece;

public interface BoardView {

    void draw();

    void selectPiece(Piece piece);

    void clearSelection();
}
