package uk.co.mruoc.nac.board;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;

@RequiredArgsConstructor
@EqualsAndHashCode
public class FreeCoordinates {

    private final CoordinateMapping mapping;

    public FreeCoordinates() {
        this(Coordinates::new);
    }
}
