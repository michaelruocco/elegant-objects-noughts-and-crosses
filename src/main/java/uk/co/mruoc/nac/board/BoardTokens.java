package uk.co.mruoc.nac.board;

import uk.co.mruoc.nac.Location;
import uk.co.mruoc.nac.token.Token;

public interface BoardTokens {

    Token token(Location location);

    Board place(Location location, Token newToken);
}
