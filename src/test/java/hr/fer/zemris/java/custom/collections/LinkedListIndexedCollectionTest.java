package hr.fer.zemris.java.custom.collections;

import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class LinkedListIndexedCollectionTest {

	private static LinkedListIndexedCollection collection;

	@Test
	public void addTest() {
		collection = new LinkedListIndexedCollection();
		assertEquals(0, collection.size());

		// dodajemo neke elemente
		collection.add("Prvi");
		collection.add(2);
		collection.add("Treci"); // realokacija
		collection.add("Cetvrti");

		assertEquals(4, collection.size()); // velicina 4
	}

	@Test(expected = NullPointerException.class)
	public void addNullTest() {
		collection.add(null);
	}

	@Test
	public void getTest() {
		assertEquals(2, collection.get(1)); // 2 smo stavili drugog u kolekciju
		assertEquals(1, collection.indexOf(2)); // provjera

	}

	@Test
	public void insertTest() {
		assertEquals(4, collection.size());
		collection.insert(5, 3);
		assertEquals(5, collection.size());
		Object[] expected = { "Prvi", 2, "Treci", 5, "Cetvrti" }; // ocekivamo
		assertArrayEquals(expected, collection.toArray());
	}

	@Test(expected = NullPointerException.class)
	public void insertNullTest() {
		collection.insert(null, 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void insertOutOfBoundTest() {
		collection.insert("Objekt", collection.size() + 1);
	}

	@Test
	public void indexOfTest() {
		assertEquals(1, collection.indexOf(2));
		assertEquals(4, collection.indexOf("Cetvrti"));
		assertEquals(3, collection.indexOf(5));
		assertEquals(-1, collection.indexOf(7));
	}

	@Test
	public void removeTest() {
		LinkedListIndexedCollection listCollection = new LinkedListIndexedCollection();

		listCollection.add("Prvi");
		listCollection.add(2);
		listCollection.add("Treci");
		listCollection.add("Cetvrti");

		Object[] expected = { "Prvi", 2, "Treci", "Cetvrti" }; // ocekivamo
		assertArrayEquals(expected, listCollection.toArray());

		assertEquals(4, listCollection.size());
		listCollection.remove(1);
		assertEquals(3, listCollection.size());
		assertEquals(-1, listCollection.indexOf(2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeOutOfBoundTest() {
		collection.remove(10);
	}
	
	@Test
	public void clearTest() {
		LinkedListIndexedCollection listCollection = new LinkedListIndexedCollection();
		listCollection.add("Prvi");
		listCollection.add(2);
		listCollection.add("Treci"); 
		listCollection.add("Cetvrti");
		
		assertEquals(4, listCollection.size());
		listCollection.clear();
		assertEquals(0, listCollection.size());
	}
}
