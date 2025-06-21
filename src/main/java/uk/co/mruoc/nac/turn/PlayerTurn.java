package uk.co.mruoc.nac.turn;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.board.Board;
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
        return board.place(coordinates, token);
    }
}
