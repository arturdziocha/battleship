package battleship.player;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import battleship.point.PointStatus;

class ConsolePlayerTest {
    private ConsolePlayer player;

    @BeforeEach
    void setUp() throws Exception {
        this.player = new ConsolePlayer.Builder().name("artur")
                .build();
    }

    @Test
    @DisplayName("Test getShots should return expected array of PointStatus.EMPTY")
    void shouldReturnArrayOfPointStatusEmpty() {
        PointStatus[][] expected = new PointStatus[10][10];
        IntStream.range(0, 10)
                .forEach(x -> IntStream.range(0, 10)
                        .forEach(y -> expected[x][y] = PointStatus.EMPTY));
        assertArrayEquals(expected, player.getShots());

    }

}
