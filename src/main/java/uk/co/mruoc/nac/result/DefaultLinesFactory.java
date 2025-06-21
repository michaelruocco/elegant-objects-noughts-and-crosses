package uk.co.mruoc.nac.result;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultLinesFactory implements LinesFactory {

    private final int size;

    @Override
    public Collection<Line> build() {
        return Stream.concat(Stream.concat(rows(), columns()), diagonals()).toList();
    }

    private Stream<Line> rows() {
        return IntStream.range(0, size).mapToObj(this::row);
    }

    private Line row(int y) {
        return new Row(y, size);
    }

    private Stream<Line> columns() {
        return IntStream.range(0, size).mapToObj(this::column);
    }

    private Line column(int x) {
        return new Column(x, size);
    }

    private Stream<Line> diagonals() {
        return Stream.of(new BackSlashDiagonal(size), new ForwardSlashDiagonal(size));
    }
}
