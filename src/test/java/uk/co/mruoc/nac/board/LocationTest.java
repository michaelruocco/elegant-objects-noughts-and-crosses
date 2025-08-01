package uk.co.mruoc.nac.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import uk.co.mruoc.nac.Location;
import uk.co.mruoc.nac.MinimumCoordinateValueException;

class LocationTest {

    @Test
    void shouldThrowExceptionIfXAxisCoordinateIsInvalid() {
        var coordinates = new Location(-1, 1);

        var error = catchThrowable(coordinates::validate);

        assertThat(error)
                .isInstanceOf(MinimumCoordinateValueException.class)
                .hasMessage("invalid x axis coordinate -1 must be greater than or equal to 0");
    }

    @Test
    void shouldThrowExceptionIfYAxisCoordinateIsInvalid() {
        var coordinates = new Location(1, -1);

        var error = catchThrowable(coordinates::validate);

        assertThat(error)
                .isInstanceOf(MinimumCoordinateValueException.class)
                .hasMessage("invalid y axis coordinate -1 must be greater than or equal to 0");
    }

    @Test
    void shouldNotThrowExceptionIfXAxisAndYAxisAreBothValid() {
        var coordinates = new Location(1, 1);

        ThrowingCallable callable = coordinates::validate;

        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Test
    void shouldReturnCoordinatesString() {
        var coordinates = new Location(2, 3);

        var s = coordinates.toString();

        assertThat(s).isEqualTo("x:2|y:3");
    }
}
