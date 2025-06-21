package uk.co.mruoc.nac.turn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.board.Board;
import uk.co.mruoc.nac.board.CoordinateSelection;
import uk.co.mruoc.nac.token.Token;

@RequiredArgsConstructor
@EqualsAndHashCode
public class RandomTurn implements Turn {

    private final Token token;
    private final CoordinateSelection selection;

    public RandomTurn(Token token) {
        this(token, new CoordinateSelection());
    }

    @Override
    public Board apply(Board board) {
        var freeCoordinates = selection.freeCoordinates(board);
        var selectedCoordinates = selectRandomly(freeCoordinates);
        return board.place(selectedCoordinates, token);
    }

    private Coordinates selectRandomly(Collection<Coordinates> freeCoordinates) {
        var shuffled = new ArrayList<>(freeCoordinates);
        Collections.shuffle(shuffled);
        return shuffled.getFirst();
    }
}
