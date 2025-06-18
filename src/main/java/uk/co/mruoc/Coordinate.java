package uk.co.mruoc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Coordinate {

    private final String axis;
    private final long value;

    public void validate() {
        if (!valid()) {
            throw new IllegalStateException(String.format("invalid %s axis coordinate value %d", axis, value));
        }
    }

    public boolean valid() {
        return value > -1;
    }

    public String id() {
        return String.format("%s:%d", axis, value);
    }
}
