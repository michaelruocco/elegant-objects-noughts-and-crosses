package uk.co.mruoc.nac.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CachedLinesFactoryTest {

    @Test
    void shouldOnlyCreateLinesOnFirstCall() {
        var factory = new CachedLinesFactory(3);
        var lines1 = factory.build();

        var lines2 = factory.build();

        assertThat(lines1).isEqualTo(lines2);
    }
}
