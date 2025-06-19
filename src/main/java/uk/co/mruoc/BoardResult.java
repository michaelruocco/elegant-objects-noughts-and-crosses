package uk.co.mruoc;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardResult {

    private final Token token;
    private final BoardLine line;

    public boolean winner() {
        return !token.free();
    }

    public Token token() {
        return token;
    }

    public BoardLine line() {
        return line;
    }
}
