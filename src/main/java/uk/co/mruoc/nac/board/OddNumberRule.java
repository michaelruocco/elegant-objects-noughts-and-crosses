package uk.co.mruoc.nac.board;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Rule;

@RequiredArgsConstructor
public class OddNumberRule implements Rule {

    private final int value;
    private final RuntimeException exception;

    public OddNumberRule(int value) {
        this(value, new BoardSizeOddNumberException(value));
    }

    @Override
    public void validate() {
        if (even()) {
            throw exception;
        }
    }

    private boolean even() {
        return ((value % 2) == 0);
    }
}
