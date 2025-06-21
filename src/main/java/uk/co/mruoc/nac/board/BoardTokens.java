package uk.co.mruoc.nac.board;

import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.token.Token;

public interface BoardTokens {

    int size();

    Token token(Coordinates coordinates);
}
