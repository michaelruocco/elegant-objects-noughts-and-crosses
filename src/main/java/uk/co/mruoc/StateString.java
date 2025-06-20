package uk.co.mruoc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StateString {

    private final ReadOnlyState.Smart state;

    public StateString(ReadOnlyState state) {
        this(new ReadOnlyState.Smart(state));
    }

    @Override
    public String toString() {
        return Stream.concat(Stream.of(header()), rows()).collect(Collectors.joining(System.lineSeparator()));
    }

    private String header() {
        var header = sizeIntStream().mapToObj(Integer::toString).collect(Collectors.joining(" "));
        return String.format("  %s", header);
    }

    private Stream<String> rows() {
        return sizeIntStream().mapToObj(this::row);
    }

    private String row(int y) {
        Collection<String> tokens = new ArrayList<>();
        tokens.add(Integer.toString(y));
        sizeIntStream().mapToObj(x -> state.token(x, y)).map(Token::value).forEach(tokens::add);
        return String.join(" ", tokens);
    }

    private IntStream sizeIntStream() {
        return IntStream.range(0, state.sizeValue());
    }
}
