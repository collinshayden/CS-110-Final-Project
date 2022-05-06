//Hayden Collins
//CS 110 Final Project

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Board Class.
 */
public class Board {
    public int SIZE;
    private ArrayList<ArrayList<CellStatus>> layout;
    private Fleet fleet;

    /**
     * The constructor for Board. Initializes fleet and layout.
     *
     * @param filename A .txt file containing information about the location of the fleet.
     */
    public Board(String filename) {
        //initializing Fleet
        this.fleet = new Fleet();
        //initializing layout and setting all values in the 2d ArrayList to Nothing
        this.layout = new ArrayList<ArrayList<CellStatus>>();
        for (int i = 0; i <= 9; i++) {
            //rows
            layout.add(new ArrayList<CellStatus>());
            for (int j = 0; j <= 9; j++) {
                //cols
                layout.get(layout.size() - 1).add(CellStatus.NOTHING);
            }
        }

        //reading the file and setting the relevant cells to the respective values.
        try {
            Scanner infile = new Scanner(new File(filename));
            while (infile.hasNext()) {
                String line = infile.nextLine();
                int startCordRowNum = Move.letterToNum(line.substring(2, 3));
                int startCordColNum = Integer.parseInt(line.substring(3, 4)) - 1;
                int endCordRowNum = Move.letterToNum(line.substring(5, 6));
                int endCordColNum = Integer.parseInt(line.substring(6)) - 1;

                if (line.charAt(0) == 'C') {
                    //cruiser, length 3
                    layout.get(startCordRowNum).set(startCordColNum, CellStatus.CRUISER);
                    layout.get(endCordRowNum).set(endCordColNum, CellStatus.CRUISER);
                    if (startCordRowNum == endCordRowNum) {//if the ship is horizontal on a row
                        layout.get(startCordRowNum).set(startCordColNum + 1, CellStatus.CRUISER);
                    } else {//if the ship is vertical on a column
                        layout.get(startCordRowNum + 1).set(startCordColNum, CellStatus.CRUISER);
                    }
                } else if (line.charAt(0) == 'A') {
                    //aircraft carrier, length 5
                    layout.get(startCordRowNum).set(startCordColNum, CellStatus.AIRCRAFT_CARRIER);
                    layout.get(endCordRowNum).set(endCordColNum, CellStatus.AIRCRAFT_CARRIER);
                    if (startCordRowNum == endCordRowNum) {//if the ship is horizontal
                        layout.get(startCordRowNum).set(startCordColNum + 1, CellStatus.AIRCRAFT_CARRIER);
                        layout.get(startCordRowNum).set(startCordColNum + 2, CellStatus.AIRCRAFT_CARRIER);
                        layout.get(startCordRowNum).set(startCordColNum + 3, CellStatus.AIRCRAFT_CARRIER);
                    } else {//if the ship is vertical
                        layout.get(startCordRowNum + 1).set(startCordColNum, CellStatus.AIRCRAFT_CARRIER);
                        layout.get(startCordRowNum + 2).set(startCordColNum, CellStatus.AIRCRAFT_CARRIER);
                        layout.get(startCordRowNum + 3).set(startCordColNum, CellStatus.AIRCRAFT_CARRIER);


                    }
                } else if (line.charAt(0) == 'B') {
                    //battleship, length 4
                    layout.get(startCordRowNum).set(startCordColNum, CellStatus.BATTLESHIP);
                    layout.get(endCordRowNum).set(endCordColNum, CellStatus.BATTLESHIP);
                    if (startCordRowNum == endCordRowNum) {//if the ship is horizontal on a row
                        layout.get(startCordRowNum).set(startCordColNum + 1, CellStatus.BATTLESHIP);
                        layout.get(startCordRowNum).set(startCordColNum + 2, CellStatus.BATTLESHIP);
                    } else {//if the ship is vertical on a column
                        layout.get(startCordRowNum + 1).set(startCordColNum, CellStatus.BATTLESHIP);
                        layout.get(startCordRowNum + 2).set(startCordColNum, CellStatus.BATTLESHIP);
                    }
                } else if (line.charAt(0) == 'S') {
                    //sub, length 3
                    layout.get(startCordRowNum).set(startCordColNum, CellStatus.SUB);
                    layout.get(endCordRowNum).set(endCordColNum, CellStatus.SUB);
                    if (startCordRowNum == endCordRowNum) {//if the ship is horizontal on a row
                        layout.get(startCordRowNum).set(startCordColNum + 1, CellStatus.SUB);
                    } else {//if the ship is vertical on a column
                        layout.get(startCordRowNum + 1).set(startCordColNum, CellStatus.SUB);
                    }
                } else if (line.charAt(0) == 'D') {
                    //destroyer, length 2
                    layout.get(startCordRowNum).set(startCordColNum, CellStatus.DESTROYER);
                    layout.get(endCordRowNum).set(endCordColNum, CellStatus.DESTROYER);

                }
            }

        } catch (FileNotFoundException exception) {
            System.out.println(filename + " was not found. Please check that it is in the correct directory.");
        }
    }

    /**
     * Given a Move, applyMoveToLayout() will set the respective CellStatus in layout to one with a _HIT suffix.
     *
     * @param move A Move object.
     * @return A CellStatus object.
     */
    public CellStatus applyMoveToLayout(Move move) {
        CellStatus originalCellStatus = getLayout().get(move.row()).get(move.col());
        if (getLayout().get(move.row()).get(move.col()).equals(CellStatus.NOTHING)) {
            getLayout().get(move.row()).set(move.col(), CellStatus.NOTHING_HIT);
        } else if (getLayout().get(move.row()).get(move.col()).equals(CellStatus.CRUISER)) {
            getLayout().get(move.row()).set(move.col(), CellStatus.CRUISER_HIT);
        } else if (getLayout().get(move.row()).get(move.col()).equals(CellStatus.AIRCRAFT_CARRIER)) {
            getLayout().get(move.row()).set(move.col(), CellStatus.AIRCRAFT_CARRIER_HIT);
        } else if (getLayout().get(move.row()).get(move.col()).equals(CellStatus.BATTLESHIP)) {
            getLayout().get(move.row()).set(move.col(), CellStatus.BATTLESHIP_HIT);
        } else if (getLayout().get(move.row()).get(move.col()).equals(CellStatus.SUB)) {
            getLayout().get(move.row()).set(move.col(), CellStatus.SUB_HIT);
        } else if (getLayout().get(move.row()).get(move.col()).equals(CellStatus.DESTROYER)) {
            getLayout().get(move.row()).set(move.col(), CellStatus.DESTROYER_HIT);
        }

        return originalCellStatus;
    }

    /**
     * moveAvailable is given a Move and checks if that coordinate in layout has not already been hit.
     *
     * @param move A Move object.
     * @return A Boolean.
     */
    public boolean moveAvailable(Move move) {
        CellStatus cell = getLayout().get(move.row()).get(move.col());
        return (cell.equals(CellStatus.NOTHING) || cell.equals(CellStatus.CRUISER) || cell.equals(CellStatus.AIRCRAFT_CARRIER)
                || cell.equals(CellStatus.BATTLESHIP) || cell.equals(CellStatus.SUB) || cell.equals(CellStatus.DESTROYER));
    }

    /**
     * A getter for the layout instance variable.
     *
     * @return An ArrayList of ArrayLists that contain CellStatuses.
     */
    public ArrayList<ArrayList<CellStatus>> getLayout() {
        return layout;
    }

    /**
     * A getter for the instance variable Fleet.
     *
     * @return A Fleet object.
     */
    public Fleet getFleet() {
        return fleet;
    }

    /**
     * The gameOver() method calls Fleet.gameover() to determine if all ships have been sunk.
     *
     * @return A Boolean.
     */
    public boolean gameOver() {
        return getFleet().gameOver();
    }
}
