//Hayden Collins
//CS 110 Final Project

/**
 * Fleet Class.
 */
public class Fleet {
    private Ship battleShip;
    private Ship aircraftCarrier;
    private Ship cruiser;
    private Ship sub;
    private Ship destroyer;

    /**
     * The Fleet constructor initializes each ship's instance variable as their respective class.
     */
    public Fleet() {
        this.battleShip = new Battleship();
        this.aircraftCarrier = new AircraftCarrier();
        this.cruiser = new Cruiser();
        this.sub = new Sub();
        this.destroyer = new Destroyer();
    }

    /**
     * Given a ShipType, updateFleet() calls the respective instance variable's .hit() method and returns true/false if that hit sunk the ship.
     *
     * @param type A ShipType object.
     * @return A Boolean.
     */
    public boolean updateFleet(ShipType type) {
        if (type == ShipType.ST_BATTLESHIP) {
            return battleShip.hit();
        } else if (type == ShipType.ST_AIRCRAFT_CARRIER) {
            return aircraftCarrier.hit();
        } else if (type == ShipType.ST_CRUISER) {
            return cruiser.hit();
        } else if (type == ShipType.ST_SUB) {
            return sub.hit();
        } else if (type == ShipType.ST_DESTROYER) {
            return destroyer.hit();
        } else {
            return false;
        }
    }

    /**
     * Returns true/false if all the ships in the Fleet have been sunk.
     *
     * @return A Boolean.
     */
    public boolean gameOver() {
        return battleShip.getSunk() && aircraftCarrier.getSunk() && cruiser.getSunk() && sub.getSunk() && destroyer.getSunk();
    }
}
