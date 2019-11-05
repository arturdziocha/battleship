package battleship.domain.common;

import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Success {
    private String message;

    public Either<Error, Success> toEitherRight() {
        return Either.right(this);
    }
}
