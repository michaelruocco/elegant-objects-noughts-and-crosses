package uk.co.mruoc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class DefaultBoard implements Board {

    private final BoardSize size;
    private final BoardLocations locations;
    private final BoardLines lines;

    public DefaultBoard() {
        this(new BoardSize());
    }

    public DefaultBoard(int size) {
        this(new BoardSize(size));
    }

    public DefaultBoard(BoardSize size) {
        this(size, new BoardLocations(size), new BoardLines(size.lines()));
    }

    @Override
    public Board initialized() {
        size.validate();
        return new DefaultBoard(size, locations.initialized(), lines);
    }

    @Override
    public Board take(Turn turn) {
        return place(turn.coordinates(), turn.token());
    }

    @Override
    public boolean playable() {
        return !locations.full() && !result().winner();
    }

    @Override
    public BoardResult result() {
        return lines.result(locations);
    }

    @Override
    public BoardState state() {
        return locations;
    }

    private Board place(Coordinates coordinates, Token newToken) {
        var originalToken = locations.token(coordinates);
        if (!originalToken.free()) {
            throw new IllegalArgumentException(String.format(
                    "token %s already placed at coordinates %s", originalToken.value(), coordinates.toString()));
        }
        return new DefaultBoard(size, locations.place(coordinates, newToken), lines);
    }
}
