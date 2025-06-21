package uk.co.mruoc.nac.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Location;
import uk.co.mruoc.nac.Locations;

@RequiredArgsConstructor
public class Column implements Line {

    private final int x;
    private final int size;
    private final Locations locations;
    private final LineString lineString;

    public Column(int x, int size) {
        this(x, size, new Locations(), new LineString());
    }

    @Override
    public Collection<Location> coordinates() {
        Collection<Location> coordinates = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            coordinates.add(locations.location(x, y));
        }
        return Collections.unmodifiableCollection(coordinates);
    }

    @Override
    public String toString() {
        return lineString.apply(this);
    }
}
