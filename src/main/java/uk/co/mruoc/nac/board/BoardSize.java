package uk.co.mruoc.nac.board;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class BoardSize {

    private final int value;

    public BoardSize() {
        this(3);
    }

    public int value() {
        return value;
    }

    public void validate() {
        if (value < 3) {
            throw new IllegalArgumentException(String.format("board size %d cannot be less than 3", value));
        }
        if (even()) {
            throw new IllegalArgumentException(String.format("board size %d cannot be even", value));
        }
    }

    private boolean even() {
        return ((value % 2) == 0);
    }
}
