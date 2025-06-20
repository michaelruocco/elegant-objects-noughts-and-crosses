package uk.co.mruoc.nac.board;

import uk.co.mruoc.nac.token.Token;

public interface State extends ReadOnlyState {

    State initialized();

    State place(Coordinates coordinates, Token newToken);
}
