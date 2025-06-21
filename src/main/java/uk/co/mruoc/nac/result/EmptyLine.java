package uk.co.mruoc.nac.result;

import java.util.Collection;
import java.util.Collections;
import uk.co.mruoc.nac.Location;

public class EmptyLine implements Line {

    @Override
    public Collection<Location> coordinates() {
        return Collections.emptySet();
    }

    @Override
    public String toString() {
        return "";
    }
}
