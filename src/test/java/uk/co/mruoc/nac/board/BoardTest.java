package uk.co.mruoc.nac.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.nac.result.Outcome;
import uk.co.mruoc.nac.token.FreeToken;
import uk.co.mruoc.nac.token.TokenO;
import uk.co.mruoc.nac.token.TokenX;
import uk.co.mruoc.nac.turn.PlayerTurn;

class BoardTest {

    @Test
    void shouldHaveDefaultSizeIfNotProvided() {
        var board = new Board();

        var size = board.size();

        assertThat(size).isEqualTo(3);
    }

    @Test
    void shouldNotBeFullInitially() {
        var board = new Board();

        var playable = board.full();

        assertThat(playable).isFalse();
    }

    @Test
    void shouldReturnTrueIfFull() {
        var board = new BoardFiller().fill();

        var full = board.full();

        assertThat(full).isTrue();
    }

    @Test
    void shouldThrowExceptionIfXCoordinateLessThanBoardMinimumBounds() {
        var board = new Board();
        var turn = new PlayerTurn(-1, 1, new TokenX());

        var error = catchThrowable(() -> turn.apply(board));

        assertThat(error)
                .isInstanceOf(LocationOutsideBoardBoundsException.class)
                .hasMessage("location x:-1|y:1 outside board bounds, coordinate values must be between 0 and 2");
    }

    @Test
    void shouldThrowExceptionIfXCoordinateGreaterThanThanBoardMaximumBounds() {
        var board = new Board();
        var turn = new PlayerTurn(3, 1, new TokenX());

        var error = catchThrowable(() -> turn.apply(board));

        assertThat(error)
                .isInstanceOf(LocationOutsideBoardBoundsException.class)
                .hasMessage("location x:3|y:1 outside board bounds, coordinate values must be between 0 and 2");
    }

    @Test
    void shouldThrowExceptionIfYCoordinateLessThanBoardMinimumBounds() {
        var board = new Board();
        var turn = new PlayerTurn(1, -1, new TokenX());

        var error = catchThrowable(() -> turn.apply(board));

        assertThat(error)
                .isInstanceOf(LocationOutsideBoardBoundsException.class)
                .hasMessage("location x:1|y:-1 outside board bounds, coordinate values must be between 0 and 2");
    }

    @Test
    void shouldThrowExceptionIfYCoordinateGreaterThanThanBoardMaximumBounds() {
        var board = new Board();
        var turn = new PlayerTurn(1, 3, new TokenX());

        var error = catchThrowable(() -> turn.apply(board));

        assertThat(error)
                .isInstanceOf(LocationOutsideBoardBoundsException.class)
                .hasMessage("location x:1|y:3 outside board bounds, coordinate values must be between 0 and 2");
    }

    @Test
    void shouldReturnStalemateResultInitially() {
        var board = new Board();
        var outcome = new Outcome();

        var result = outcome.decide(board);

        assertThat(result.winner()).isFalse();
        assertThat(result.token()).isEqualTo(new FreeToken());
        assertThat(result.line()).hasToString("");
    }

    @Test
    void shouldReturnStalemateResultIfNoWinner() {
        var board = new Board();
        var turns = new PlayerTurn(0, 0, new TokenX()).andThen(new PlayerTurn(0, 1, new TokenO()));
        var outcome = new Outcome();

        var result = outcome.decide(turns.apply(board));

        assertThat(result.winner()).isFalse();
        assertThat(result.token()).isEqualTo(new FreeToken());
        assertThat(result.line()).hasToString("");
        assertThat(result.line().coordinates()).isEmpty();
    }

    @Test
    void shouldReturnResultWithColumnWinnerIfThereIsOne() {
        var x = new TokenX();
        var board = new Board();
        var turns = new PlayerTurn(0, 0, x).andThen(new PlayerTurn(0, 1, x)).andThen(new PlayerTurn(0, 2, x));
        var outcome = new Outcome();

        var result = outcome.decide(turns.apply(board));

        assertThat(result.winner()).isTrue();
        assertThat(result.token()).isEqualTo(x);
        assertThat(result.line()).hasToString("x:0|y:0,x:0|y:1,x:0|y:2");
    }

    @Test
    void shouldReturnResultWithRowWinnerIfThereIsOne() {
        var x = new TokenX();
        var board = new Board();
        var turns = new PlayerTurn(0, 0, x).andThen(new PlayerTurn(1, 0, x)).andThen(new PlayerTurn(2, 0, x));
        var outcome = new Outcome();

        var result = outcome.decide(turns.apply(board));

        assertThat(result.winner()).isTrue();
        assertThat(result.token()).isEqualTo(x);
        assertThat(result.line()).hasToString("x:0|y:0,x:1|y:0,x:2|y:0");
    }

    @Test
    void shouldReturnResultWithForwardSlashWinnerIfThereIsOne() {
        var x = new TokenX();
        var board = new Board();
        var turns = new PlayerTurn(0, 2, x).andThen(new PlayerTurn(1, 1, x)).andThen(new PlayerTurn(2, 0, x));
        var outcome = new Outcome();

        var result = outcome.decide(turns.apply(board));

        assertThat(result.winner()).isTrue();
        assertThat(result.token()).isEqualTo(x);
        assertThat(result.line()).hasToString("x:2|y:0,x:1|y:1,x:0|y:2");
    }

    @Test
    void shouldReturnResultWithBackSlashWinnerIfThereIsOne() {
        var x = new TokenX();
        var board = new Board();
        var turns = new PlayerTurn(0, 0, x).andThen(new PlayerTurn(1, 1, x)).andThen(new PlayerTurn(2, 2, x));
        var outcome = new Outcome();

        var result = outcome.decide(turns.apply(board));

        assertThat(result.winner()).isTrue();
        assertThat(result.token()).isEqualTo(x);
        assertThat(result.line()).hasToString("x:0|y:0,x:1|y:1,x:2|y:2");
    }

    @Test
    void shouldThrowExceptionIfCoordinatesAlreadyTaken() {
        var turn = new PlayerTurn(0, 0, new TokenX());
        var board = turn.apply(new Board());

        var error = catchThrowable(() -> new PlayerTurn(0, 0, new TokenO()).apply(board));

        assertThat(error)
                .isInstanceOf(LocationAlreadyTakenException.class)
                .hasMessage("location x:0|y:0 already taken by token X");
    }
}
