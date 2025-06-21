package uk.co.mruoc.nac;

import static java.util.Collections.unmodifiableCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import uk.co.mruoc.nac.board.Board;
import uk.co.mruoc.nac.token.Token;

public class Locations {

    public Collection<Location> free(Board board) {
        return selection(board, Token::free);
    }

    private Collection<Location> selection(Board board, Predicate<Token> predicate) {
        Collection<Location> selected = new ArrayList<>();
        for (int x = 0; x < board.size(); x++) {
            for (int y = 0; y < board.size(); y++) {
                var location = location(x, y);
                var token = board.token(location);
                if (predicate.test(token)) {
                    selected.add(location);
                }
            }
        }
        return unmodifiableCollection(selected);
    }

    public Location location(int x, int y) {
        return new Location(x, y);
    }
}
