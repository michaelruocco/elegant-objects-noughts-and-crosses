package uk.co.mruoc.nac.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BoardStringTest {

    @Test
    void shouldDisplayEmptyBoardStateAsString() {
        var state = new EmptyBoard();
        var stateString = new BoardString(state);

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
        var board = new BoardFiller().fill();
        var state = new BoardString(board);

        var string = state.toString();

        assertThat(string)
                .isEqualTo(
                        """
                            0 1 2
                          0 X X X
                          1 X X X
                          2 X X X""");
    }
}
