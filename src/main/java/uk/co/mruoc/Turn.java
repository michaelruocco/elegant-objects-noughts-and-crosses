package uk.co.mruoc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Turn {

    private final Coordinates coordinates;
    private final Token token;

    public Turn(long x, long y, Token token) {
        this(new Coordinates(x, y), token);
    }

    public Coordinates coordinates() {
        return coordinates;
    }

    public Token token() {
        return token;
    }
}
