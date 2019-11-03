package battleship.game;

public interface Game {

    void play();

    void setupGame();

    boolean isStillGameOn();

    void showWinner();
}
