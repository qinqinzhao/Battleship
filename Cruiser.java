package battleship;

public class Cruiser extends Ship {
	
	int length = 3;
    
    /**
     * @return String "cruiser".
     */
    @Override
    String getShipType() {
        return "cruiser";
    }

    /**
     * @return The length of this particular ship. 
     */
	@Override
	int getLength() {
		return length;
	}

}
