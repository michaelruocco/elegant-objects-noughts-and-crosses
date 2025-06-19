package uk.co.mruoc;

import java.util.function.UnaryOperator;

public interface Turn extends UnaryOperator<Board> {
    @Override
    Board apply(Board board);
}
