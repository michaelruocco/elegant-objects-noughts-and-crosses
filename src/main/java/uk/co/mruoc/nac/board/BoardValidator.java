package uk.co.mruoc.nac.board;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardValidator {

    private final BoardRules rules;

    public BoardValidator(Board board) {
        this(new BoardRules(board.size()));
    }

    public void validate() {
        rules.validate();
    }
}
