package battleship;

import java.util.Random;

public class Ocean {
	
	/** Used to quickly determine which ship is in any given location. */
	Ship[][] ships = new Ship[10][10];
	
	/** An array of booleans telling whether the user has fired on a certain location. */
	boolean[][] fired = new boolean[10][10];
	
	/** The total number of shots fired by the user.*/
	int shotsFired;
	
	/** 
	 * The number of times a shot hit a ship. If the user shoots the same 
	 * part of a ship more than once, every hit is counted, even though the 
	 * additional "hits" don't do the user any good.
	 */
	int hitCount;
	
	/**
	 * The constructor. Creates an "empty" ocean (fills the ships array with 
	 * EmptySeas). Also initializes any game variables, such as how many shots 
	 * have been fired.
	 */
	Ocean() {
		shotsFired = 0;
		hitCount = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ships[i][j] = new EmptySea();
				fired[i][j] = false;
			}
		}
	}
	
	/**
	 * Place all ten ships randomly on the (initially empty) ocean, making 
	 * certain that all ships are placed legally. 
	 */
	void placeAllShipsRandomly() {
		Ship[] myShips = {new Battleship(), new Cruiser(), new Cruiser(), new Destroyer(), 
				          new Destroyer(), new Destroyer(), new Submarine(), new Submarine(), 
				          new Submarine(), new Submarine()};
		Random random = new Random();
	    for (int i = 0; i < 10; ++i) {
	        Ship ship = myShips[i];
	        while (true) {
	            int x = random.nextInt(10);
	            int y = random.nextInt(10);
	            boolean horizontal = random.nextInt(2) == 0;
	            if (ship.okToPlaceShipAt(x, y, horizontal, this)) {
	                ship.placeShipAt(x, y, horizontal, this);
	                break;
	            }
	        }
	    }
	}
	
	/**
	 * Check if a given location is occupied by a ship.
	 * @param row An integer in the range of 0 to 9. 
	 * @param column An integer in the range of 0 to 9. 
	 * @return Returns true if the given location contains a ship, false if it
	 * does not or it is beyond the array.
	 */
	boolean isOccupied(int row, int column) {
		if (row < 0 || row > 9 || column < 0 || column > 9) {
	        return false;
	    }
		if (ships[row][column] instanceof EmptySea) {
		    return false;
		}
		return true;
	}
	
	/**
	 * Returns true if the given location contains a "real" ship, still afloat, 
	 * (not an EmptySea), false if it does not. In addition, this method updates 
	 * the number of shots that have been fired, and the number of hits.
	 * @param row An integer in the range of 0 to 9. 
	 * @param column An integer in the range of 0 to 9. 
	 * @return True if the given location contains a "real" ship and still afloat, 
	 * (not an EmptySea), false if it does not.
	 */
	boolean shootAt(int row, int column) {
		fired[row][column] = true;
		shotsFired++;
		if (ships[row][column].shootAt(row, column)) {
		    hitCount++;
		    return true;
		} else {
		    return false;   
		}
	}
	
	/**
	 * 
	 * @return The number of shots fired (in this game).
	 */
	int getShotsFired() {
		return shotsFired;
	}
	
	/**
	 * Returns the number of hits recorded (in this game). All hits are counted, 
	 * not just the first time a given square is hit. (Allow the user to get a 
	 * worse score by shooting again at the same location.)
	 * @return The number of hits recorded (in this game).
	 */
	int getHitCount() {
		return hitCount;
	}
	
	/**
	 * Check if the game is over.
	 * @return True if all ships have been sunk, otherwise false.
	 */
	boolean isGameOver() {
		int count = 0;
	    for (int i = 0; i < 10; ++i)
	        for (int j = 0; j < 10; ++j) {
	            if (ships[i][j].isSunk()) {
	                ++count;
	            }
	        }
	    return count == 20; 
	}
	
	/**
	 * @return The actual 10x10 array of ships, not a copy. 
	 */
	Ship[][] getShipArray() {
		return ships;
	}
	
	/**
	 * Print the ocean. To aid the user, row numbers should be displayed along 
	 * the left edge of the array, and column numbers should be displayed along 
	 * the top. Numbers should be 0 to 9, not 1 to 10. The top left corner square
	 * should be 0, 0. Use 'S' to indicate a location that you have fired upon 
	 * and hit a (real) ship, '-' to indicate a location that you have fired upon 
	 * and found nothing there, 'x' to indication location containing a sunken 
	 * ship, and '.' to indicate a location that you have never fired upon. 
	 */
	void print() {
		System.out.println("    0 1 2 3 4 5 6 7 8 9");
		System.out.println("   ---------------------");
		for (int i = 0; i < 10; i++) {
		    System.out.print(i + " |");
		    for (int j = 0; j < 10; j++) {
		        if (!fired[i][j]) {
		            System.out.print(" .");
		        } else {
		            System.out.print(" " + ships[i][j].toString());
		        }
		    }
		    System.out.println(" |");
		}
	}

}
