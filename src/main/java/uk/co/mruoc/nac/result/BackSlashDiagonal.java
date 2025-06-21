package uk.co.mruoc.nac.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Location;

@RequiredArgsConstructor
public class BackSlashDiagonal implements Line {

    private final int size;
    private final LineString lineString;

    public BackSlashDiagonal(int size) {
        this(size, new LineString());
    }

    @Override
    public Collection<Location> coordinates() {
        Collection<Location> coordinates = new ArrayList<>();
        var i = 0;
        do {
            coordinates.add(new Location(i, i));
            i++;
        } while (i < size);
        return Collections.unmodifiableCollection(coordinates);
    }

    @Override
    public String toString() {
        return lineString.apply(this);
    }
}
