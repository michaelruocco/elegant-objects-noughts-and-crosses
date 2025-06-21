package uk.co.mruoc.nac.board;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
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
        this(size, new BoardRules(size), new HashMap<>(), Coordinates::new, new FreeToken());
    }

    @Override
    public Board initialized() {
        rules.validate();
        return this;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Board place(Coordinates coordinates, Token newToken) {
        var originalToken = token(coordinates);
        if (!originalToken.free()) {
            // TODO add location must be free to board rules
            throw new IllegalArgumentException(String.format(
                    "token %s already placed at coordinates %s", originalToken.value(), coordinates.toString()));
        }
        var newLocations = new LinkedHashMap<>(locations);
        newLocations.put(coordinates, newToken);
        return new DefaultBoard(size, rules, newLocations, coordinateMapping, freeToken);
    }

    @Override
    public Token token(Coordinates coordinates) {
        if (!withinBounds(coordinates)) {
            throw new IllegalArgumentException(String.format(
                    "location %s is not within board bounds, coordinates must be between 0 and %d",
                    coordinates.toString(), size - 1));
        }
        return Optional.ofNullable(locations.get(coordinates)).orElse(freeToken);
    }

    @Override
    public boolean playable() {
        return locations.size() < size * size;
    }

    private boolean withinBounds(Coordinates coordinates) {
        return coordinates.withinBounds(0, size - 1);
    }
}
