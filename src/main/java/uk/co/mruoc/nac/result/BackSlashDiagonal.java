package uk.co.mruoc.nac.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.board.CoordinateMapping;

@RequiredArgsConstructor
public class BackSlashDiagonal implements Line {

    private final int size;
    private final CoordinateMapping mapping;
    private final LineString lineString;

    public BackSlashDiagonal(int size) {
        this(size, Coordinates::new, new LineString());
    }

    @Override
    public Collection<Coordinates> coordinates() {
        Collection<Coordinates> coordinates = new ArrayList<>();
        var i = 0;
        do {
            coordinates.add(mapping.map(i, i));
            i++;
        } while (i < size);
        return Collections.unmodifiableCollection(coordinates);
    }

    @Override
    public String toString() {
        return lineString.apply(this);
    }
}
