package uk.co.mruoc.nac.result;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;

@RequiredArgsConstructor
public class Line {

    private final Collection<Coordinates> coordinates;

    public Line() {
        this(Collections.emptyList());
    }

    public Collection<Coordinates> coordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return coordinates.stream().map(Coordinates::toString).collect(Collectors.joining(","));
    }
}
