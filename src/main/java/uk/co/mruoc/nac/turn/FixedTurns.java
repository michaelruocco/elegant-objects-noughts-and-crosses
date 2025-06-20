package uk.co.mruoc.nac.turn;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.token.PlayerToken;

@RequiredArgsConstructor
public class FixedTurns implements Turns {

    private final Queue<Coordinates> coordinates;

    public FixedTurns() {
        this(new LinkedBlockingQueue<>(List.of(
                new Coordinates(0, 0),
                new Coordinates(0, 1),
                new Coordinates(0, 2),
                new Coordinates(1, 0),
                new Coordinates(1, 1),
                new Coordinates(1, 2),
                new Coordinates(2, 1),
                new Coordinates(2, 0),
                new Coordinates(2, 2))));
    }

    @Override
    public Turn next(PlayerToken token) {
        return new PlayerTurn(coordinates.poll(), token);
    }
}
