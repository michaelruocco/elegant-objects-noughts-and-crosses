package uk.co.mruoc;

import lombok.RequiredArgsConstructor;

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
