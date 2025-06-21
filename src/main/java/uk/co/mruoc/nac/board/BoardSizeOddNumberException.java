package uk.co.mruoc.nac.board;

public class BoardSizeOddNumberException extends RuntimeException {

    public BoardSizeOddNumberException(int size) {
        super(String.format("board size %d must be an odd number", size));
    }
}
