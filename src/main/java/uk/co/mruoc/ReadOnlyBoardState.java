package uk.co.mruoc;

import java.util.Collection;
import lombok.RequiredArgsConstructor;

public interface ReadOnlyBoardState {

    BoardSize size();

    Token token(Coordinates coordinates);

    boolean full();

    Collection<Coordinates> freeCoordinates();

    @RequiredArgsConstructor
    final class Smart {
        private final ReadOnlyBoardState origin;

        public int sizeValue() {
            return origin.size().value();
        }

        public Token token(long x, long y) {
            return origin.token(new Coordinates(x, y));
        }
    }
}
