package uk.co.mruoc.nac;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CoordinateMinimumValueRule implements Rule {

    private final Rule rule;

    public CoordinateMinimumValueRule(String axis, long value) {
        this(axis, value, 0);
    }

    public CoordinateMinimumValueRule(String axis, long value, long minimum) {
        this(new GreaterThanOrEqualToRule(value, minimum, new MinimumCoordinateValueException(axis, value, minimum)));
    }

    @Override
    public void validate() {
        rule.validate();
    }
}
