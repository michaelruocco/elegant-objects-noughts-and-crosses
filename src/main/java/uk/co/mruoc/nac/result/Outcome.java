package uk.co.mruoc.nac.result;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.board.ReadOnlyState;

@RequiredArgsConstructor
public class Outcome {

    public Result decide(ReadOnlyState state) {
        var lines = new Lines(state.size());
        return lines.result(state);
    }
}
