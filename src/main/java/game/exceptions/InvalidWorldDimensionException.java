package game.exceptions;

public class InvalidWorldDimensionException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public InvalidWorldDimensionException(String exception) {
        super(exception);
    }
}
