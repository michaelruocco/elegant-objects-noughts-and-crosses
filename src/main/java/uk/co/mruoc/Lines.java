package uk.co.mruoc;

import java.util.Collection;
import lombok.RequiredArgsConstructor;

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
