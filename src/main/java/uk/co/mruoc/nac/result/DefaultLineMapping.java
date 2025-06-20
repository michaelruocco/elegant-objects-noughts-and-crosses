package uk.co.mruoc.nac.result;

import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultLineMapping implements LineMapping {

    @Override
    public Line row(int y, int size) {
        return new Row(y, size);
    }

    @Override
    public Line column(int x, int size) {
        return new Column(x, size);
    }

    @Override
    public Stream<Line> diagonals(int size) {
        return Stream.of(new BackSlashDiagonal(size), new ForwardSlashDiagonal(size));
    }
}
