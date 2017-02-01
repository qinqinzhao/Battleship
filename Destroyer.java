package battleship;

public class Destroyer extends Ship {
    
	int length = 2;
    
    /**
     * @return String "destroyer".
     */
    @Override
    String getShipType() {
        return "destroyer";
    }

    /**
     * @return The length of this particular ship. 
     */
	@Override
	int getLength() {
		return length;
	}
}
