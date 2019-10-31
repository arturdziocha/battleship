package battleship.point.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import battleship.point.PointImpl;
import io.vavr.control.Either;

class PointDecoderTest {
    // getRow testing
    @Test
    @DisplayName("Given empty string to getRow() should return Either.left")
    void givenEmptyStringToGetRowShouldReturnEiherLeft() {
        Either<String, Integer> row = PointDecoder.getRow("");
        assertTrue(row.isLeft());
        assertFalse(row.isRight());
        assertEquals("String cannot be empty", row.getLeft());
    }

    @Test
    @DisplayName("Given not integer should return Either.left")
    void givenNonIntegerShouldReturnEitherLeft() {
        Either<String, Integer> row = PointDecoder.getRow("ac");
        assertTrue(row.isLeft());
        assertFalse(row.isRight());
        assertEquals("You have to set Row position", row.getLeft());
    }

    @Test
    @DisplayName("Given integer should not equals another int")
    void givenIntegerStringShouldNotEqualsAnotherInt() {
        Either<String, Integer> row = PointDecoder.getRow("a5");
        assertNotEquals(1, row.get());
    }

    @ParameterizedTest(name = "Is  the same X(row) when given String as row")
    @CsvSource({ "a1,0", "a2,1", "a3,2", "a4,3", "a5,4", "a6,5", "a7,6", "a8,7", "a9,8", "a10,9" })
    void shouldEqualsWhentherIsTheSameFromInput(String x, int y) {
        Either<String, Integer> row = PointDecoder.getRow(x);
        assertEquals(y, row.get());
    }

    // getColumntesting
    @Test
    @DisplayName("Given empty string to getColumn() should return Either.left")
    void givenEmptyStringToGetColumnShouldReturnEiherLeft() {
        Either<String, Integer> column = PointDecoder.getColumn("");
        assertTrue(column.isLeft());
        assertFalse(column.isRight());
        assertEquals("String cannot be empty", column.getLeft());
    }

    @Test
    @DisplayName("Given not string should return Either.left")
    void givenNoGoodStringShouldReturnEitherLeft() {
        Either<String, Integer> column = PointDecoder.getColumn("[c");
        assertTrue(column.isLeft());
        assertFalse(column.isRight());
        assertEquals("Wrong column specified", column.getLeft());
    }

    @Test
    @DisplayName("Given integer should not equals another int")
    void givenWringStringShouldNotEqualsAnotherInt() {
        Either<String, Integer> column = PointDecoder.getRow("a5");
        assertNotEquals(1, column.get());
    }

    @ParameterizedTest(name = "Is  the same X(row) when given String as row")
    @CsvSource({ "a1,0", "b2,1", "c3,2", "D4,3", "E5,4", "F6,5", "g7,6", "h8,7", "i9,8", "J10,9" })
    void shouldEqualWhenIsTheSameStringFromInput(String x, int y) {
        Either<String, Integer> column = PointDecoder.getColumn(x);
        assertEquals(y, column.get());
    }

    // Testing pointToString
    @Test
    @DisplayName("Given column outside board should return Either.left")
    void givenColumnOutsideBoardShouldReturnEitherLeft() {
        Either<String, String> point = PointDecoder.pointToString(new PointImpl.Builder(99, 99).build());
        assertTrue(point.isLeft());
        assertFalse(point.isRight());
        assertEquals("Cannot find Column for this point", point.getLeft());
    }

    @Test
    @DisplayName("Given given good point should return String")
    void givenGoodPointShouldReturnGoodString() {
        Either<String, String> point = PointDecoder.pointToString(new PointImpl.Builder(3, 1).build());
        assertEquals("B4", point.get());
    }

}
