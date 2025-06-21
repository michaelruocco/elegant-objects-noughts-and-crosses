package uk.co.mruoc.nac.result;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.board.Board;

@RequiredArgsConstructor
class Lines {

    private final LinesFactory linesFactory;
    private final Result stalemate;

    public Lines(int size) {
        this(new CachedLinesFactory(size), new StalemateResult());
    }

    public Result result(Board board) {
        return linesFactory.build().stream()
                .map(line -> result(board, line))
                .filter(Result::winner)
                .findFirst()
                .orElse(stalemate);
    }

    private Result result(Board board, Line line) {
        var lineTokens = line.coordinates().stream().map(board::token).collect(Collectors.toSet());
        if (lineTokens.size() != 1) {
            return stalemate;
        }
        var token = lineTokens.stream().findFirst().orElseThrow();
        if (token.free()) {
            return stalemate;
        }
        return new WinnerResult(token, line);
    }
}
