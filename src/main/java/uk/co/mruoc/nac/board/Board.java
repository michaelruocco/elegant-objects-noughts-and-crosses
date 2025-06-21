package uk.co.mruoc.nac.board;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Location;
import uk.co.mruoc.nac.token.FreeToken;
import uk.co.mruoc.nac.token.Token;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Board implements BoardTokens {

    private final int size;
    private final Map<Location, Token> tokens;
    private final FreeToken freeToken;

    public Board() {
        this(3);
    }

    public Board(int size) {
        this(size, new HashMap<>(), new FreeToken());
    }

    public int size() {
        return size;
    }

    @Override
    public Board place(Location location, Token newToken) {
        var token = token(location);
        if (!token.free()) {
            throw new LocationAlreadyTakenException(location, token);
        }
        var newTokens = new LinkedHashMap<>(tokens);
        newTokens.put(location, newToken);
        return new Board(size, newTokens, freeToken);
    }

    @Override
    public Token token(Location location) {
        if (!withinBounds(location)) {
            throw new LocationOutsideBoardBoundsException(location, size - 1);
        }
        return Optional.ofNullable(tokens.get(location)).orElse(freeToken);
    }

    public boolean full() {
        return tokens.size() >= size * size;
    }

    private boolean withinBounds(Location location) {
        return location.withinBounds(0, size - 1);
    }
}
