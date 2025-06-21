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
import uk.co.mruoc.nac.board.BoardString;
import uk.co.mruoc.nac.result.Outcome;
import uk.co.mruoc.nac.token.Players;
import uk.co.mruoc.nac.turn.RandomTurns;
import uk.co.mruoc.nac.turn.StalemateFixedTurns;
import uk.co.mruoc.nac.turn.Turns;
import uk.co.mruoc.nac.turn.WinningFixedTurns;

@Slf4j
class RunnableGameTest {

    private final Board board = new Board();
    private final Players players = new Players();
    private final Outcome outcome = new Outcome();

    @ParameterizedTest(name = "{0}")
    @MethodSource("turns")
    void shouldPlayGame(Turns turns) {
        var game = new Game(board, players, turns, outcome);
        var runnable = new RunnableGame(game);

        var completeGame = runnable.run();

        assertThat(completeGame.playable()).isFalse();
        log.info("{}{}", System.lineSeparator(), new BoardString(completeGame.board()));
    }

    private static Stream<Arguments> turns() {
        return Stream.of(
                Arguments.of(Named.of("random", new RandomTurns())),
                Arguments.of(Named.of("stalemate-fixed", new StalemateFixedTurns())),
                Arguments.of(Named.of("winning-fixed", new WinningFixedTurns())));
    }
}
