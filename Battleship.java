package battleship;

public class Battleship extends Ship {
    
	int length = 4;
    
    /**
     * @return String "battleship".
     */
    @Override
    String getShipType() {
        return "battleship";
    }

    /**
     * @return The length of this particular ship. 
     */
	@Override
	int getLength() {
		return length;
	}
}
