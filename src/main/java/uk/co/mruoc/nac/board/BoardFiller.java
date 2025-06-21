package uk.co.mruoc.nac.board;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.nac.Locations;
import uk.co.mruoc.nac.token.Token;
import uk.co.mruoc.nac.token.TokenX;

@RequiredArgsConstructor
public class BoardFiller {

    private final Board board;
    private final Locations locations;
    private final Token token;

    public BoardFiller() {
        this(new EmptyBoard());
    }

    public BoardFiller(Board board) {
        this(board, new Locations(), new TokenX());
    }

    public Board fill() {
        var filledBoard = board;
        for (int x = 0; x < filledBoard.size(); x++) {
            for (int y = 0; y < filledBoard.size(); y++) {
                filledBoard = filledBoard.place(locations.location(x, y), token);
            }
        }
        return filledBoard;
    }
}
