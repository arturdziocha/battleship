package battleship.point;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import battleship.direction.Direction;
import battleship.exception.MalformattedException;

class PointsSetterTest {
    @Test
    @DisplayName("Should create 3 points up")
    void shouldCreate3PointsUp() throws MalformattedException {
        Point[] points = {
                new Point.Builder("B2").build(),
                new Point.Builder("b3").build(),
                new Point.Builder(3, 1).build() };
        PointsSetter pointSetter = new PointsSetter.Builder(3).startPoint(new Point.Builder("b2").build())
                .direction(Direction.DOWN)
                .build();
        assertThat(pointSetter.getPoints(), containsInAnyOrder(points));
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
        assertThrows(MalformattedException.class, () -> new PointsSetter.Builder(3).startPoint(new Point.Builder("b2").build())
                .build(), "Direction cannot be empty");
    }

    @Test
    @DisplayName("Should throws Malformatted Exception when point and direction is null")
    void shouldThrowsMalformattedExceptionWhenPointAndDirectionIsNull() {
        assertThrows(MalformattedException.class, () -> new PointsSetter.Builder(3).build(),
                "Direction and point cannot be empty");
    }

}