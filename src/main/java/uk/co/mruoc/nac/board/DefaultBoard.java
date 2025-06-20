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

    private final int size;
    private final BoardRules rules;
    private final Map<Coordinates, Token> locations;
    private final CoordinateMapping coordinateMapping;
    private final FreeToken freeToken;

    public DefaultBoard() {
        this(3);
    }

    public DefaultBoard(int size) {
        this(
                size,
                new BoardRules(size),
                Collections.unmodifiableMap(new HashMap<>()),
                Coordinates::new,
                new FreeToken());
    }

    @Override
    public Board initialized() {
        rules.validate();
        Map<Coordinates, Token> freeLocations = new LinkedHashMap<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                freeLocations.put(coordinateMapping.map(x, y), freeToken);
            }
        }
        return new DefaultBoard(size, rules, Collections.unmodifiableMap(freeLocations), coordinateMapping, freeToken);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Board place(Coordinates coordinates, Token newToken) {
        var newLocations = new LinkedHashMap<>(locations);
        newLocations.put(coordinates, newToken);
        return new DefaultBoard(size, rules, newLocations, coordinateMapping, freeToken);
    }

    // TODO split the methods below out into BoardLocations class
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
