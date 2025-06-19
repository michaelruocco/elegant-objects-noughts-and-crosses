package uk.co.mruoc;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RunnableGame {

    private final Game game;

    public Game run() {
        var currentGame = game;
        while (currentGame.playable()) {
            currentGame = currentGame.playTurn();
            // System.out.println(new BoardStateString(currentGame.boardState()));
            // System.out.println();
        }
        return currentGame;
    }
}
