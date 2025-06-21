package uk.co.mruoc.nac;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GreaterThanOrEqualToRule implements Rule {

    private final long value;
    private final long minimum;
    private final RuntimeException exception;

    @Override
    public void validate() {
        if (value < minimum) {
            throw exception;
        }
    }
}
