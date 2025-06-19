package uk.co.mruoc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class RandomTurn implements Turn {

    private final Token token;

    @Override
    public Board apply(Board board) {
        var state = board.state();
        var freeCoordinates = state.freeCoordinates();
        var coordinates = selectRandomly(freeCoordinates);
        return new DefaultBoard(state.place(coordinates, token));
    }

    private Coordinates selectRandomly(Collection<Coordinates> freeCoordinates) {
        var shuffled = new ArrayList<>(freeCoordinates);
        Collections.shuffle(shuffled);
        return shuffled.getFirst();
    }
}
