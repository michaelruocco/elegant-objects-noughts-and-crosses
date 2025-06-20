package uk.co.mruoc.nac.board;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.GreaterThanOrEqualToRule;

@RequiredArgsConstructor
public class BoardMinimumSizeRule implements Rule {

    private final Rule rule;

    public BoardMinimumSizeRule(long value, long minimum) {
        this(new GreaterThanOrEqualToRule(
                value, minimum, String.format("board size %d must be greater than or equal to %d", value, minimum)));
    }

    @Override
    public void validate() {
        rule.validate();
    }
}
