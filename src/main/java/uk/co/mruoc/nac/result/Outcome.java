package uk.co.mruoc.nac.result;

import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.board.Board;

@RequiredArgsConstructor
public class Outcome {

    private final Function<Integer, Lines> mapping;

    public Outcome() {
        this(Lines::new);
    }

    public Result decide(Board board) {
        var lines = mapping.apply(board.size());
        return lines.result(board);
    }
}
