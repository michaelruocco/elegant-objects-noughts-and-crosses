package uk.co.mruoc.nac;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.board.Rule;

@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "rule")
public class Coordinate {

    private final String axis;
    private final long value;
    private final Rule rule;

    public Coordinate(String axis, long value) {
        this(axis, value, new CoordinateMinimumValueRule(axis, value));
    }

    public void validate() {
        rule.validate();
    }

    @Override
    public String toString() {
        return String.format("%s:%d", axis, value);
    }
}
