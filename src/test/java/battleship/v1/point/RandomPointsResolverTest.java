package battleship.v1.point;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battleship.v1.MalformedException;
import battleship.v1.ship.ShipClass;

public class RandomPointsResolverTest {
    private PointsResolver pointsResolver;

    @BeforeEach
    void setUp() {
        pointsResolver = new RandomPointsResolver(ShipClass.DESTROYER);
    }

    @Test
    public void whenResolveShouldReturnRandomPointList() throws MalformedException {
        List<Point> pointList = pointsResolver.resolve();
        Assertions.assertEquals(3, pointList.size());
    }
}
