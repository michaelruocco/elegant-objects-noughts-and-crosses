package uk.co.mruoc.nac.board;

import uk.co.mruoc.nac.Location;
import uk.co.mruoc.nac.token.Token;

public class LocationAlreadyTakenException extends RuntimeException {

    public LocationAlreadyTakenException(Token token, Location location) {
        super(String.format("location %s already contains token %s", location.toString(), token.value()));
    }
}
