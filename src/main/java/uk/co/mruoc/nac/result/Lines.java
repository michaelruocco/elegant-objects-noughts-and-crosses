package uk.co.mruoc.nac.result;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.board.BoardTokens;

@RequiredArgsConstructor
class Lines {

    private final int size;
    private final LineMapping lineMapping; // TODO cache wrapper so only built once
    private final Result stalemate;
    private final WinnerMapping winnerMapping;

    public Lines(int size) {
        this(size, new DefaultLineMapping(), new StalemateResult(), WinnerResult::new);
    }

    public Result result(BoardTokens tokens) {
        return lineMapping.lines(size).stream()
                .map(line -> result(tokens, line))
                .filter(Result::winner)
                .findFirst()
                .orElse(stalemate);
    }

    private Result result(BoardTokens tokens, Line line) {
        var lineTokens = line.coordinates().stream().map(tokens::token).collect(Collectors.toSet());
        if (lineTokens.size() != 1) {
            return stalemate;
        }
        var token = lineTokens.stream().findFirst().orElseThrow();
        if (token.free()) {
            return stalemate;
        }
        return winnerMapping.winner(token, line);
    }
}
