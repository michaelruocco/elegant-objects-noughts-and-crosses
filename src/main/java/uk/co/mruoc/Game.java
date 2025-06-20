package uk.co.mruoc;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.board.Board;
import uk.co.mruoc.nac.board.State;
import uk.co.mruoc.nac.token.Players;
import uk.co.mruoc.nac.turn.Turns;

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

    public State boardState() {
        return board.state();
    }
}
