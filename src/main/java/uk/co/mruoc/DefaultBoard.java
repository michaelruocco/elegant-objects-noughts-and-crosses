package uk.co.mruoc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class DefaultBoard implements Board {

    private final BoardState state;
    private final BoardLines lines;

    public DefaultBoard() {
        this(new BoardSize());
    }

    public DefaultBoard(int size) {
        this(new BoardSize(size));
    }

    public DefaultBoard(BoardSize size) {
        this(new DefaultBoardState(size));
    }

    public DefaultBoard(BoardState state) {
        this(state, new BoardLines(state.size().lines()));
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
    public BoardResult result() {
        return lines.result(state);
    }

    @Override
    public BoardState state() {
        return state;
    }
}
