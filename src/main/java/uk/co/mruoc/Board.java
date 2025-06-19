package uk.co.mruoc;

import lombok.RequiredArgsConstructor;

public interface Board {

    Board initialized();

    boolean playable();

    BoardResult result();

    BoardState state();

    @RequiredArgsConstructor
    final class Smart {
        private final Board origin;

        public BoardSize size() {
            return origin.state().size();
        }

        public boolean full() {
            return origin.state().full();
        }
    }
}
