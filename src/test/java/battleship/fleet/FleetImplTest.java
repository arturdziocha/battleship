package battleship.fleet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import battleship.direction.Direction;
import battleship.fleet.Fleet;
import battleship.fleet.FleetImpl;
import battleship.point.PointImpl;
import battleship.ship.Ship;
import battleship.ship.ShipClass;
import battleship.ship.ShipImpl;
import io.vavr.collection.List;
import io.vavr.control.Either;

class FleetImplTest {
	private Fleet fleet;

	@BeforeEach
	void setUp() {
		fleet = new FleetImpl.Builder().build();
	}

	@Test
	@DisplayName("Should return Either.left when place null ship")
	void shouldReturnEitherLeftWhenPlaceNullShip() {
		Either<String, List<Ship>> ships = fleet.placeShip(null);
		assertTrue(ships.isLeft());
		assertEquals("Ship cannot be null", ships.getLeft());
	}

	@Test
	@DisplayName("getShips() should return one ship when one is placed")
	void shouldReturn1When1ShipPlaced() {
		Either<String, Ship> ship = new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(6, 6).build()
		        .get(), Direction.DOWN).build();
		Either<String, List<Ship>> placedShipsEither = fleet.placeShip(ship.get());
		assertTrue(placedShipsEither.isRight());
		assertEquals(1, fleet.getShips()
		        .size());
	}

	@Test
	@DisplayName("When place ship already placed shoutld Return Either.left")
	void shouldReturnEitherLeftWhenShipIsAlreadyplaced() {
		Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(5, 5).build()
		        .get(), Direction.DOWN).build()
		                .get();
		Ship isPlacedShip = new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(9, 7).build()
		        .get(), Direction.UP).build()
		                .get();
		Either<String, List<Ship>> placedShip = fleet.placeShip(ship);
		Either<String, List<Ship>> isPlaced = fleet.placeShip(isPlacedShip);
		assertTrue(isPlaced.isLeft());
		assertEquals("Ship is aleady placed!.", isPlaced.getLeft());
	}

	@Test
	@DisplayName("When place ship and ship is outside bottom board then return Either.left")
	void shouldReturnEitherLeftWhenShipPointBottomOutsideBoard() {
		Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(9, 7).build()
		        .get(), Direction.DOWN).build()
		                .get();
		Either<String, List<Ship>> placedShip = fleet.placeShip(ship);
		assertTrue(placedShip.isLeft());
		assertEquals("Ship is outside board", placedShip.getLeft());
	}

	@Test
	@DisplayName("When place ship and ship is outside top board then return Either.left")
	void shouldReturnEitherLeftWhenShipPointTopOutsideBoard() {
		Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(1, 8).build()
		        .get(), Direction.UP).build()
		                .get();
		Either<String, List<Ship>> placedShip = fleet.placeShip(ship);
		assertTrue(placedShip.isLeft());
		assertEquals("Ship is outside board", placedShip.getLeft());
	}

	@Test
	@DisplayName("When place ship and ship is outside right board then return Either.left")
	void shouldReturnEitherLeftWhenShipPointRightOutsideBoard() {
		Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(1, 8).build()
		        .get(), Direction.RIGHT).build()
		                .get();
		Either<String, List<Ship>> placedShip = fleet.placeShip(ship);
		assertTrue(placedShip.isLeft());
		assertEquals("Ship is outside board", placedShip.getLeft());
	}

	@Test
	@DisplayName("When place ship and ship is outside left board then return Either.left")
	void shouldReturnEitherLeftWhenShipPointLefttOutsideBoard() {
		Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(2, 1).build()
		        .get(), Direction.LEFT).build()
		                .get();
		Either<String, List<Ship>> placedShip = fleet.placeShip(ship);
		assertTrue(placedShip.isLeft());
		assertEquals("Ship is outside board", placedShip.getLeft());
	}

	@Test
	@DisplayName("Should put two ships in Fleet")
	void shouldPutShipInFleetWhenShipIsNotToCloseShips() {
		Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(1, 1).build()
		        .get(), Direction.RIGHT).build()
		                .get();
		Ship notToClose = new ShipImpl.Builder(ShipClass.SUBMARINE, new PointImpl.Builder(3, 1).build()
		        .get(), Direction.RIGHT).build()
		                .get();
		fleet.placeShip(ship);
		fleet.placeShip(notToClose);
		assertEquals(2, fleet.getShips()
		        .size());
	}

	@Test
	@DisplayName("placeShip() should return Either.left when ship is to close another")
	void shouldReturnEitherLeftWhenShipIsToCloseShips() {
		Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(1, 1).build()
		        .get(), Direction.RIGHT).build()
		                .get();
		Ship toClose = new ShipImpl.Builder(ShipClass.SUBMARINE, new PointImpl.Builder(2, 1).build()
		        .get(), Direction.RIGHT).build()
		                .get();
		Either<String, List<Ship>> placedShip = fleet.placeShip(ship);
		Either<String, List<Ship>> placedToClose = fleet.placeShip(toClose);
		assertTrue(placedToClose.isLeft());
		assertEquals("Ship is to close another placed ship", placedToClose.getLeft());
	}

	@Test
	@DisplayName("Should eturn five ships not placed yet")
	void shouldReturnFiveShipsNotPlacedYet() {
		ShipClass[] shipClassesNotPlaced = { ShipClass.BARCA, ShipClass.BATTLESHIP, ShipClass.CARRIER,
		        ShipClass.PATROL_BOAT, ShipClass.SUBMARINE };
		Ship ship = new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(1, 1).build()
		        .get(), Direction.RIGHT).build()
		                .get();
		fleet.placeShip(ship);
		assertThat(fleet.shipsToPlace(), containsInAnyOrder(shipClassesNotPlaced));

	}

	@Test
	@DisplayName("Should return true when all ships placed")
	void shouldReturnTrueWhenAllShipsPlaced() {
		Ship[] ships = { new ShipImpl.Builder(ShipClass.BARCA, new PointImpl.Builder(0, 0).build()
		        .get(), Direction.DOWN).build()
		                .get(),
		        new ShipImpl.Builder(ShipClass.PATROL_BOAT, new PointImpl.Builder(0, 2).build()
		                .get(), Direction.DOWN).build()
		                        .get(),
		        new ShipImpl.Builder(ShipClass.SUBMARINE, new PointImpl.Builder(0, 4).build()
		                .get(), Direction.DOWN).build()
		                        .get(),
		        new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(0, 6).build()
		                .get(), Direction.DOWN).build()
		                        .get(),
		        new ShipImpl.Builder(ShipClass.BATTLESHIP, new PointImpl.Builder(0, 8).build()
		                .get(), Direction.DOWN).build()
		                        .get(),
		        new ShipImpl.Builder(ShipClass.CARRIER, new PointImpl.Builder(7, 0).build()
		                .get(), Direction.RIGHT).build()
		                        .get() };
		for (Ship ship : ships) {
			Either<String, List<Ship>> placed = fleet.placeShip(ship);
		}
		assertTrue(fleet.isAllShipsPlaced());
	}

	@Test
	@DisplayName("Should return false when all ships placed")
	void shouldReturnFalseWhenAllShipsPlaced() {
		Ship[] ships = { new ShipImpl.Builder(ShipClass.BARCA, new PointImpl.Builder(0, 0).build()
		        .get(), Direction.DOWN).build()
		                .get(),
		        new ShipImpl.Builder(ShipClass.PATROL_BOAT, new PointImpl.Builder(0, 2).build()
		                .get(), Direction.DOWN).build()
		                        .get(),
		        new ShipImpl.Builder(ShipClass.SUBMARINE, new PointImpl.Builder(0, 4).build()
		                .get(), Direction.DOWN).build()
		                        .get(),
		        new ShipImpl.Builder(ShipClass.DESTROYER, new PointImpl.Builder(0, 6).build()
		                .get(), Direction.DOWN).build()
		                        .get(),
		        new ShipImpl.Builder(ShipClass.BATTLESHIP, new PointImpl.Builder(0, 8).build()
		                .get(), Direction.DOWN).build()
		                        .get() };
		for (Ship ship : ships) {
			Either<String, List<Ship>> placed = fleet.placeShip(ship);
		}
		assertFalse(fleet.isAllShipsPlaced());
	}
	@Test
    void shouldReturnTrueWhenPlaceAllShipsRandom() {
        fleet.placeAllShipsRandom();
        assertEquals(6, fleet.getShips().size());
        
        assertTrue(fleet.isAllShipsPlaced());
    }

}
