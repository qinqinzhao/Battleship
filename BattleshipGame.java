package battleship;

import java.util.Scanner;

public class BattleshipGame {
    
	/**
	 * The main method that runs the Battleship game.
	 * @param args Not used.
	 */
    public static void main(String args[]) {
       BattleshipGame game = new BattleshipGame();
       while(game.runGame()) {
           continue;
       }
    }
    
    /**
     * Run the game: set up the game; accept "shots" from the user; display the results; 
     * print final scores; and ask the user if he/she wants to play again. 
     * @return True if the user enters a string that starts with a "y", false otherwise.
     */
    public boolean runGame() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        Scanner sc = new Scanner(System.in);
        ocean.print();
        while (!ocean.isGameOver()) {
        	System.out.println("Please enter two integers between 0 and 9 (0 and 9 included), "
        			+ "one per line (use Enter to break lines).");
        	int x = getNextInt(sc);
        	int y = getNextInt(sc);
            if (ocean.shootAt(x, y)) {
                System.out.println("hit");
                Ship[][] ships = ocean.getShipArray();
                if (ships[x][y].isSunk()) {
                    System.out.println("You just sank a " + ships[x][y].getShipType() + ".");
                }
            } else {
                System.out.println("miss");
            }
            ocean.print();
        }
        System.out.println("Your final score is " + ocean.getShotsFired() + ".");
        System.out.println("Do you want to play again?");
        String answer = sc.nextLine();
        return answer.toLowerCase().startsWith("y");
    }
    
    /**
     * Catch exceptions of user inputs that are not integers. 
     * @param sc User input. 
     * @return The integer that user inputs.
     */
    int getNextInt(Scanner sc) {
        int x;
        while (true) {
            try {
                x = sc.nextInt();
                if (!isLegalNumber(x)) {
                    System.out.println("Both integers should be between 0 and 9 (0 and 9 included).");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println ("Please enter an integer!");
                sc.nextLine();
            }
        }
        return x;
    }
    
    /**
     * Check if the integer that user inputs is between 0 and 9. 
     * @param x The integer that user inputs.
     * @return True if the integers is between 0 and 9, false otherwise.
     */
    boolean isLegalNumber(int x) {
    	if (x >= 0 && x <= 9) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
}
