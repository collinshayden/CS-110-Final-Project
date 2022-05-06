//Hayden Collins
//CS 110 Final Project

/**
 * ComputerBoard Class, an extension of Board.
 */
public class ComputerBoard extends Board {

    /**
     * The constructor for ComputerBoard passes the given filename to Board's constructor.
     *
     * @param filename A String.
     */
    public ComputerBoard(String filename) {
        super(filename);
    }

    /**
     * Given a Move, this method determines if that Move is available, and if so, updates Fleet if that Move hits a ship.
     *
     * @param move A Move object.
     * @return A String.
     */
    public String makePlayerMove(Move move) {
        if (moveAvailable(move)) {
            CellStatus originalCellStatus = applyMoveToLayout(move);
            if (originalCellStatus.equals(CellStatus.CRUISER)) {
                if (getFleet().updateFleet(ShipType.ST_CRUISER)) {
                    for (int i = 0; i <= 9; i++) {
                        for (int j = 0; j <= 9; j++) {
                            if (getLayout().get(i).get(j).equals(CellStatus.CRUISER_HIT)) {
                                getLayout().get(i).set(j, CellStatus.CRUISER_SUNK);
                            }
                        }
                    }
                    return "You sank My Cruiser!";
                }
            } else if (originalCellStatus.equals(CellStatus.AIRCRAFT_CARRIER)) {
                if (getFleet().updateFleet(ShipType.ST_AIRCRAFT_CARRIER)) {
                    for (int i = 0; i <= 9; i++) {
                        for (int j = 0; j <= 9; j++) {
                            if (getLayout().get(i).get(j).equals(CellStatus.AIRCRAFT_CARRIER_HIT)) {
                                getLayout().get(i).set(j, CellStatus.AIRCRAFT_CARRIER_SUNK);
                            }
                        }
                    }
                    return "You sank My Aircraft Carrier!";
                }
            } else if (originalCellStatus.equals(CellStatus.BATTLESHIP)) {
                if (getFleet().updateFleet(ShipType.ST_BATTLESHIP)) {
                    for (int i = 0; i <= 9; i++) {
                        for (int j = 0; j <= 9; j++) {
                            if (getLayout().get(i).get(j).equals(CellStatus.BATTLESHIP_HIT)) {
                                getLayout().get(i).set(j, CellStatus.BATTLESHIP_SUNK);
                            }
                        }
                    }
                    return "You sank My Battleship!";
                }
            } else if (originalCellStatus.equals(CellStatus.SUB)) {
                if (getFleet().updateFleet(ShipType.ST_SUB)) {
                    for (int i = 0; i <= 9; i++) {
                        for (int j = 0; j <= 9; j++) {
                            if (getLayout().get(i).get(j).equals(CellStatus.SUB_HIT)) {
                                getLayout().get(i).set(j, CellStatus.SUB_SUNK);
                            }
                        }
                    }
                    return "You sank My Sub!";
                }
            } else if (originalCellStatus.equals(CellStatus.DESTROYER)) {
                if (getFleet().updateFleet(ShipType.ST_DESTROYER)) {
                    for (int i = 0; i <= 9; i++) {
                        for (int j = 0; j <= 9; j++) {
                            if (getLayout().get(i).get(j).equals(CellStatus.DESTROYER_HIT)) {
                                getLayout().get(i).set(j, CellStatus.DESTROYER_SUNK);
                            }
                        }
                    }
                    return "You sank My Destroyer!";
                }
            }
        }
        return null;
    }

    /**
     * The toString() method returns a string representing the board state of the ComputerBoard.
     *
     * @return A String.
     */
    public String toString() {
        String str = "";
        for (int i = 1; i <= 10; i++) {
            str += "  " + i;
        }

        for (int i = 0; i <= 9; i++) {
            str += "\n" + Move.letters[i] + " ";
            for (int j = 0; j <= 9; j++) {
                str += getLayout().get(i).get(j).toString().substring(0, 1);
                str += "  ";
            }
        }
        return str;
    }
}
