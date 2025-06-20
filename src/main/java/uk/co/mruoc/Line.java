package uk.co.mruoc;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Line {

    private final Collection<Coordinates> locations;

    public Line() {
        this(Collections.emptyList());
    }

    @Override
    public String toString() {
        return locations.stream().map(Coordinates::toString).collect(Collectors.joining(","));
    }

    public Result result(ReadOnlyState state) {
        var lineTokens = locations.stream().map(state::token).collect(Collectors.toSet());
        if (lineTokens.size() != 1) {
            return new StalemateResult();
        }
        return lineTokens.stream()
                .findFirst()
                .filter(token -> !token.free())
                .map(token -> (Result) new WinnerResult(token, this))
                .orElse(new StalemateResult());
    }
}
