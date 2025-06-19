package uk.co.mruoc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;

class CoordinatesTest {

    @Test
    void shouldThrowExceptionIfXAxisCoordinateIsInvalid() {
        var coordinates = new Coordinates(-1, 1);

        var error = catchThrowable(coordinates::validate);

        assertThat(error).isInstanceOf(IllegalStateException.class).hasMessage("invalid x axis coordinate value -1");
    }

    @Test
    void shouldThrowExceptionIfYAxisCoordinateIsInvalid() {
        var coordinates = new Coordinates(1, -1);

        var error = catchThrowable(coordinates::validate);

        assertThat(error).isInstanceOf(IllegalStateException.class).hasMessage("invalid y axis coordinate value -1");
    }

    @Test
    void shouldReturnCoordinatesString() {
        var coordinates = new Coordinates(2, 3);

        var s = coordinates.toString();

        assertThat(s).isEqualTo("x:2-y:3");
    }
}
