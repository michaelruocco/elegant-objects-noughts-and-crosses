package uk.co.mruoc.nac.result;

import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;

@RequiredArgsConstructor
public class LineString implements Function<Line, String> {

    @Override
    public String apply(Line line) {
        return line.coordinates().stream().map(Coordinates::toString).collect(Collectors.joining(","));
    }
}
