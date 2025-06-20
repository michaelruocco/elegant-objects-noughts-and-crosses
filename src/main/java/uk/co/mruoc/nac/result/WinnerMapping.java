package uk.co.mruoc.nac.result;

import uk.co.mruoc.nac.token.Token;

public interface WinnerMapping {

    Result winner(Token token, Line line);
}
