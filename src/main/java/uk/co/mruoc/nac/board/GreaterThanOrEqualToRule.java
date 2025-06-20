package uk.co.mruoc.nac.board;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GreaterThanOrEqualToRule implements Rule {

    private final int value;
    private final int minimum;
    private final RuntimeException exception;

    public GreaterThanOrEqualToRule(int value, int minimum) {
        this(value, minimum, "board size %d must be greater than or equal to %d");
    }

    public GreaterThanOrEqualToRule(int value, int minimum, String messageFormat) {
        this(value, minimum, new IllegalArgumentException(String.format(messageFormat, value, minimum)));
    }

    @Override
    public void validate() {
        if (value < minimum) {
            throw exception;
        }
    }
}
