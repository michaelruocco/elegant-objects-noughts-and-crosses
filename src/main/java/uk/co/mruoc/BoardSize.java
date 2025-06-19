package uk.co.mruoc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class BoardSize {

    private final int value;

    public BoardSize() {
        this(3);
    }

    public int value() {
        return value;
    }

    public void validate() {
        if (value < 3) {
            throw new IllegalArgumentException(String.format("board size %d cannot be less than 3", value));
        }
        if (even()) {
            throw new IllegalArgumentException(String.format("board size %d cannot be even", value));
        }
    }

    public Collection<BoardLine> lines() {
        Collection<BoardLine> lines = new ArrayList<>();
        lines.addAll(rows());
        lines.addAll(columns());
        lines.add(backSlashDiagonal());
        lines.add(forwardSlashDiagonal());
        return Collections.unmodifiableCollection(lines);
    }

    private boolean even() {
        return ((value % 2) == 0);
    }

    private Collection<BoardLine> rows() {
        Collection<BoardLine> lines = new ArrayList<>();
        for (int x = 0; x < value; x++) {
            Collection<Coordinates> coordinates = new ArrayList<>();
            for (int y = 0; y < value; y++) {
                coordinates.add(new Coordinates(x, y));
            }
            lines.add(new BoardLine(coordinates));
        }
        return lines;
    }

    private Collection<BoardLine> columns() {
        Collection<BoardLine> lines = new ArrayList<>();
        for (int y = 0; y < value; y++) {
            Collection<Coordinates> coordinates = new ArrayList<>();
            for (int x = 0; x < value; x++) {
                coordinates.add(new Coordinates(x, y));
            }
            lines.add(new BoardLine(coordinates));
        }
        return lines;
    }

    private BoardLine backSlashDiagonal() {
        Collection<Coordinates> coordinates = new ArrayList<>();
        var y = 0;
        var x = 0;
        do {
            coordinates.add(new Coordinates(x, y));
            y++;
            x++;
        } while (y < value && x < value);
        return new BoardLine(coordinates);
    }

    private BoardLine forwardSlashDiagonal() {
        Collection<Coordinates> coordinates = new ArrayList<>();
        int y = 0;
        int x = value - 1;
        do {
            coordinates.add(new Coordinates(x, y));
            y++;
            x--;
        } while (y <= value && x >= 0);
        return new BoardLine(coordinates);
    }
}
