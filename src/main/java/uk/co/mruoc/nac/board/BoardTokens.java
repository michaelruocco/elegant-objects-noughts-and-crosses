package uk.co.mruoc.nac.board;

import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.token.Token;

public interface BoardTokens {

    Token token(Coordinates coordinates);
}
