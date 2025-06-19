package uk.co.mruoc;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Game {

    private final Board board;
    private final Players players;
    private final Turns turns;

    public Game playTurn() {
        var nextTurn = turns.next(players.next());
        var updatedBoard = nextTurn.apply(board);
        return new Game(updatedBoard, players.switchNext(), turns);
    }

    public boolean playable() {
        return board.playable();
    }

    public BoardState boardState() {
        return board.state();
    }
}
