package uk.co.mruoc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void shouldHaveDefaultSizeIfNotProvided() {
        var board = new Board();

        var size = board.size();

        assertThat(size).isEqualTo(3);
    }

    @Test
    void shouldNotAllowBoardSizeLessThan3() {
        var board = new Board(2);

        var error = catchThrowable(board::validate);

        assertThat(error).isInstanceOf(IllegalArgumentException.class).hasMessage("board size 2 cannot be less than 3");
    }

    @Test
    void shouldNotAllowEvenBoardSize() {
        var board = new Board(4);

        var error = catchThrowable(board::validate);

        assertThat(error).isInstanceOf(IllegalArgumentException.class).hasMessage("board size 4 cannot be even");
    }

    @Test
    void shouldNotThrowExceptionIfBoardIsValid() {
        var board = new Board();

        ThrowingCallable callable = board::validate;

        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Test
    void shouldBeEmptyInitially() {
        var board = new Board();

        var empty = board.empty();

        assertThat(empty).isTrue();
    }

    @Test
    void shouldReturnFalseIfNotEmpty() {
        var board = new Board().take(new Turn(0, 0, PlayerToken.X()));

        var empty = board.empty();

        assertThat(empty).isFalse();
    }

    @Test
    void shouldBePlayableInitially() {
        var board = new Board();

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
    void shouldNotBeFullInitially() {
        var board = new Board();

        var full = board.full();

        assertThat(full).isFalse();
    }

    @Test
    void shouldReturnTrueIfFull() {
        var board = fullBoard();

        var full = board.full();

        assertThat(full).isTrue();
    }

    @Test
    void shouldThrowExceptionIfTurnIfCoordinateLocationNotFoundOnBoard() {
        var board = new Board();

        var error = catchThrowable(() -> board.take(new Turn(4, 4, PlayerToken.X())));

        assertThat(error)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("location at coordinates x:4-y:4 not found");
    }

    @Test
    void shouldReturnStalemateResultInitially() {
        var board = new Board();

        var result = board.result();

        assertThat(PlayerToken.X()).isEqualTo(new PlayerToken('X'));
        assertThat(PlayerToken.X().equals(new PlayerToken('X'))).isTrue();

        assertThat(result.winner()).isFalse();
        assertThat(result.token()).isEqualTo(new FreeToken());
        assertThat(result.line()).hasToString("");
    }

    @Test
    void shouldReturnStalemateResultIfNoWinner() {
        var board = new Board().take(new Turn(0, 0, PlayerToken.X())).take(new Turn(0, 1, PlayerToken.O()));

        var result = board.result();

        assertThat(result.winner()).isFalse();
        assertThat(result.token()).isEqualTo(new FreeToken());
        assertThat(result.line()).hasToString("");
    }

    @Test
    void shouldReturnResultWithWinnerIfThereIsOne() {
        var x = PlayerToken.X();
        var board = new Board().take(new Turn(0, 0, x)).take(new Turn(0, 1, x)).take(new Turn(0, 2, x));

        var result = board.result();

        assertThat(result.winner()).isTrue();
        assertThat(result.token()).isEqualTo(x);
        assertThat(result.line()).hasToString("x:0-y:0,x:0-y:1,x:0-y:2");
    }

    @Test
    void shouldThrowExceptionIfCoordinatesAlreadyTaken() {
        var board = new Board().take(new Turn(0, 0, PlayerToken.X()));

        var error = catchThrowable(() -> board.take(new Turn(0, 0, PlayerToken.O())));

        assertThat(error)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("token X already placed at coordinates x:0-y:0");
    }

    @Test
    void shouldDisplayEmptyBoardStateAsString() {
        var state = new BoardStateString(new Board());

        var string = state.toString();

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
        var state = new BoardStateString(fullBoard());

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
        var x = PlayerToken.X();
        var o = PlayerToken.O();
        return new Board()
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
