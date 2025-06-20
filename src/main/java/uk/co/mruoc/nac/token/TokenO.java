package uk.co.mruoc.nac.token;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TokenO implements PlayerToken {

    @Override
    public String value() {
        return Character.toString('O');
    }
}
