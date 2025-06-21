package uk.co.mruoc.nac.board;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.CompositeRule;
import uk.co.mruoc.nac.Rule;

@RequiredArgsConstructor
public class BoardRules implements Rule {

    private final CompositeRule rule;

    public BoardRules(int size) {
        this(size, 3);
    }

    public BoardRules(int size, int minimum) {
        this(new CompositeRule(new BoardMinimumSizeRule(size, minimum), new OddNumberRule(size)));
    }

    @Override
    public void validate() {
        rule.validate();
    }
}
