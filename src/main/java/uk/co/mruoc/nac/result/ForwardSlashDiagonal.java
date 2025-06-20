package uk.co.mruoc.nac.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.board.CoordinateMapping;

@RequiredArgsConstructor
public class ForwardSlashDiagonal implements Line {

    private final int size;
    private final CoordinateMapping mapping;
    private final LineString lineString;

    public ForwardSlashDiagonal(int size) {
        this(size, Coordinates::new, new LineString());
    }

    @Override
    public Collection<Coordinates> coordinates() {
        Collection<Coordinates> coordinates = new ArrayList<>();
        int y = 0;
        int x = size - 1;
        do {
            coordinates.add(mapping.map(x, y));
            y++;
            x--;
        } while (y <= size && x >= 0);
        return Collections.unmodifiableCollection(coordinates);
    }

    @Override
    public String toString() {
        return lineString.apply(this);
    }
}
