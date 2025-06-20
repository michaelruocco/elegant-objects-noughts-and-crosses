package uk.co.mruoc.nac.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.board.CoordinateMapping;

@RequiredArgsConstructor
public class Row implements Line {

    private final int y;
    private final int size;
    private final CoordinateMapping mapping;
    private final LineString lineString;

    public Row(int y, int size) {
        this(y, size, Coordinates::new, new LineString());
    }

    @Override
    public Collection<Coordinates> coordinates() {
        Collection<Coordinates> coordinates = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            coordinates.add(mapping.map(x, y));
        }
        return Collections.unmodifiableCollection(coordinates);
    }

    @Override
    public String toString() {
        return lineString.apply(this);
    }
}
