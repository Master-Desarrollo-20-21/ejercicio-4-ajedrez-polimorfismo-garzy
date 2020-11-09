package com.tangorabox.chess.coordinate;

public enum Row {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT;

    public static Row parse(char character) {
        try {
            int row = Integer.parseInt(String.valueOf(character));
            if (row > 0 && row <= Row.values().length) {
                return Row.values()[row - 1];
            }
            return null;
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(ordinal() + 1);
    }
}
