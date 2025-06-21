package uk.co.mruoc.nac.result;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CachedLinesFactory implements LinesFactory {

    private final LinesFactory factory;
    private final Collection<Line> lines;

    public CachedLinesFactory(int size) {
        this(new DefaultLinesFactory(size), new ConcurrentLinkedQueue<>());
    }

    public Collection<Line> build() {
        if (lines.isEmpty()) {
            lines.addAll(factory.build());
        }
        return lines;
    }
}
