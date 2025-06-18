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
        var board = new Board().take(new Turn(0, 0, 'X'));

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

        var full = board.full();

        assertThat(full).isTrue();
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
    void shouldThrowExceptionIfCoordinatesAlreadyTaken() {
        var board = new Board().take(new Turn(0, 0, 'X'));

        var error = catchThrowable(() -> board.take(new Turn(0, 0, 'O')));

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
        return new Board()
                .take(new Turn(0, 0, 'X'))
                .take(new Turn(0, 1, 'O'))
                .take(new Turn(0, 2, 'X'))
                .take(new Turn(1, 0, 'O'))
                .take(new Turn(1, 1, 'X'))
                .take(new Turn(1, 2, 'O'))
                .take(new Turn(2, 0, 'X'))
                .take(new Turn(2, 1, 'O'))
                .take(new Turn(2, 2, 'X'));
    }
}
