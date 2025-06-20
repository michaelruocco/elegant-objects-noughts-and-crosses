package uk.co.mruoc.nac.result;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.board.ReadOnlyBoard;

@RequiredArgsConstructor
public class Outcome {

    public Result decide(ReadOnlyBoard state) {
        var lines = new Lines(state.size());
        return lines.result(state);
    }
}
