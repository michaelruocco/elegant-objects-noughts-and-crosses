package uk.co.mruoc;

import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardLines {

    private final Collection<BoardLine> lines;

    public BoardResult result(ReadOnlyBoardState state) {
        return lines.stream()
                .map(line -> line.result(state))
                .filter(BoardResult::winner)
                .findFirst()
                .orElse(new StalemateResult());
    }
}
