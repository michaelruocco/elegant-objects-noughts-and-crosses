package uk.co.mruoc.nac.board;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.token.FreeToken;
import uk.co.mruoc.nac.token.Token;

@RequiredArgsConstructor
@EqualsAndHashCode
public class DefaultBoard implements Board {

    private final BoardSize size;
    private final Map<Coordinates, Token> locations;

    public DefaultBoard() {
        this(new BoardSize());
    }

    public DefaultBoard(int size) {
        this(new BoardSize(size));
    }

    public DefaultBoard(BoardSize size) {
        this(size, Collections.unmodifiableMap(new HashMap<>()));
    }

    @Override
    public Board initialized() {
        size.validate();
        Map<Coordinates, Token> freeLocations = new LinkedHashMap<>();
        for (int y = 0; y < size.value(); y++) {
            for (int x = 0; x < size.value(); x++) {
                freeLocations.put(new Coordinates(x, y), new FreeToken());
            }
        }
        return new DefaultBoard(size, Collections.unmodifiableMap(freeLocations));
    }

    @Override
    public int size() {
        return size.value();
    }

    @Override
    public Board place(Coordinates coordinates, Token newToken) {
        var newLocations = new LinkedHashMap<>(locations);
        newLocations.put(coordinates, newToken);
        return new DefaultBoard(size, newLocations);
    }

    @Override
    public Token token(Coordinates coordinates) {
        return Optional.ofNullable(locations.get(coordinates))
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("location at coordinates %s not found", coordinates.toString())));
    }

    @Override
    public Collection<Coordinates> freeCoordinates() {
        return locations.keySet().stream()
                .filter(coordinates -> token(coordinates).free())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean playable() {
        return locations.values().stream().anyMatch(Token::free);
    }
}
