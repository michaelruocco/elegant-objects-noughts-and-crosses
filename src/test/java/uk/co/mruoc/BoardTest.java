package uk.co.mruoc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void shouldHaveDefaultSizeIfNotProvided() {
        var board = new Board.Smart(new DefaultBoard());

        var size = board.size();

        assertThat(size).isEqualTo(new BoardSize(3));
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
        var board = new DefaultBoard();

        var error = catchThrowable(() -> board.take(new Turn(4, 4, new TokenX())));

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
        var board = new DefaultBoard()
                .initialized()
                .take(new Turn(0, 0, new TokenX()))
                .take(new Turn(0, 1, new TokenO()));

        var result = board.result();

        assertThat(result.winner()).isFalse();
        assertThat(result.token()).isEqualTo(new FreeToken());
        assertThat(result.line()).hasToString("");
    }

    @Test
    void shouldReturnResultWithWinnerIfThereIsOne() {
        var x = new TokenX();
        var board = new DefaultBoard()
                .initialized()
                .take(new Turn(0, 0, x))
                .take(new Turn(0, 1, x))
                .take(new Turn(0, 2, x));

        var result = board.result();

        assertThat(result.winner()).isTrue();
        assertThat(result.token()).isEqualTo(x);
        assertThat(result.line()).hasToString("x:0-y:0,x:0-y:1,x:0-y:2");
    }

    @Test
    void shouldThrowExceptionIfCoordinatesAlreadyTaken() {
        var board = new DefaultBoard().initialized().take(new Turn(0, 0, new TokenX()));

        var error = catchThrowable(() -> board.take(new Turn(0, 0, new TokenO())));

        assertThat(error)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("token X already placed at coordinates x:0-y:0");
    }

    @Test
    void shouldDisplayEmptyBoardStateAsString() {
        var state = new DefaultBoard().initialized().state();
        var stateString = new BoardStateString(state);

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
        var state = new BoardStateString(board.state());

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
        return new DefaultBoard()
                .initialized()
                .take(new Turn(0, 0, x))
                .take(new Turn(0, 1, o))
                .take(new Turn(0, 2, x))
                .take(new Turn(1, 0, o))
                .take(new Turn(1, 1, x))
                .take(new Turn(1, 2, o))
                .take(new Turn(2, 0, x))
                .take(new Turn(2, 1, o))
                .take(new Turn(2, 2, x));
    }
}
