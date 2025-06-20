package uk.co.mruoc.nac.token;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class FreeToken implements Token {

    @Override
    public boolean free() {
        return true;
    }

    @Override
    public String value() {
        return " ";
    }
}
