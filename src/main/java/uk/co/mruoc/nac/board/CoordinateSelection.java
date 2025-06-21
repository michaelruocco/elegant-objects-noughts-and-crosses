package uk.co.mruoc.nac.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Coordinates;
import uk.co.mruoc.nac.token.Token;

@RequiredArgsConstructor
public class CoordinateSelection {

    private final CoordinateMapping coordinateMapping;

    public CoordinateSelection() {
        this(Coordinates::new);
    }

    public Collection<Coordinates> freeCoordinates(Board board) {
        return selection(board, Token::free);
    }

    private Collection<Coordinates> selection(Board board, Predicate<Token> predicate) {
        Collection<Coordinates> selected = new ArrayList<>();
        for (int x = 0; x < board.size(); x++) {
            for (int y = 0; y < board.size(); y++) {
                var coordinates = coordinateMapping.map(x, y);
                var token = board.token(coordinates);
                if (predicate.test(token)) {
                    selected.add(coordinates);
                }
            }
        }
        return Collections.unmodifiableCollection(selected);
    }
}
