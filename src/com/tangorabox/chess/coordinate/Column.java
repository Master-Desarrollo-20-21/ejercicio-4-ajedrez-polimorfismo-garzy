package com.tangorabox.chess.coordinate;

public enum Column {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

    public static Column parse(char character) {
        try {
            return Column.valueOf(String.valueOf(character).toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
