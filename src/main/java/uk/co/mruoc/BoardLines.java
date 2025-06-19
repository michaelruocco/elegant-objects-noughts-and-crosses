package uk.co.mruoc;

import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardLines {

    private final Collection<BoardLine> lines;

    public BoardLines(BoardSize size) {
        this(lines(size));
    }

    public BoardResult result(BoardTokens tokens) {
        return lines.stream()
                .map(line -> line.result(tokens))
                .filter(BoardResult::winner)
                .findFirst()
                .orElse(new StalemateResult());
    }

    private static Collection<BoardLine> lines(BoardSize size) {
        Collection<BoardLine> lines = new ArrayList<>();
        lines.addAll(rows(size));
        lines.addAll(columns(size));
        lines.add(backSlashDiagonal(size));
        lines.add(forwardSlashDiagonal(size));
        return lines;
    }

    private static Collection<BoardLine> rows(BoardSize size) {
        Collection<BoardLine> lines = new ArrayList<>();
        for (int x = 0; x < size.value(); x++) {
            Collection<Coordinates> coordinates = new ArrayList<>();
            for (int y = 0; y < size.value(); y++) {
                coordinates.add(new Coordinates(x, y));
            }
            lines.add(new BoardLine(coordinates));
        }
        return lines;
    }

    private static Collection<BoardLine> columns(BoardSize size) {
        Collection<BoardLine> lines = new ArrayList<>();
        for (int y = 0; y < size.value(); y++) {
            Collection<Coordinates> coordinates = new ArrayList<>();
            for (int x = 0; x < size.value(); x++) {
                coordinates.add(new Coordinates(x, y));
            }
            lines.add(new BoardLine(coordinates));
        }
        return lines;
    }

    private static BoardLine backSlashDiagonal(BoardSize size) {
        Collection<Coordinates> coordinates = new ArrayList<>();
        var y = 0;
        var x = 0;
        do {
            coordinates.add(new Coordinates(x, y));
            y++;
            x++;
        } while (y < size.value() && x < size.value());
        return new BoardLine(coordinates);
    }

    private static BoardLine forwardSlashDiagonal(BoardSize size) {
        Collection<Coordinates> coordinates = new ArrayList<>();
        int y = 0;
        int x = size.value() - 1;
        do {
            coordinates.add(new Coordinates(x, y));
            y++;
            x--;
        } while (y <= size.value() && x >= 0);
        return new BoardLine(coordinates);
    }
}
