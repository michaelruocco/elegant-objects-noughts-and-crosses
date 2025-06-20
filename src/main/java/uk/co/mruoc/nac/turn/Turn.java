package uk.co.mruoc.nac.turn;

import uk.co.mruoc.nac.board.Board;

import java.util.function.UnaryOperator;

public interface Turn extends UnaryOperator<Board> {
    @Override
    Board apply(Board board);
}
