package uk.co.mruoc.nac.board;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.result.Result;
import uk.co.mruoc.nac.result.StalemateResult;

@RequiredArgsConstructor
public class Lines {

    private final Collection<Line> lines;

    public Result result(ReadOnlyState state) {
        return lines.stream()
                .map(line -> line.result(state))
                .filter(Result::winner)
                .findFirst()
                .orElse(new StalemateResult());
    }
}
