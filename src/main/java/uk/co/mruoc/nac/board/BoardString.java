package uk.co.mruoc.nac.board;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Locations;
import uk.co.mruoc.nac.token.Token;

@RequiredArgsConstructor
public class BoardString {

    private final int size;
    private final BoardTokens tokens;
    private final Locations locations;

    public BoardString(Board board) {
        this(board.size(), board, new Locations());
    }

    @Override
    public String toString() {
        return Stream.concat(Stream.of(header()), rows()).collect(Collectors.joining(System.lineSeparator()));
    }

    private String header() {
        var header = sizeIntStream().mapToObj(Integer::toString).collect(Collectors.joining(" "));
        return String.format("  %s", header);
    }

    private Stream<String> rows() {
        return sizeIntStream().mapToObj(this::row);
    }

    private String row(int y) {
        var rowId = Stream.of(Integer.toString(y));
        var rowTokens = sizeIntStream()
                .mapToObj(x -> locations.location(x, y))
                .map(tokens::token)
                .map(Token::value);
        return Stream.concat(rowId, rowTokens).collect(Collectors.joining(" "));
    }

    private IntStream sizeIntStream() {
        return IntStream.range(0, size);
    }
}
