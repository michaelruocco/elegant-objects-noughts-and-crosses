package uk.co.mruoc.nac.board;

import java.util.Collection;
import uk.co.mruoc.nac.Coordinates;

public interface ReadOnlyBoard extends BoardTokens {

    int size();

    boolean playable();

    Collection<Coordinates> freeCoordinates();
}
