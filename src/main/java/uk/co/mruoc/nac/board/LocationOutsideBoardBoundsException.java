package uk.co.mruoc.nac.board;

import uk.co.mruoc.nac.Location;

public class LocationOutsideBoardBoundsException extends RuntimeException {

    public LocationOutsideBoardBoundsException(Location location, int maximumValue) {
        super(String.format(
                "location %s outside board bounds, coordinate values must be between 0 and %d",
                location, maximumValue));
    }
}
