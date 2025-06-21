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
public class EmptyBoard implements Board {

    private final int size;
    private final Map<Location, Token> tokens;
    private final FreeToken freeToken;

    public EmptyBoard() {
        this(3);
    }

    public EmptyBoard(int size) {
        this(size, new HashMap<>(), new FreeToken());
    }

    @Override
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
        return new EmptyBoard(size, newTokens, freeToken);
    }

    @Override
    public Token token(Location location) {
        if (!withinBounds(location)) {
            throw new LocationOutsideBoardBoundsException(location, size - 1);
        }
        return Optional.ofNullable(tokens.get(location)).orElse(freeToken);
    }

    @Override
    public boolean full() {
        return tokens.size() >= size * size;
    }

    private boolean withinBounds(Location location) {
        return location.withinBounds(0, size - 1);
    }
}
