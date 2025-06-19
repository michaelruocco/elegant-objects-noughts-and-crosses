package uk.co.mruoc;

public interface BoardState extends ReadOnlyBoardState {

    BoardState initialized();

    BoardState place(Coordinates coordinates, Token newToken);
}
