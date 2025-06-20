package uk.co.mruoc.nac.board;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OddNumberRule implements Rule {

    private final int value;
    private final RuntimeException exception;

    public OddNumberRule(int value) {
        this(value, "board size %d must be an odd number");
    }

    public OddNumberRule(int value, String messageFormat) {
        this(value, new IllegalArgumentException(String.format(messageFormat, value)));
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
