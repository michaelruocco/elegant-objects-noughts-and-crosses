package uk.co.mruoc.nac.turn;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.board.Board;
import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.token.Token;

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
        var originalToken = board.token(coordinates);
        if (!originalToken.free()) {
            throw new IllegalArgumentException(String.format(
                    "token %s already placed at coordinates %s", originalToken.value(), coordinates.toString()));
        }
        return board.place(coordinates, token);
    }
}
