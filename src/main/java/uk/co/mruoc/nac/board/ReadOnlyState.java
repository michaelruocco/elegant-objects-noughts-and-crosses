package uk.co.mruoc.nac.board;

import java.util.Collection;
import uk.co.mruoc.nac.token.Token;

public interface ReadOnlyState {

    int size();

    default Token token(long x, long y) {
        return token(new Coordinates(x, y));
    }

    Token token(Coordinates coordinates);

    boolean full();

    Collection<Coordinates> freeCoordinates();
}
