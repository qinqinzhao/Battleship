package battleship;

public abstract class Ship {
	
	/** The row (0 to 9) which contains the bow (front) of the ship. */
	int bowRow; 
	
	/** The column (0 to 9) which contains the bow (front) of the ship. */
	int bowColumn; 
	
	/** The number of squares occupied by the ship. An "empty sea" location has length 1. */
	int length; 
	
	/** True if the ship occupies a single row, false otherwise. */
	boolean horizontal; 
	
	/**　
	 * An array of booleans telling whether that part of the ship has　been hit. 
	 * Only battleships use all four locations; cruisers use the first three;
	 * destroyers 2; submarines 1; and "empty sea" either one or none. 
	 */
	boolean[] hit = {false, false, false, false}; 

    /**
     * @return The length of this particular ship. 
     * (An abstract "ship" doesn't have a fixed length.)
     */
	abstract int getLength();
	
	/**
	 * @return The given bowRow.
	 */
	int getBowRow() {
		return bowRow;
	}
	
	/**
	 * @return The given bowColumn.
	 */
	int getBowColumn() {
		return bowColumn;
	}
	
	/**
	 * @return The given value of horizontal.
	 */
	boolean isHorizontal() {
		return horizontal;
	}
	
	/**
	 * Set the value of bowRow.
	 * @param row An integer in the range of 0 to 9. 
	 */
	void setBowRow(int row) {
		this.bowRow = row;
	}
	
	/**
	 * Set the value of bowColumn.
	 * @param column An integer in the range of 0 to 9. 
	 */
	void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	/**
	 * Set the value of the instance variable horizontal.
	 * @param column
	 */
	void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	/**
     * @return The type of this ship. (An abstract "ship" doesn't have a type.)
     */
	abstract String getShipType();
	
	/**
	 * Check if it is legal to put a ship of this length with its bow in this location, 
	 * with the given orientationThe ship must not overlap another ship, or touch another 
	 * ship (vertically, horizontally, or diagonally), and it must not "stick out" beyond 
	 * the array. 
	 * @param row An integer in the range of 0 to 9. 
	 * @param column An integer in the range of 0 to 9. 
	 * @param horizontal A boolean value.
	 * @param ocean An Ocean object.
	 * @return True if it is legal, False otherwise. 
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){
		if (horizontal) {
		    if (column + getLength() > 10) {
		        return false;
		    }
			for (int column1 = column - 1; column1 <= column + getLength(); column1++) {
				if (ocean.isOccupied(row - 1, column1) || ocean.isOccupied(row, column1) || ocean.isOccupied(row + 1, column1)) {
					return false;
				}
			}
			return true;
		} else {
		    if (row + getLength() > 10) {
		        return false;
		    }
			for (int row1 = row - 1; row1 <= row + getLength(); row1++) {
				if (ocean.isOccupied(row1, column - 1) || ocean.isOccupied(row1, column) || ocean.isOccupied(row1, column + 1)) {
				    return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * "Puts" the ship in the ocean. This involves giving values to the bowRow, bowColumn, 
	 * and horizontal instance variables in the ship, and it also involves putting a 
	 * reference to the ship in each of 1 or more locations (up to 4) in the ships array 
	 * in the Ocean object. 
	 * @param row An integer in the range of 0 to 9. 
	 * @param column An integer in the range of 0 to 9. 
	 * @param horizontal A boolean value.
	 * @param ocean An Ocean object.
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		setBowRow(row);
		setBowColumn(column);
		setHorizontal(horizontal);	    
	    Ship[][] ships = ocean.getShipArray();
		if (horizontal) {
		    for (int i = column; i < column + getLength(); i++) {
	            ships[row][i] = this;
	        }
		} else {
		    for (int i = row; i < row + getLength(); i++) {
		        ships[i][column] = this;
		    }
		}
	}
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn't already 
	 * been sunk, mark that part of the ship as "hit" (in the hit array, 0 indicates the bow) 
	 * and return true, otherwise return false.
	 * @param row An integer in the range of 0 to 9. 
	 * @param column An integer in the range of 0 to 9. 
	 * @return True if a part of the ship occupies the given row and column and the ship 
	 * hasn't already been sunk, false otherwise.
	 */
	boolean shootAt(int row, int column) {
		if (!this.isSunk()) {
		    if (this.horizontal) {
		    	hit[column - this.bowColumn] = true;
		    	return true;
		    } else {
		    	hit[row - this.bowRow] = true;
		    	return true;
		    }
	    } else {
		    return false;
	    }
	}
	
	/**
	 * Check if the ship has been sunk.
	 * @return True if every part of the ship has been hit, false otherwise.
	 */
	boolean isSunk() {
		for (int i = 0; i < getLength(); i++) {
		    if (this.hit[i] == false) {
		        return false;
		    }
		}
		return true;
	}
	
	/**
	 * Returns a single-character String to use in the Ocean's print method.
	 * This method should return "x" if the ship has been sunk, "S" if it has not been sunk. 
	 * This method can be used to print out locations in the ocean that have been shot at; 
	 * it should not be used to print locations that have not been shot at. 
	 */
	public String toString(){
		if (this.isSunk()) {
			return "x";
		} else {
			return "S";
		}
	}

}
