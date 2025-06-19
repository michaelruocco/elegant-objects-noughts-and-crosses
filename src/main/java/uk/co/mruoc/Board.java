package uk.co.mruoc;

import java.util.*;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Board implements BoardTokens {

    private final BoardSize size;
    private final Map<Coordinates, Token> locations;
    private final BoardLines lines;

    public Board() {
        this(new BoardSize());
    }

    public Board(int size) {
        this(new BoardSize(size));
    }

    public Board(BoardSize size) {
        this(size, freeLocations(size.value()));
    }

    private Board(BoardSize size, Map<Coordinates, Token> locations) {
        this.size = size;
        this.locations = Collections.unmodifiableMap(locations);
        this.lines = new BoardLines(size);
    }

    public void validate() {
        size.validate();
        locations.keySet().forEach(Coordinates::validate);
    }

    public Board take(Turn turn) {
        return place(turn.coordinates(), turn.token());
    }

    public Board place(Coordinates coordinates, Token newToken) {
        var originalToken = token(coordinates);
        if (!originalToken.free()) {
            throw new IllegalArgumentException(String.format(
                    "token %s already placed at coordinates %s", originalToken.value(), coordinates.toString()));
        }
        return new Board(size, update(coordinates, newToken));
    }

    public int size() {
        return size.value();
    }

    public boolean playable() {
        return !full(); // TODO need to add winner check here
    }

    public boolean full() {
        return locations.values().stream().noneMatch(Token::free);
    }

    public boolean empty() {
        return locations.values().stream().allMatch(Token::free);
    }

    @Override
    public Token token(Coordinates coordinates) {
        return Optional.ofNullable(locations.get(coordinates))
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("location at coordinates %s not found", coordinates.toString())));
    }

    public BoardResult result() {
        return lines.result(this);
    }

    private Map<Coordinates, Token> update(Coordinates coordinates, Token newToken) {
        var newLocations = new LinkedHashMap<>(locations);
        newLocations.put(coordinates, newToken);
        return newLocations;
    }

    private static Map<Coordinates, Token> freeLocations(long size) {
        Map<Coordinates, Token> freeLocations = new LinkedHashMap<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                freeLocations.put(new Coordinates(x, y), new FreeToken());
            }
        }
        return freeLocations;
    }
}
