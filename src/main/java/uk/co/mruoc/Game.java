package uk.co.mruoc;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.board.Board;
import uk.co.mruoc.nac.result.Outcome;
import uk.co.mruoc.nac.token.Players;
import uk.co.mruoc.nac.turn.Turns;

@RequiredArgsConstructor
public class Game {

    private final Board board;
    private final Players players;
    private final Turns turns;
    private final Outcome outcome;

    public Game playTurn() {
        var nextTurn = turns.next(players.next());
        var updatedBoard = nextTurn.apply(board);
        return new Game(updatedBoard, players.switchNext(), turns, outcome);
    }

    public Board board() {
        return board;
    }

    public boolean playable() {
        return !board.full() && !outcome.decide(board).winner();
    }
}
