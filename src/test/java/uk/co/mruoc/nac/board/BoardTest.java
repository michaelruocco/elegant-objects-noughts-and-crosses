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
        var board = new EmptyBoard();

        var size = board.size();

        assertThat(size).isEqualTo(3);
    }

    @Test
    void shouldNotBeFullInitially() {
        var board = new EmptyBoard();

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
    void shouldThrowExceptionIfTurnIfCoordinateLocationNotFoundOnBoard() {
        var board = new EmptyBoard();
        var turn = new PlayerTurn(4, 4, new TokenX());

        var error = catchThrowable(() -> turn.apply(board));

        assertThat(error)
                .isInstanceOf(LocationOutsideBoardBoundsException.class)
                .hasMessage("location x:4-y:4 outside board bounds, coordinate values must be between 0 and 2");
    }

    @Test
    void shouldReturnStalemateResultInitially() {
        var board = new EmptyBoard();
        var outcome = new Outcome();

        var result = outcome.decide(board);

        assertThat(result.winner()).isFalse();
        assertThat(result.token()).isEqualTo(new FreeToken());
        assertThat(result.line()).hasToString("");
    }

    @Test
    void shouldReturnStalemateResultIfNoWinner() {
        var board = new EmptyBoard();
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
        var board = new EmptyBoard();
        var turns = new PlayerTurn(0, 0, x).andThen(new PlayerTurn(0, 1, x)).andThen(new PlayerTurn(0, 2, x));
        var outcome = new Outcome();

        var result = outcome.decide(turns.apply(board));

        assertThat(result.winner()).isTrue();
        assertThat(result.token()).isEqualTo(x);
        assertThat(result.line()).hasToString("x:0-y:0,x:0-y:1,x:0-y:2");
    }

    @Test
    void shouldReturnResultWithRowWinnerIfThereIsOne() {
        var x = new TokenX();
        var board = new EmptyBoard();
        var turns = new PlayerTurn(0, 0, x).andThen(new PlayerTurn(1, 0, x)).andThen(new PlayerTurn(2, 0, x));
        var outcome = new Outcome();

        var result = outcome.decide(turns.apply(board));

        assertThat(result.winner()).isTrue();
        assertThat(result.token()).isEqualTo(x);
        assertThat(result.line()).hasToString("x:0-y:0,x:1-y:0,x:2-y:0");
    }

    @Test
    void shouldReturnResultWithForwardSlashWinnerIfThereIsOne() {
        var x = new TokenX();
        var board = new EmptyBoard();
        var turns = new PlayerTurn(0, 2, x).andThen(new PlayerTurn(1, 1, x)).andThen(new PlayerTurn(2, 0, x));
        var outcome = new Outcome();

        var result = outcome.decide(turns.apply(board));

        assertThat(result.winner()).isTrue();
        assertThat(result.token()).isEqualTo(x);
        assertThat(result.line()).hasToString("x:2-y:0,x:1-y:1,x:0-y:2");
    }

    @Test
    void shouldReturnResultWithBackSlashWinnerIfThereIsOne() {
        var x = new TokenX();
        var board = new EmptyBoard();
        var turns = new PlayerTurn(0, 0, x).andThen(new PlayerTurn(1, 1, x)).andThen(new PlayerTurn(2, 2, x));
        var outcome = new Outcome();

        var result = outcome.decide(turns.apply(board));

        assertThat(result.winner()).isTrue();
        assertThat(result.token()).isEqualTo(x);
        assertThat(result.line()).hasToString("x:0-y:0,x:1-y:1,x:2-y:2");
    }

    @Test
    void shouldThrowExceptionIfCoordinatesAlreadyTaken() {
        var turn = new PlayerTurn(0, 0, new TokenX());
        var board = turn.apply(new EmptyBoard());

        var error = catchThrowable(() -> new PlayerTurn(0, 0, new TokenO()).apply(board));

        assertThat(error)
                .isInstanceOf(LocationAlreadyTakenException.class)
                .hasMessage("location x:0-y:0 already taken by token X");
    }
}
