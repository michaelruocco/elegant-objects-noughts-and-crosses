package uk.co.mruoc.nac;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Location {

    private final Coordinate x;
    private final Coordinate y;

    public Location(long x, long y) {
        this(new Coordinate("x", x), new Coordinate("y", y));
    }

    public void validate() {
        x.validate();
        y.validate();
    }

    public boolean withinBounds(int min, int max) {
        return x.withinBounds(min, max) && y.withinBounds(min, max);
    }

    @Override
    public String toString() {
        return String.format("%s|%s", x.toString(), y.toString());
    }
}
