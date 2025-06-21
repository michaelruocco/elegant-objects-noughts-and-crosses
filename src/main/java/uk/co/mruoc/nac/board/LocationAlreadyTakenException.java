package uk.co.mruoc.nac.board;

import uk.co.mruoc.nac.Location;
import uk.co.mruoc.nac.token.Token;

public class LocationAlreadyTakenException extends RuntimeException {

    public LocationAlreadyTakenException(Location location, Token token) {
        super(String.format("location %s already taken by token %s", location.toString(), token.value()));
    }
}
