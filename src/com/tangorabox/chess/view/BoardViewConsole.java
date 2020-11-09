package com.tangorabox.chess.view;

import com.tangorabox.chess.Board;
import com.tangorabox.chess.Square;
import com.tangorabox.chess.coordinate.Column;
import com.tangorabox.chess.coordinate.Coordinate;
import com.tangorabox.chess.coordinate.Row;
import com.tangorabox.chess.piece.Piece;

import java.util.HashSet;
import java.util.Set;

public class BoardViewConsole implements BoardView {

    private final Board board;
    private StringBuilder builder;

    private final Set<Coordinate> validMovementCoordinates = new HashSet<>();
    private final Set<Coordinate> validAttackCoordinates = new HashSet<>();

    public BoardViewConsole(Board board) {
        this.board = board;
    }

    @Override
    public void draw() {
        builder = new StringBuilder();
        drawColumnHeader();
        drawUpperBorder();
        for (int i = Row.values().length - 1; i >= 0; i--) {
            drawRowHeader(Row.values()[i], true);
            for (Column column : Column.values()) {
                drawSquare(board.getSquare(column, Row.values()[i]), column == Column.H);
            }
            drawRowHeader(Row.values()[i], false);
            newLine();
            if (!isLastRow(i)) {
                drawMiddleLine();
            }
        }
        drawLowerBorder();
        drawColumnHeader();
        System.out.println(builder.toString());
    }

    @Override
    public void selectPiece(Piece piece) {
        assert piece != null;
        validMovementCoordinates.clear();
        validAttackCoordinates.clear();

        validMovementCoordinates.addAll(board.getValidMovementCoordinates(piece));
        validAttackCoordinates.addAll(board.getValidAttackCoordinates(piece));
        draw();
    }

    @Override
    public void clearSelection() {
        validMovementCoordinates.clear();
        validAttackCoordinates.clear();
    }

    private void drawRowHeader(Row row, boolean leftHeader) {
        if (leftHeader) {
            builder.append(row).append(" ║");
        } else {
            builder.append(" ").append(row);
        }
    }


    private void drawColumnHeader() {
        builder.append("\u2002\u2002\u2002");
        for (Column value : Column.values()) {
            builder.append("\u2007\u2004");
            builder.append(value);
            builder.append("\u2007\u2004");
        }
        newLine();
    }

    private void newLine() {
        builder.append("\n");
    }

    private void drawSquare(Square square, boolean lastColumn) {

        builder.append(square.getRepresentation(validMovementCoordinates, validAttackCoordinates));
        if (lastColumn) {
            builder.append("║");
        } else {
            builder.append("│");
        }
    }

    private void drawUpperBorder() {
        builder.append("  ");
        builder.append("╔");
        for (int i = 0; i < Column.values().length; i++) {
            builder.append("═══");
            if (!isLastColumn(i)) {
                builder.append("╤");
            }
        }
        builder.append("╗");
        newLine();
    }

    private void drawLowerBorder() {
        builder.append("  ");
        builder.append("╚");
        for (int i = 0; i < Column.values().length; i++) {
            builder.append("═══");
            if (!isLastColumn(i)) {
                builder.append("╧");
            }
        }
        builder.append("╝");
        newLine();
    }

    private void drawMiddleLine() {
        builder.append("  ");
        builder.append("╟");
        for (int i = 0; i < Column.values().length; i++) {
            builder.append("───");
            if (isLastColumn(i)) {
                builder.append("╢");
            } else {
                builder.append("┼");
            }
        }
        newLine();
    }

    private boolean isLastColumn(int i) {
        return i == Column.values().length - 1;
    }

    private boolean isLastRow(int i) {
        return i == 0;
    }
}
