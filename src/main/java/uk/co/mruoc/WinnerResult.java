package uk.co.mruoc;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WinnerResult implements Result {

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
