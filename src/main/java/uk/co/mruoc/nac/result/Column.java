package uk.co.mruoc.nac.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.board.CoordinateMapping;

@RequiredArgsConstructor
public class Column implements Line {

    private final int x;
    private final int size;
    private final CoordinateMapping mapping;
    private final LineString lineString;

    public Column(int x, int size) {
        this(x, size, Coordinates::new, new LineString());
    }

    @Override
    public Collection<Coordinates> coordinates() {
        Collection<Coordinates> coordinates = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            coordinates.add(mapping.map(x, y));
        }
        return Collections.unmodifiableCollection(coordinates);
    }

    @Override
    public String toString() {
        return lineString.apply(this);
    }
}
