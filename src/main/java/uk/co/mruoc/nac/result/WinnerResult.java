package uk.co.mruoc.nac.result;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.token.Token;

@RequiredArgsConstructor
class WinnerResult implements Result {

    private final Token token;
    private final Line line;

    @Override
    public boolean winner() {
        return !token.free();
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
