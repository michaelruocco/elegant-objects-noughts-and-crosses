package uk.co.mruoc.nac.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Location;
import uk.co.mruoc.nac.Locations;

@RequiredArgsConstructor
public class ForwardSlashDiagonal implements Line {

    private final int size;
    private final Locations locations;
    private final LineString lineString;

    public ForwardSlashDiagonal(int size) {
        this(size, new Locations(), new LineString());
    }

    @Override
    public Collection<Location> coordinates() {
        Collection<Location> coordinates = new ArrayList<>();
        int y = 0;
        int x = size - 1;
        do {
            coordinates.add(locations.location(x, y));
            y++;
            x--;
        } while (x >= 0);
        return Collections.unmodifiableCollection(coordinates);
    }

    @Override
    public String toString() {
        return lineString.apply(this);
    }
}
