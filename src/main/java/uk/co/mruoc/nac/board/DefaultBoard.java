package uk.co.mruoc.nac.board;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class DefaultBoard implements Board {

    private final State state;

    public DefaultBoard() {
        this(new BoardSize());
    }

    public DefaultBoard(int size) {
        this(new BoardSize(size));
    }

    public DefaultBoard(BoardSize size) {
        this(new DefaultState(size));
    }

    @Override
    public Board initialized() {
        return new DefaultBoard(state.initialized());
    }

    @Override
    public boolean playable() {
        return !state.full();
    }

    @Override
    public State state() {
        return state;
    }
}
