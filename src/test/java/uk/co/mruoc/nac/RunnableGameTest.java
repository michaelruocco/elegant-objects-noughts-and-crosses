package uk.co.mruoc.nac;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.co.mruoc.Game;
import uk.co.mruoc.RunnableGame;
import uk.co.mruoc.nac.board.Board;
import uk.co.mruoc.nac.board.DefaultBoard;
import uk.co.mruoc.nac.board.StateString;
import uk.co.mruoc.nac.token.Players;
import uk.co.mruoc.nac.turn.FixedTurns;
import uk.co.mruoc.nac.turn.RandomTurns;
import uk.co.mruoc.nac.turn.Turns;

@Slf4j
class RunnableGameTest {

    private final Board board = new DefaultBoard().initialized();
    private final Players players = new Players();

    @ParameterizedTest(name = "{0}")
    @MethodSource("turns")
    void shouldPlayGame(Turns turns) {
        var game = new Game(board, players, turns);
        var runnable = new RunnableGame(game);

        var completeGame = runnable.run();

        assertThat(completeGame.playable()).isFalse();
        log.info("{}{}", System.lineSeparator(), new StateString(completeGame.boardState()));
    }

    private static Stream<Arguments> turns() {
        return Stream.of(
                Arguments.of(Named.of("random", new RandomTurns())), Arguments.of(Named.of("fixed", new FixedTurns())));
    }
}
