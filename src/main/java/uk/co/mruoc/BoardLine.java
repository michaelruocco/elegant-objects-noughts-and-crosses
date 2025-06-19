package uk.co.mruoc;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardLine {

    private final Collection<Coordinates> locations;

    public BoardLine() {
        this(Collections.emptyList());
    }

    @Override
    public String toString() {
        return locations.stream().map(Coordinates::toString).collect(Collectors.joining(","));
    }

    public BoardResult result(ReadOnlyBoardState state) {
        var lineTokens = locations.stream().map(state::token).collect(Collectors.toSet());
        if (lineTokens.size() != 1) {
            return new StalemateResult(this);
        }
        return lineTokens.stream()
                .findFirst()
                .filter(token -> !token.free())
                .map(token -> new BoardResult(token, this))
                .orElse(new StalemateResult(this));
    }
}
