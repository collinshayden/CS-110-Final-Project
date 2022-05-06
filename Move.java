//Hayden Collins
//CS 110

/**
 * Move Class.
 */
public class Move {
    public static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private int row;
    private int col;

    /**
     * A constructor for Move that takes two integer indices representing a coordinate on the Board layout.
     *
     * @param row An integer.
     * @param col An integer.
     */
    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * A constructor for Move that takes a String for Move representing a coordinate on the Board layout.
     *
     * @param move A String.
     */
    public Move(String move) {
        this.row = letterToNum(move.substring(0, 1));
        this.col = Integer.parseInt(move.substring(1)) - 1;
    }

    /**
     * A method to convert a letter coordinate into an integer coordinate.
     *
     * @param letter A String.
     * @return An integer.
     */
    public static int letterToNum(String letter) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i].equals(letter)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * A toString() method that converts the two integer indices into String form.
     *
     * @return A String.
     */
    public String toString() {
        return String.format("%s%d", letters[row()], col() + 1);
    }

    /**
     * A getter for the row instance variable.
     *
     * @return An integer.
     */
    public int row() {
        return row;
    }

    /**
     * A getter for the row instance variable.
     *
     * @return An integer.
     */
    public int col() {
        return col;
    }
}
