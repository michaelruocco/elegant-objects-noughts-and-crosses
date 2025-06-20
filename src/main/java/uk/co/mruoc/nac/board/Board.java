package uk.co.mruoc.nac.board;

public interface Board {

    Board initialized();

    boolean playable();

    State state();
}
