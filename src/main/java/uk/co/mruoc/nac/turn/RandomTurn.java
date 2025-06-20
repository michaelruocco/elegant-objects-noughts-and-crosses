package uk.co.mruoc.nac.turn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.board.Board;
import uk.co.mruoc.nac.token.Token;

@RequiredArgsConstructor
@EqualsAndHashCode
public class RandomTurn implements Turn {

    private final Token token;

    @Override
    public Board apply(Board board) {
        var freeCoordinates = board.freeCoordinates();
        var coordinates = selectRandomly(freeCoordinates);
        return board.place(coordinates, token);
    }

    private Coordinates selectRandomly(Collection<Coordinates> freeCoordinates) {
        var shuffled = new ArrayList<>(freeCoordinates);
        Collections.shuffle(shuffled);
        return shuffled.getFirst();
    }
}
