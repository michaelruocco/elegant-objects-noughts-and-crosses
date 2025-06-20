package uk.co.mruoc.nac;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.board.Rule;

@RequiredArgsConstructor
public class CoordinateMinimumValueRule implements Rule {

    private final Rule rule;

    public CoordinateMinimumValueRule(String axis, long value) {
        this(axis, value, 0);
    }

    public CoordinateMinimumValueRule(String axis, long value, long minimum) {
        this(new GreaterThanOrEqualToRule(
                value,
                minimum,
                String.format(
                        "invalid %s axis coordinate %d must be greater than or equal to %d", axis, value, minimum)));
    }

    @Override
    public void validate() {
        rule.validate();
    }
}
