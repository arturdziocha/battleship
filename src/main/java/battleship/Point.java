package battleship;

public class Point {
	private Ship ship;
	private PointStatus status;

	public Point() {
		this.status = PointStatus.Empty;
	}

	public void placeShip(Ship ship) throws AlreadyOccupiedException {
		if (status == PointStatus.Empty) {
			this.ship = ship;
			this.status = PointStatus.Ship;
		} else if (status == PointStatus.Occupied || status == PointStatus.Ship) {
			throw new AlreadyOccupiedException();
		}
	}

	public Ship getShip() {
		return ship;
	}

	public PointStatus getStatus() {
		return status;
	}

	public void receiveShot() throws AlreadySunkEception {
		if (status == PointStatus.Empty) {
			status = PointStatus.Miss;
		}
		if (status == PointStatus.Ship && !ship.isSunk()) {
			ship.shot();
			this.status = PointStatus.Hit;

		}

	}

}
