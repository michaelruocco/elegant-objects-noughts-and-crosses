package uk.co.mruoc.nac.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

class BoardValidatorTest {

    @Test
    void shouldNotAllowBoardSizeLessThan3() {
        var board = new Board(2);
        var validator = new BoardValidator(board);

        var error = catchThrowable(validator::validate);

        assertThat(error)
                .isInstanceOf(MinimumBoardSizeException.class)
                .hasMessage("board size 2 must be greater than or equal to 3");
    }

    @Test
    void shouldNotAllowEvenBoardSize() {
        var board = new Board(4);
        var validator = new BoardValidator(board);

        var error = catchThrowable(validator::validate);

        assertThat(error)
                .isInstanceOf(BoardSizeOddNumberException.class)
                .hasMessage("board size 4 must be an odd number");
    }

    @Test
    void shouldNotThrowExceptionIfBoardIsValid() {
        var board = new Board();
        var validator = new BoardValidator(board);

        ThrowingCallable callable = validator::validate;

        assertThatCode(callable).doesNotThrowAnyException();
    }
}
