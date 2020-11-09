package com.tangorabox.chess;

import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.piece.Piece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayerIO {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private final Board board;

    public PlayerIO(Board board) {
        this.board = board;
    }

    public Piece selectPiece(Player player) {
        assert player != null;
        Piece result = null;
        do {
            System.out.printf("(%s) Source Piece? ", player.getName());
            Coordinate coordinate = Coordinate.parse(readInput());
            if (coordinate == null) {
                System.out.println("Invalid Coordinate");
            } else {
                result = player.getPieceAtCoordinate(coordinate);
                if (result == null) {
                    System.out.println("This Square doesn't have a player Piece");
                }
            }
        } while (result == null);
        return result;
    }

    private String readInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    public Coordinate selectTargetOrCancel(Piece piece, Player player) {
        assert player != null;
        assert piece != null;

        Coordinate result = null;
        do {
            System.out.printf("(%s)[%s] Target Coordinate [(c)ancel]? ", player.getName(), piece);
            String userInput = readInput();
            if (userCancels(userInput)) {
                return null;
            }
            Coordinate coordinate = Coordinate.parse(userInput);
            if (coordinate == null) {
                System.out.println("Invalid Coordinate");
            } else {
                if (board.isValidMovement(piece, coordinate)) {
                    result = coordinate;
                } else {
                    System.out.printf("\t\t[%s] Cannot be moved to coordinate %s%n", piece, coordinate);
                }
            }
        } while (result == null);
        return result;
    }

    private boolean userCancels(String source) {
        return source.length() == 1 && source.charAt(0) == 'c';
    }
}
