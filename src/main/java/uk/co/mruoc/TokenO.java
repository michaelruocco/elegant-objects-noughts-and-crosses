package uk.co.mruoc;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class TokenO extends PlayerToken {

    public TokenO() {
        super('O');
    }
}
