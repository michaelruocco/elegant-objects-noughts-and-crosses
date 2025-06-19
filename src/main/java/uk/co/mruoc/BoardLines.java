package uk.co.mruoc;

import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardLines {

    private final Collection<BoardLine> lines;

    public BoardResult result(BoardState tokens) {
        return lines.stream()
                .map(line -> line.result(tokens))
                .filter(BoardResult::winner)
                .findFirst()
                .orElse(new StalemateResult());
    }
}
