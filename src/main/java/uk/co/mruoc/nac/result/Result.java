package uk.co.mruoc.nac.result;

import uk.co.mruoc.nac.board.Line;
import uk.co.mruoc.nac.token.Token;

public interface Result {

    boolean winner();

    Token token();

    Line line();
}
