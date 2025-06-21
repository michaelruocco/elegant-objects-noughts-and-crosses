package uk.co.mruoc.nac.turn;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Location;
import uk.co.mruoc.nac.token.PlayerToken;

@RequiredArgsConstructor
public class FixedTurns implements Turns {

    private final Queue<Location> coordinates;

    public FixedTurns() {
        this(new LinkedBlockingQueue<>(List.of(
                new Location(0, 0),
                new Location(2, 0),
                new Location(1, 0),
                new Location(0, 1),
                new Location(2, 1),
                new Location(1, 1),
                new Location(0, 2),
                new Location(1, 2),
                new Location(2, 2))));
    }

    @Override
    public Turn next(PlayerToken token) {
        return new PlayerTurn(coordinates.poll(), token);
    }
}
