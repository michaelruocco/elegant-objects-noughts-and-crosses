package uk.co.mruoc;

public interface State extends ReadOnlyState {

    State initialized();

    State place(Coordinates coordinates, Token newToken);
}
