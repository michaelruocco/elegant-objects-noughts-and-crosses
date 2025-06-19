package uk.co.mruoc;

public class StalemateResult extends BoardResult {

    public StalemateResult() {
        this(new BoardLine());
    }

    public StalemateResult(BoardLine line) {
        super(new FreeToken(), line);
    }
}
