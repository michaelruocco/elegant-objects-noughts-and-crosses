package uk.co.mruoc;

import lombok.RequiredArgsConstructor;

public interface BoardState {

    BoardSize size();

    BoardState initialized();

    BoardState place(Coordinates coordinates, Token newToken);

    Token token(Coordinates coordinates);

    boolean full();

    @RequiredArgsConstructor
    final class Smart {
        private final BoardState origin;

        public int sizeValue() {
            return origin.size().value();
        }

        public Token token(long x, long y) {
            return origin.token(new Coordinates(x, y));
        }
    }
}
