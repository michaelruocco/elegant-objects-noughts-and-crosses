package uk.co.mruoc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GameTest {

    private final Board board = new DefaultBoard().initialized();
    private final Players players = new Players();

    @ParameterizedTest(name = "{0}")
    @MethodSource("turns")
    void shouldPlayGame(Turns turns) {
        var game = new Game(board, players, turns);
        var runnable = new RunnableGame(game);

        var completeGame = runnable.run();

        assertThat(completeGame.playable()).isFalse();
        System.out.println(new BoardStateString(completeGame.boardState()));
    }

    private static Stream<Arguments> turns() {
        return Stream.of(
                Arguments.of(Named.of("random", new RandomTurns())), Arguments.of(Named.of("fixed", new FixedTurns())));
    }
}
