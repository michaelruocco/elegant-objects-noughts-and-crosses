package uk.co.mruoc.nac.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import uk.co.mruoc.nac.token.FreeToken;
import uk.co.mruoc.nac.token.TokenO;
import uk.co.mruoc.nac.token.TokenX;
import uk.co.mruoc.nac.turn.PlayerTurn;

class BoardTest {

    @Test
    void shouldHaveDefaultSizeIfNotProvided() {
        var board = new Board.Smart(new DefaultBoard());

        var size = board.size();

        assertThat(size).isEqualTo(new Size(3));
    }

    @Test
    void shouldNotAllowBoardSizeLessThan3() {
        var board = new DefaultBoard(2);

        var error = catchThrowable(board::initialized);

        assertThat(error).isInstanceOf(IllegalArgumentException.class).hasMessage("board size 2 cannot be less than 3");
    }

    @Test
    void shouldNotAllowEvenBoardSize() {
        var board = new DefaultBoard(4);

        var error = catchThrowable(board::initialized);

        assertThat(error).isInstanceOf(IllegalArgumentException.class).hasMessage("board size 4 cannot be even");
    }

    @Test
    void shouldNotThrowExceptionIfBoardIsValid() {
        var board = new DefaultBoard();

        ThrowingCallable callable = board::initialized;

        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Test
    void shouldBePlayableInitially() {
        var board = new DefaultBoard().initialized();

        var playable = board.playable();

        assertThat(playable).isTrue();
    }

    @Test
    void shouldNotBePlayableIfFull() {
        var board = fullBoard();

        var full = board.playable();

        assertThat(full).isFalse();
    }

    @Test
    void shouldReturnTrueIfFull() {
        var board = fullBoard();

        var full = new Board.Smart(board).full();

        assertThat(full).isTrue();
    }

    @Test
    void shouldThrowExceptionIfTurnIfCoordinateLocationNotFoundOnBoard() {
        var board = new DefaultBoard().initialized();
        var turn = new PlayerTurn(4, 4, new TokenX());

        var error = catchThrowable(() -> turn.apply(board));

        assertThat(error)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("location at coordinates x:4-y:4 not found");
    }

    @Test
    void shouldReturnStalemateResultInitially() {
        var board = new DefaultBoard().initialized();

        var result = board.result();

        assertThat(result.winner()).isFalse();
        assertThat(result.token()).isEqualTo(new FreeToken());
        assertThat(result.line()).hasToString("");
    }

    @Test
    void shouldReturnStalemateResultIfNoWinner() {
        var board = new DefaultBoard().initialized();
        var turns = new PlayerTurn(0, 0, new TokenX()).andThen(new PlayerTurn(0, 1, new TokenO()));

        var result = turns.apply(board).result();

        assertThat(result.winner()).isFalse();
        assertThat(result.token()).isEqualTo(new FreeToken());
        assertThat(result.line()).hasToString("");
    }

    @Test
    void shouldReturnResultWithWinnerIfThereIsOne() {
        var x = new TokenX();
        var board = new DefaultBoard().initialized();
        var turns = new PlayerTurn(0, 0, x).andThen(new PlayerTurn(0, 1, x)).andThen(new PlayerTurn(0, 2, x));

        var result = turns.apply(board).result();

        assertThat(result.winner()).isTrue();
        assertThat(result.token()).isEqualTo(x);
        assertThat(result.line()).hasToString("x:0-y:0,x:0-y:1,x:0-y:2");
    }

    @Test
    void shouldThrowExceptionIfCoordinatesAlreadyTaken() {
        var turn = new PlayerTurn(0, 0, new TokenX());
        var board = turn.apply(new DefaultBoard().initialized());

        var error = catchThrowable(() -> new PlayerTurn(0, 0, new TokenO()).apply(board));

        assertThat(error)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("token X already placed at coordinates x:0-y:0");
    }

    @Test
    void shouldDisplayEmptyBoardStateAsString() {
        var state = new DefaultBoard().initialized().state();
        var stateString = new StateString(state);

        var string = stateString.toString();

        assertThat(string)
                .isEqualTo(
                        """
                            0 1 2
                          0     \s
                          1     \s
                          2     \s""");
    }

    @Test
    void shouldDisplayFullBoardStateAsString() {
        var board = fullBoard();
        var state = new StateString(board.state());

        var string = state.toString();

        assertThat(string)
                .isEqualTo(
                        """
                            0 1 2
                          0 X O X
                          1 O X O
                          2 X O X""");
    }

    private Board fullBoard() {
        var x = new TokenX();
        var o = new TokenO();
        var turns = new PlayerTurn(0, 0, x)
                .andThen(new PlayerTurn(0, 1, o))
                .andThen(new PlayerTurn(0, 2, x))
                .andThen(new PlayerTurn(1, 0, o))
                .andThen(new PlayerTurn(1, 1, x))
                .andThen(new PlayerTurn(1, 2, o))
                .andThen(new PlayerTurn(2, 0, x))
                .andThen(new PlayerTurn(2, 1, o))
                .andThen(new PlayerTurn(2, 2, x));
        var board = new DefaultBoard().initialized();
        return turns.apply(board);
    }
}
