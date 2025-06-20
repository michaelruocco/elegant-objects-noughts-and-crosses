package uk.co.mruoc.nac.result;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.board.BoardTokens;

@RequiredArgsConstructor
class Lines {

    private final Collection<Line> lines;

    public Lines(int size) {
        this(Stream.concat(
                        Stream.concat(rows(size), columns(size)),
                        Stream.of(new BackSlashDiagonal(size), new ForwardSlashDiagonal(size)))
                .toList());
    }

    public Result result(BoardTokens tokens) {
        return lines.stream()
                .map(line -> result(tokens, line))
                .filter(Result::winner)
                .findFirst()
                .orElse(new StalemateResult());
    }

    private static Stream<Line> rows(int size) {
        return IntStream.range(0, size).mapToObj(y -> new Row(y, size));
    }

    private static Stream<Line> columns(int size) {
        return IntStream.range(0, size).mapToObj(x -> new Column(x, size));
    }

    private Result result(BoardTokens tokens, Line line) {
        var lineTokens = line.coordinates().stream().map(tokens::token).collect(Collectors.toSet());
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
