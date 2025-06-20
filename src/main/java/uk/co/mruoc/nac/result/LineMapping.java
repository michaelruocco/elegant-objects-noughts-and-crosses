package uk.co.mruoc.nac.result;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface LineMapping {

    default Collection<Line> lines(int size) {
        return Stream.concat(Stream.concat(rows(size), columns(size)), diagonals(size))
                .toList();
    }

    default Stream<Line> rows(int size) {
        return IntStream.range(0, size).mapToObj(y -> row(y, size));
    }

    Line row(int y, int size);

    default Stream<Line> columns(int size) {
        return IntStream.range(0, size).mapToObj(x -> column(x, size));
    }

    Line column(int x, int size);

    Stream<Line> diagonals(int size);
}
