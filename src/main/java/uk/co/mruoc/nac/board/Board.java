package uk.co.mruoc.nac.board;

import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.token.Token;

public interface Board extends ReadOnlyBoard {

    Board initialized();

    Board place(Coordinates coordinates, Token newToken);
}
