
package lightsoutgame;

/**
 * Contains all of the game actions for the user.
 * @author Greg Geary
 * @version 1
 */
class LightsOut {

    private int size;
    private boolean[][] grid;

    /**
     * Sets the size of the array representing the square game board.
     * @param size an integer that represents the game board's size.
     */
    public LightsOut(int size) {
        this.size = size;
        grid = new boolean[size][size];
    }

    /**
     * Gets the size of the game board.
     * @return an integer representing the size of the board.
     */
    public int getSize() {
        return size;
    }

    /**
     * Turns the five lights on: the center one and the four surrounding ones in a "+" pattern
     * @param row the row of the center light.
     * @param col the column of the center light.
     */
    public void press(int row, int col) {
        try
        {
            toggle(row, col);
        }
        catch (IndexOutOfBoundsException e){}
        try
        {
            toggle(row+1, col);
        }
        catch (IndexOutOfBoundsException e){}
        try
        {
            toggle(row-1, col);
        }
        catch (IndexOutOfBoundsException e){}
        try
        {
            toggle(row, col+1);
        }
        catch (IndexOutOfBoundsException e){}
        try
        {
            toggle(row, col-1);
        }
        catch (IndexOutOfBoundsException e){}
    }

    /**
     * Checks if the specified light is lit.
     * @param row the row of the light.
     * @param col the column of the light.
     * @return a boolean representing if the light is on (true) or off (false).
     */
    public Boolean isLit(int row, int col)
    {
        try
        {
            return grid[row][col];
        }
        catch (NullPointerException e)
        {
            return false;
        }
    }

    /**
     * Sets a light to a specific boolean value regardless of it's current value.
     * @param row the row of the light.
     * @param col the column of the light.
     * @param value the boolean value to which the light is to be set.
     */
    public void forceLit(int row, int col, boolean value) {
        grid[row][col] = value;
    }

    /**
     * If the selected light is on, turn it off. If the light is off, turn it on.
     * @param row the row of the light.
     * @param col the column of the light
     */
    private void toggle(int row, int col) {
        if (grid[row][col]) grid[row][col] = false;
        else grid[row][col] = true;
    }
}