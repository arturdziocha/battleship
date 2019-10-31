package battleship.point.vavr;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import battleship.direction.vavr.Direction;
import io.vavr.control.Either;

class PointSetterTest {

    @Test
    @DisplayName("Should create 3 points up")
    void shouldCreate3PointsUp() {
        Point[] points = {
                new PointImpl.Builder("B2").build()
                        .get(),
                new PointImpl.Builder("b3").build()
                        .get(),
                new PointImpl.Builder(3, 1).build()
                        .get() };
        Either<String, PointSetter> pointSetter = new PointSetter.Builder(3)
                .startPoint(new PointImpl.Builder("b2").build())
                .direction(Direction.getFromShortName('d'))
                .build();
        assertThat(pointSetter.get().getPoints(), containsInAnyOrder(points));
    }

    @Test
    @DisplayName("Should throws Malformatted Exception when point is null")
    void shouldThrowsMalformattedExceptionWhenPointIsNull() {
        assertThrows(MalformattedException.class, () -> new PointsSetter.Builder(3).startPoint(null)
                .direction(Direction.DOWN)
                .build(), "Point cannot be empty");
    }

    @Test
    @DisplayName("Should throws Malformatted Exception when direction is null")
    void shouldThrowsMalformattedExceptionWhenDirectionIsNull() {
        assertThrows(MalformattedException.class,
                () -> new PointsSetter.Builder(3).startPoint(new PointImpl.Builder("b2").build())
                        .build(),
                "Direction cannot be empty");
    }

    @Test
    @DisplayName("Should throws Malformatted Exception when point and direction is null")
    void shouldThrowsMalformattedExceptionWhenPointAndDirectionIsNull() {
        assertThrows(MalformattedException.class, () -> new PointsSetter.Builder(3).build(),
                "Direction and point cannot be empty");
    }

}
