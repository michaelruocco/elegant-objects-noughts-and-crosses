package uk.co.mruoc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Turn {

    private final Coordinates coordinates;
    private final Token token;

    public Turn(long x, long y, char token) {
        this(new Coordinates(x, y), new PlayerToken(token));
    }

    public Coordinates coordinates() {
        return coordinates;
    }

    public Token token() {
        return token;
    }
}
