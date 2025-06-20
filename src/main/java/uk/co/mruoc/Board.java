package uk.co.mruoc;

import lombok.RequiredArgsConstructor;

public interface Board {

    Board initialized();

    boolean playable();

    Result result();

    State state();

    @RequiredArgsConstructor
    final class Smart {
        private final Board origin;

        public Size size() {
            return origin.state().size();
        }

        public boolean full() {
            return origin.state().full();
        }
    }
}
