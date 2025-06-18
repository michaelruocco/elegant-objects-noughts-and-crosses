package uk.co.mruoc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Coordinates {

    private final Coordinate x;
    private final Coordinate y;

    public Coordinates(long x, long y) {
        this(new Coordinate("x", x), new Coordinate("y", y));
    }

    public void validate() {
        x.validate();
        y.validate();
    }

    public String id() {
        return String.format("%s-%s", x.id(), y.id());
    }
}
