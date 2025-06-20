package uk.co.mruoc.nac.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.board.ReadOnlyBoard;

@RequiredArgsConstructor
class Lines {

    private final int size;

    public Result result(ReadOnlyBoard state) {
        return lines().map(line -> result(state, line))
                .filter(Result::winner)
                .findFirst()
                .orElse(new StalemateResult());
    }

    // TODO refactor so these are not generated multiple times
    private Stream<Line> lines() {
        Collection<Line> lines = new ArrayList<>();
        lines.addAll(rows());
        lines.addAll(columns());
        lines.add(backSlashDiagonal());
        lines.add(forwardSlashDiagonal());
        return lines.stream();
    }

    private Collection<Line> rows() {
        Collection<Line> lines = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            Collection<Coordinates> coordinates = new ArrayList<>();
            for (int y = 0; y < size; y++) {
                coordinates.add(new Coordinates(x, y));
            }
            lines.add(new Line(coordinates));
        }
        return lines;
    }

    private Collection<Line> columns() {
        Collection<Line> lines = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            Collection<Coordinates> coordinates = new ArrayList<>();
            for (int x = 0; x < size; x++) {
                coordinates.add(new Coordinates(x, y));
            }
            lines.add(new Line(coordinates));
        }
        return lines;
    }

    private Line backSlashDiagonal() {
        Collection<Coordinates> coordinates = new ArrayList<>();
        var y = 0;
        var x = 0;
        do {
            coordinates.add(new Coordinates(x, y));
            y++;
            x++;
        } while (y < size && x < size);
        return new Line(coordinates);
    }

    private Line forwardSlashDiagonal() {
        Collection<Coordinates> coordinates = new ArrayList<>();
        int y = 0;
        int x = size - 1;
        do {
            coordinates.add(new Coordinates(x, y));
            y++;
            x--;
        } while (y <= size && x >= 0);
        return new Line(coordinates);
    }

    private Result result(ReadOnlyBoard state, Line line) {
        var lineTokens = line.coordinates().stream().map(state::token).collect(Collectors.toSet());
        if (lineTokens.size() != 1) {
            return new StalemateResult();
        }
        return lineTokens.stream()
                .findFirst()
                .filter(token -> !token.free())
                .map(token -> (Result) new WinnerResult(token, line))
                .orElse(new StalemateResult());
    }
}
