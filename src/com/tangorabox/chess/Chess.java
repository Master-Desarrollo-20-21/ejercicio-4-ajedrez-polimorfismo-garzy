package com.tangorabox.chess;

import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.piece.BlackPieceFactory;
import com.tangorabox.chess.piece.Piece;
import com.tangorabox.chess.piece.PieceType;
import com.tangorabox.chess.piece.WhitePieceFactory;
import com.tangorabox.chess.view.BoardView;
import com.tangorabox.chess.view.BoardViewConsole;
import com.tangorabox.chess.view.StartView;

public class Chess {

    private final Board board;
    private final Turn turn;

    private final PlayerIO playerIO;

    private final BoardView boardView;
    private final StartView startView = new StartView();

    public Chess() {
        board = new Board();
        playerIO = new PlayerIO(board);
        Player whitePlayer = new WhitePlayer(board, new WhitePieceFactory());
        Player blackPlayer = new BlackPlayer(board, new BlackPieceFactory());
        turn = new Turn(whitePlayer, blackPlayer);
        whitePlayer.putPieces(board);
        blackPlayer.putPieces(board);
        boardView = new BoardViewConsole(board);
    }

    public void startGame() {
        startView.draw();
        boolean endOfGame = false;
        Piece capturedPiece = null;
        Player player;
        do {
            boardView.clearSelection();
            boardView.draw();
            player = turn.take();
            if (capturedPiece != null) {
                player.removePiece(capturedPiece);
            }
            capturedPiece = playerTurn(player);
            if (capturedPiece != null) {
                System.out.printf("Player %s Captured %s%n", player.getName(), capturedPiece);
                player.addCapturedPiece(capturedPiece);
                if (playerWins(capturedPiece)) {
                    System.out.printf("Player %s WINS!%n", player.getName());
                    endOfGame = true;
                }
            }
        } while (!endOfGame);
    }

    private boolean playerWins(Piece capturedPiece) {
        return capturedPiece.getPieceType() == PieceType.KING;
    }

    private Piece playerTurn(Player player) {
        Piece piece;
        Coordinate target;

        do {
            piece = playerIO.selectPiece(player);
            boardView.selectPiece(piece);
            target = playerIO.selectTargetOrCancel(piece, player);
        } while (target == null);

        return board.move(piece, target);
    }

    /**
     * Use "monospaced" font and font size 16
     */
    public static void main(String[] args) {
        new Chess().startGame();
    }
}
