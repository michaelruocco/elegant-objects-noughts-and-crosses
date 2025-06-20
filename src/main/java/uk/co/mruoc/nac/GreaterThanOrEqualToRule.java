package uk.co.mruoc.nac;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.board.Rule;

@RequiredArgsConstructor
public class GreaterThanOrEqualToRule implements Rule {

    private final long value;
    private final long minimum;
    private final RuntimeException exception;

    public GreaterThanOrEqualToRule(long value, long minimum, String message) {
        this(value, minimum, new IllegalArgumentException(message));
    }

    @Override
    public void validate() {
        if (value < minimum) {
            throw exception;
        }
    }
}
