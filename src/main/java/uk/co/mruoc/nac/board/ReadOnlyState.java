package uk.co.mruoc.nac.board;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.token.Token;

public interface ReadOnlyState {

    Size size();

    Token token(Coordinates coordinates);

    boolean full();

    Collection<Coordinates> freeCoordinates();

    @RequiredArgsConstructor
    final class Smart {
        private final ReadOnlyState origin;

        public int sizeValue() {
            return origin.size().value();
        }

        public Token token(long x, long y) {
            return origin.token(new Coordinates(x, y));
        }
    }
}
