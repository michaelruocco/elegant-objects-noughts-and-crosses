package uk.co.mruoc;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class DefaultState implements State {

    private final Size size;
    private final Map<Coordinates, Token> locations;

    public DefaultState(Size size) {
        this(size, new LinkedHashMap<>());
    }

    @Override
    public Size size() {
        return size;
    }

    @Override
    public State initialized() {
        size.validate();
        Map<Coordinates, Token> freeLocations = new LinkedHashMap<>();
        for (int y = 0; y < size.value(); y++) {
            for (int x = 0; x < size.value(); x++) {
                freeLocations.put(new Coordinates(x, y), new FreeToken());
            }
        }
        return new DefaultState(size, Collections.unmodifiableMap(freeLocations));
    }

    @Override
    public State place(Coordinates coordinates, Token newToken) {
        var newLocations = new LinkedHashMap<>(locations);
        newLocations.put(coordinates, newToken);
        return new DefaultState(size, newLocations);
    }

    @Override
    public Token token(Coordinates coordinates) {
        return Optional.ofNullable(locations.get(coordinates))
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("location at coordinates %s not found", coordinates.toString())));
    }

    @Override
    public boolean full() {
        return locations.values().stream().noneMatch(Token::free);
    }

    @Override
    public Collection<Coordinates> freeCoordinates() {
        return locations.keySet().stream()
                .filter(coordinates -> token(coordinates).free())
                .collect(Collectors.toSet());
    }
}
