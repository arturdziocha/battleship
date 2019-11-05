package battleship.domain.point.dto;

import battleship.point.Point;
import io.vavr.control.Either;

public interface Builder {
    Either<String, Point> build();
}
