package uk.co.mruoc.nac.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Location;
import uk.co.mruoc.nac.Locations;

@RequiredArgsConstructor
public class Row implements Line {

    private final int y;
    private final int size;
    private final Locations locations;
    private final LineString lineString;

    public Row(int y, int size) {
        this(y, size, new Locations(), new LineString());
    }

    @Override
    public Collection<Location> coordinates() {
        Collection<Location> coordinates = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            coordinates.add(locations.location(x, y));
        }
        return Collections.unmodifiableCollection(coordinates);
    }

    @Override
    public String toString() {
        return lineString.apply(this);
    }
}
