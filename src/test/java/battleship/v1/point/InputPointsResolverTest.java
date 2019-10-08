package battleship.v1.point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battleship.v1.MalformedException;
import battleship.v1.ship.ShipClass;

class InputPointsResolverTest {
    private PointsResolver resolver;

    @BeforeEach
    void setUp() throws Exception {
        resolver = new InputPointsResolver(ShipClass.DESTROYER, "D3", "l");
    }

    @Test
    void whenInstantiatedThenStartPointInitialized() {
        Point point = new PointImpl(2, 3);
        assertEquals(point, resolver.getStartPoint());
    }

    @Test
    void whenInstantiatedThenDirectionInitialized() {
        Direction direction = Direction.LEFT;
        assertEquals(direction, resolver.getDirection());
    }

    @Test
    void whenResolveReturnPointList() throws MalformedException {
        Point[] points = { new PointImpl(2, 1), new PointImpl(2, 2), new PointImpl(2, 3) };
        assertThat(resolver.resolve(), containsInAnyOrder(points));
    }

}
