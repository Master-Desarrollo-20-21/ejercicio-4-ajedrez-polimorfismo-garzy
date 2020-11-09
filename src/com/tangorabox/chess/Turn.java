package com.tangorabox.chess;

public class Turn {

    private final Player[] players;

    private int playerPointer = 0;

    public Turn(Player... players) {
        assert players != null;
        assert players.length >= 2 : "minimum 2 players";
        this.players = players;
    }

    public Player take() {
        Player result = players[playerPointer];
        playerPointer++;
        if (playerPointer >= players.length) {
            playerPointer = 0;
        }
        return result;
    }
}
