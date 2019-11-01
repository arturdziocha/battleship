package battleship.point.vavr;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import battleship.direction.vavr.Direction;
import io.vavr.control.Either;

class PointSetterTest {

	@Test
	@DisplayName("Should create 3 points up")
	void shouldCreate3PointsUp() {
		Point[] points = { new PointImpl.Builder("B2").build()
		        .get(),
		        new PointImpl.Builder("b3").build()
		                .get(),
		        new PointImpl.Builder(3, 1).build()
		                .get() };
		Either<String, PointSetter> pointSetter = new PointSetter.Builder(3, new PointImpl.Builder("b2").build()
		        .get(), Direction.DOWN).build();
		assertThat(pointSetter.get()
		        .getPoints(), containsInAnyOrder(points));
	}

}
