//Hayden Collins
//CS 110 Final Project

import java.util.ArrayList;
import java.util.Random;

/**
 * UserBoard Class, extension of Board.
 */
public class UserBoard extends Board {
    private ArrayList<Move> moves;
    private Random rand;

    /**
     * A constructor that passes a given filename to the Board constructor. Initializes rand and moves instance variables.
     *
     * @param filename A String.
     */
    public UserBoard(String filename) {
        super(filename);
        this.rand = new Random();
        this.moves = new ArrayList<>();

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                moves.add(new Move(i, j));
            }
        }
    }

    /**
     * A method that randomly chooses a Move from the ArrayList of remaining moves.
     *
     * @return A Move object.
     */
    public Move pickRandomMove() {
        int randomIndex = rand.nextInt(moves.size());
        Move move = moves.get(randomIndex);
        moves.remove(randomIndex);
        return move;
    }

    /**
     * Picks a random move for the computer to play. Updates layout and Fleet.
     *
     * @return An Array of Strings.
     */
    public String[] makeComputerMove() {
        Move move = pickRandomMove();
        CellStatus originalCellStatus = applyMoveToLayout(move);
        String[] arr = {move.toString(), null};

        if (originalCellStatus.equals(CellStatus.CRUISER)) {
            if (getFleet().updateFleet(ShipType.ST_CRUISER)) {
                for (int i = 0; i <= 9; i++) {
                    for (int j = 0; j <= 9; j++) {
                        if (getLayout().get(i).get(j).equals(CellStatus.CRUISER_HIT)) {
                            getLayout().get(i).set(j, CellStatus.CRUISER_SUNK);
                        }
                    }
                }
                arr[1] = "You sank My Cruiser!";
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
                arr[1] = "You sank My Aircraft Carrier!";
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
                arr[1] = "You sank My Battleship!";
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
                arr[1] = "You sank My Sub!";
            }
        } else if (originalCellStatus.equals(CellStatus.DESTROYER)) {
            if (getFleet().updateFleet(ShipType.ST_DESTROYER)) {
                System.out.println("updateFleet returns true");
                for (int i = 0; i <= 9; i++) {
                    for (int j = 0; j <= 9; j++) {
                        if (getLayout().get(i).get(j).equals(CellStatus.DESTROYER_HIT)) {
                            getLayout().get(i).set(j, CellStatus.DESTROYER_SUNK);
                        }
                    }
                }
                arr[1] = "You sank My Destroyer!";
            }
        }
        return arr;
    }

    /**
     * A toString() method that returns a string representing the user's board state.
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
                str += getLayout().get(i).get(j).toString().substring(1, 2);
                str += "  ";

            }
        }
        return str;
    }
}
