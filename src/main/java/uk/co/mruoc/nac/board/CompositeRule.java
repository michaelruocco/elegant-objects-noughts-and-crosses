package uk.co.mruoc.nac.board;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompositeRule implements Rule {

    private final Collection<Rule> rules;

    public CompositeRule(Rule... rules) {
        this(List.of(rules));
    }

    @Override
    public void validate() {
        rules.forEach(Rule::validate);
    }
}
