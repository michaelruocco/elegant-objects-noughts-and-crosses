package uk.co.mruoc.nac.result;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.token.FreeToken;
import uk.co.mruoc.nac.token.Token;

@RequiredArgsConstructor
public class StalemateResult implements Result {

    @Override
    public boolean winner() {
        return false;
    }

    @Override
    public Token token() {
        return new FreeToken();
    }

    @Override
    public Line line() {
        return new Line();
    }
}
