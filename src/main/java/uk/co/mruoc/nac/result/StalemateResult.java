package uk.co.mruoc.nac.result;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.token.FreeToken;
import uk.co.mruoc.nac.token.Token;

@RequiredArgsConstructor
class StalemateResult implements Result {

    private final Token token;
    private final Line line;

    public StalemateResult() {
        this(new FreeToken(), new EmptyLine());
    }

    @Override
    public boolean winner() {
        return false;
    }

    @Override
    public Token token() {
        return token;
    }

    @Override
    public Line line() {
        return line;
    }
}
