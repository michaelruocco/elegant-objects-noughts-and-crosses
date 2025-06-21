package uk.co.mruoc.nac.board;

public class MinimumBoardSizeException extends RuntimeException {

    public MinimumBoardSizeException(long size, long minimum) {
        super(String.format("board size %d must be greater than or equal to %d", size, minimum));
    }
}
