package battleship.player;

import battleship.fleet.Fleet;

public class ConsolePlayer extends AbstractPlayer {
    public static class Builder {
        private String name;
        private Fleet fleet;        
        public Builder(String name, Fleet fleet) {
            this.name = name;
            this.fleet = fleet;
        }             
        public ConsolePlayer build(){            
            return new ConsolePlayer(this);
        }

    }

    private ConsolePlayer(Builder builder) {
        super();
        name = builder.name;  
        fleet = builder.fleet;
    }
}
