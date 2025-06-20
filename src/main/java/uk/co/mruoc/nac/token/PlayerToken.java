package uk.co.mruoc.nac.token;

public interface PlayerToken extends Token {

    @Override
    default boolean free() {
        return false;
    }
}
