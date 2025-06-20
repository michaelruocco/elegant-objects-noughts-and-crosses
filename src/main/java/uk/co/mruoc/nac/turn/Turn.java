package uk.co.mruoc.nac.turn;

import java.util.function.UnaryOperator;
import uk.co.mruoc.nac.board.Board;

public interface Turn extends UnaryOperator<Board> {
    @Override
    Board apply(Board board);
}
