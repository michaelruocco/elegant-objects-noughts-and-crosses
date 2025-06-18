package uk.co.mruoc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class BoardStateString {

    private final Board board;

    @Override
    public String toString() {
        int size = board.size();
        Collection<String> rows = new ArrayList<>();
        rows.add(buildHeader(size));
        for (int y = 0; y < size; y++) {
            rows.add(buildRow(board, size, y));
        }
        return rows.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    private static String buildHeader(int size) {
        var header = IntStream.range(0, size).mapToObj(Integer::toString).collect(Collectors.joining(" "));
        return String.format("  %s", header);
    }

    private static String buildRow(Board board, int size, int y) {
        Collection<String> tokens = new ArrayList<>();
        tokens.add(Integer.toString(y));
        for (int x = 0; x < size; x++) {
            tokens.add(board.token(new Coordinates(x, y)).value());
        }
        return String.join(" ", tokens);
    }
}
