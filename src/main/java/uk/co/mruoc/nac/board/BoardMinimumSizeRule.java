package uk.co.mruoc.nac.board;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.GreaterThanOrEqualToRule;
import uk.co.mruoc.nac.Rule;

@RequiredArgsConstructor
public class BoardMinimumSizeRule implements Rule {

    private final Rule rule;

    public BoardMinimumSizeRule(long value, long minimum) {
        this(new GreaterThanOrEqualToRule(value, minimum, new MinimumBoardSizeException(value, minimum)));
    }

    @Override
    public void validate() {
        rule.validate();
    }
}
