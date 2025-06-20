package uk.co.mruoc.nac.result;

import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.board.ReadOnlyBoard;

@RequiredArgsConstructor
public class Outcome {

    private final Function<Integer, Lines> mapping;

    public Outcome() {
        this(Lines::new);
    }

    public Result decide(ReadOnlyBoard state) {
        var lines = mapping.apply(state.size());
        return lines.result(state);
    }
}
