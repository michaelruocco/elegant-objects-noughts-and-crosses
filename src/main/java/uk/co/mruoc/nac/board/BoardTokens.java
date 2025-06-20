package uk.co.mruoc.nac.board;

import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.token.Token;

public interface BoardTokens {

    default Token token(long x, long y) {
        return token(new Coordinates(x, y));
    }

    Token token(Coordinates coordinates);
}
