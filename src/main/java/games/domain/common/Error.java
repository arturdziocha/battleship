package games.domain.common;
import io.vavr.control.Either;

public interface Error {
    String getCause();
    default Either<Error, Success> toEitherLeft() {
        return Either.left(this);
    }
}
