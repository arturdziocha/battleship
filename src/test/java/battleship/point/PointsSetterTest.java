package battleship.point;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import org.junit.jupiter.api.Test;

import battleship.direction.Direction;
import battleship.exception.MalformattedException;

class PointsSetterTest {
    @Test
    void shouldCreate3PointsUp() throws MalformattedException {
        Point[] points = {new Point.Builder("B2").build(), new Point.Builder("b3").build(), new Point.Builder(3,1).build()};
        PointsSetter pointSetter = new PointsSetter.Builder(3).startPoint(new Point.Builder("b2").build()).direction(Direction.DOWN).build();
        assertThat(pointSetter.getPoints(), containsInAnyOrder(points));
    }

}