package battleship;

public class EmptySea extends Ship {
	
	int length = 1;
	
	/**
	 * @return Always false to indicate that nothing was hit.
	 */
    @Override
    boolean shootAt(int row, int column) {
        return false;
    }
    
    /**
     * @return Always false to indicate that the user didn't sink anything.
     */
    @Override
    boolean isSunk() {
        return false;
    }
    
    /**
     * @return A single-character String "-" to use in the Ocean's print method.
     */
    @Override
    public String toString() {
        return "-";
    }
