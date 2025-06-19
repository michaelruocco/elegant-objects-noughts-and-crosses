package uk.co.mruoc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class PlayerToken implements Token {

    private final char value;

    @Override
    public boolean free() {
        return false;
    }

    @Override
    public String value() {
        return Character.toString(value);
    }
}
