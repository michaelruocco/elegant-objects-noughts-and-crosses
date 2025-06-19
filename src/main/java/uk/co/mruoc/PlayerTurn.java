package uk.co.mruoc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class PlayerTurn implements Turn {

    private final Coordinates coordinates;
    private final Token token;

    public PlayerTurn(long x, long y, Token token) {
        this(new Coordinates(x, y), token);
    }

    @Override
    public Board apply(Board board) {
        var state = board.state();
        var originalToken = state.token(coordinates);
        if (!originalToken.free()) {
            throw new IllegalArgumentException(String.format(
                    "token %s already placed at coordinates %s", originalToken.value(), coordinates.toString()));
        }
        return new DefaultBoard(state.place(coordinates, token));
    }
}
