package uk.co.mruoc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class DefaultBoard implements Board {

    private final State state;
    private final Lines lines;

    public DefaultBoard() {
        this(new Size());
    }

    public DefaultBoard(int size) {
        this(new Size(size));
    }

    public DefaultBoard(Size size) {
        this(new DefaultState(size));
    }

    public DefaultBoard(State state) {
        this(state, new Lines(state.size().lines()));
    }

    @Override
    public Board initialized() {
        return new DefaultBoard(state.initialized());
    }

    @Override
    public boolean playable() {
        return !state.full() && !result().winner();
    }

    @Override
    public Result result() {
        return lines.result(state);
    }

    @Override
    public State state() {
        return state;
    }
}
