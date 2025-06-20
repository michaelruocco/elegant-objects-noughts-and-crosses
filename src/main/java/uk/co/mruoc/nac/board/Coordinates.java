package uk.co.mruoc.nac.board;

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

    @Override
    public String toString() {
        return String.format("%s-%s", x.toString(), y.toString());
    }
}
