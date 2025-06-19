package uk.co.mruoc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class PlayerToken implements Token {

    private final char value;

    public static Token X() {
        return new PlayerToken('X');
    }

    public static Token O() {
        return new PlayerToken('O');
    }

    @Override
    public boolean free() {
        return false;
    }

    @Override
    public String value() {
        return Character.toString(value);
    }
}
