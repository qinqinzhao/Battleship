package battleship;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {
	
	Ocean ocean = new Ocean();
	Battleship battleship = new Battleship();
	Cruiser cruiser = new Cruiser();
	Cruiser cruiser1 = new Cruiser();
	Destroyer destroyer = new Destroyer();
	Destroyer destroyer1 = new Destroyer();
	Destroyer destroyer2 = new Destroyer();
	Submarine submarine = new Submarine();
	Submarine submarine1 = new Submarine();
	Submarine submarine2 = new Submarine();
	Submarine submarine3 = new Submarine();
	EmptySea emptysea = new EmptySea();
	
	@Before
	public void setUp() {
		battleship.placeShipAt(6, 9, false, ocean);
		cruiser.placeShipAt(1, 1, true, ocean);
		cruiser1.placeShipAt(3, 2, true, ocean);
		destroyer.placeShipAt(5, 1, true, ocean);
		destroyer1.placeShipAt(1, 6, false, ocean);
		destroyer2.placeShipAt(2, 9, false, ocean);
		submarine.placeShipAt(3, 0, true, ocean);
		submarine1.placeShipAt(9, 0, false, ocean);
		submarine2.placeShipAt(5, 4, true, ocean);
		submarine3.placeShipAt(5, 7, false, ocean);
	}
	
	@Test
	public void testGetLength() {
		assertEquals(4, battleship.getLength());
		assertEquals(3, cruiser.getLength());
		assertEquals(2, destroyer.getLength());
		assertEquals(1, submarine.getLength());
	}
	
	@Test
	public void testGetBowRow() {
		assertEquals(6, battleship.getBowRow());
		assertEquals(1, cruiser.getBowRow());
		assertEquals(5, destroyer.getBowRow());
		assertEquals(3, submarine.getBowRow());
	}
	
	@Test
	public void testGetBowColumn() {
		assertEquals(9, battleship.getBowColumn());
		assertEquals(1, cruiser.getBowColumn());
		assertEquals(1, destroyer.getBowColumn());
		assertEquals(0, submarine.getBowColumn());
	}
	
	@Test
	public void testIsHorizontal() {
		assertFalse(battleship.isHorizontal());
		assertTrue(cruiser.isHorizontal());
		assertTrue(destroyer.isHorizontal());
		assertTrue(submarine.isHorizontal());
	}
	
	@Test
	public void testGetShipType() {
		assertEquals("battleship", battleship.getShipType());
		assertEquals("cruiser", cruiser.getShipType());
		assertEquals("destroyer", destroyer.getShipType());
		assertEquals("submarine", submarine.getShipType());
		assertEquals("emptysea", emptysea.getShipType());
	}
	
	@Test
	public void testOkToPlaceShipAt() {
		assertTrue(battleship.okToPlaceShipAt(7, 4, true, ocean));
		assertFalse(battleship.okToPlaceShipAt(6, 5, false, ocean));
		assertTrue(cruiser.okToPlaceShipAt(7, 0, true, ocean));
		assertFalse(cruiser.okToPlaceShipAt(6, 3, true, ocean));
		assertTrue(destroyer.okToPlaceShipAt(8, 5, false, ocean));
		assertFalse(destroyer.okToPlaceShipAt(9, 5, false, ocean));
		assertTrue(submarine.okToPlaceShipAt(8, 2, true, ocean));
	}
	
	@Test
	public void testShootAt() {
		assertTrue(battleship.shootAt(7, 9));
		assertTrue(battleship.hit[1]);
		assertTrue(battleship.shootAt(8, 9));
		assertTrue(battleship.shootAt(9, 9));
		assertTrue(battleship.shootAt(6, 9));
		assertTrue(battleship.hit[3]);
		assertFalse(battleship.shootAt(6, 9));
		assertFalse(battleship.shootAt(5, 9));
		assertTrue(destroyer.shootAt(5, 1));
		assertTrue(destroyer.shootAt(5, 2));
	}
	
	@Test
	public void testIsSunk() {
		battleship.shootAt(6, 9);
		battleship.shootAt(7, 9);
		battleship.shootAt(8, 9);
		battleship.shootAt(9, 9);
		assertTrue(battleship.isSunk());
		cruiser1.shootAt(3, 2);
		cruiser1.shootAt(3, 3);
		cruiser1.shootAt(3, 4);
		assertTrue(cruiser1.isSunk());
		destroyer2.shootAt(2, 9);
		assertFalse(destroyer2.isSunk());
		submarine3.shootAt(5, 7);
		assertTrue(submarine3.isSunk());
	}
	
	@Test
	public void testToString() {
		battleship.shootAt(6, 9);
		battleship.shootAt(7, 9);
		battleship.shootAt(8, 9);
		battleship.shootAt(9, 9);
		assertEquals("x", battleship.toString());
		cruiser1.shootAt(3, 2);
		cruiser1.shootAt(3, 3);
		assertEquals("S", cruiser1.toString());
	}

}
