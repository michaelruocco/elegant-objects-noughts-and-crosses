package uk.co.mruoc.nac;

public class MinimumCoordinateValueException extends RuntimeException {

    public MinimumCoordinateValueException(String axis, long value, long minimum) {
        super(String.format("invalid %s axis coordinate %d must be greater than or equal to %d", axis, value, minimum));
    }
}
