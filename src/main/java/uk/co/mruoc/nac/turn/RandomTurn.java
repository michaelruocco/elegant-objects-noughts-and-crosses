package uk.co.mruoc.nac.turn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Location;
import uk.co.mruoc.nac.Locations;
import uk.co.mruoc.nac.board.Board;
import uk.co.mruoc.nac.token.Token;

@RequiredArgsConstructor
@EqualsAndHashCode
public class RandomTurn implements Turn {

    private final Token token;
    private final Locations locations;

    public RandomTurn(Token token) {
        this(token, new Locations());
    }

    @Override
    public Board apply(Board board) {
        var freeLocations = locations.free(board);
        var selectedLocation = selectRandomly(freeLocations);
        return board.place(selectedLocation, token);
    }

    private Location selectRandomly(Collection<Location> freeLocations) {
        var shuffled = new ArrayList<>(freeLocations);
        Collections.shuffle(shuffled);
        return shuffled.getFirst();
    }
}
