package battleship;

public class Submarine extends Ship {
    
	int length = 1;
    
    /**
     * @return String "submarine".
     */
    @Override
    String getShipType() {
        return "submarine";
    }

    /**
     * @return The length of this particular ship. 
     */
	@Override
	int getLength() {
		return length;
	}
}
