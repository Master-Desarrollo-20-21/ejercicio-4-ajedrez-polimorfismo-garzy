package com.tangorabox.chess.coordinate;

import java.util.Objects;
import java.util.Optional;

public class Coordinate {

    private final int column;
    private final int row;


    public Coordinate(Column column, Row row) {
        this(column.ordinal(), row.ordinal());
    }

    public Coordinate(int column, int row) {
        assert column > 0 && column < Column.values().length;
        assert row > 0 && row < Row.values().length;
        this.column = column;
        this.row = row;
    }

    public static Coordinate parse(String source) {
        if (source == null || source.length() < 2) {
            return null;
        }

        Column column = Column.parse(source.charAt(0));
        Row row = Row.parse(source.charAt(1));

        if (column == null || row == null) {
            return null;
        }
        
        return new Coordinate(column, row);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Optional<Coordinate> add(int columnAdd, int rowAdd) {
        int columnOrdinal = this.column + columnAdd;
        int rowOrdinal = this.row + rowAdd;
        if (isValidColumn(columnOrdinal) && isValidRow(rowOrdinal)) {
            return Optional.of(new Coordinate(Column.values()[columnOrdinal], Row.values()[rowOrdinal]));
        }
        return Optional.empty();
    }

    public Optional<Coordinate> add(Coordinate coordinate) {
        return add(coordinate.getColumn(), coordinate.getRow());
    }

    private boolean isValidColumn(int columnOrdinal) {
        return columnOrdinal >= 0 && columnOrdinal < Column.values().length;
    }

    private boolean isValidRow(int rowOrdinal) {
        return rowOrdinal >= 0 && rowOrdinal < Row.values().length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return getColumn() == that.getColumn() &&
                getRow() == that.getRow();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn(), getRow());
    }

    @Override
    public String toString() {
        return String.format("[%s%s]", Column.values()[column], Row.values()[row]);
    }
}
