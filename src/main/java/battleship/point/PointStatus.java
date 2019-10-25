package battleship.point;

public enum PointStatus {
    EMPTY(" "), MISS("M"), OCCUPIED("O"), HIT("X"), SUNK("S");
    private final String status;

    PointStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
