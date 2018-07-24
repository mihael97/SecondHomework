package hr.fer.zemris.java.custom.collections;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class ArrayIndexedCollectionTest {

	private static ArrayIndexedCollection collection;

	@Test
	public void addTest() {
		// postavljamo velicinu kolekcije na 2
		collection = new ArrayIndexedCollection(2);
		assertEquals(0, collection.size());

		// dodajemo neke elemente
		collection.add("Prvi");
		collection.add(2);
		collection.add("Treci"); // realokacija
		collection.add("Cetvrti");

		assertEquals(4, collection.size()); // velicina 4
	}
	
	@Test(expected=NullPointerException.class)
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

	@Test(expected=NullPointerException.class) 
	public void insertNullTest() {
		collection.insert(null, 0);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void insertOutOfBoundTest() {
		collection.insert("Objekt", collection.size()+1);
	}
	
	@Test
	public void indexOfTest() {
		assertEquals(1, collection.indexOf(2));
		assertEquals(4, collection.indexOf("Cetvrti"));
		assertEquals(3, collection.indexOf(5));
		assertEquals(-1, collection.indexOf(6));
	}
	
	@Test
	public void removeTest() {
		ArrayIndexedCollection arrayCollection = new ArrayIndexedCollection(2);

		arrayCollection.add("Prvi");
		arrayCollection.add(2);
		arrayCollection.add("Treci"); 
		arrayCollection.add("Cetvrti");
		
		assertEquals(4, arrayCollection.size());
		arrayCollection.remove(1);
		assertEquals(3, arrayCollection.size());
		assertEquals(-1, arrayCollection.indexOf(2));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void removeOutOfBoundTest() {
		collection.remove(10);
	}
	
	@Test
	public void clearTest() {
		ArrayIndexedCollection arrayCollection = new ArrayIndexedCollection(2);

		arrayCollection.add("Prvi");
		arrayCollection.add(2);
		arrayCollection.add("Treci"); 
		arrayCollection.add("Cetvrti");
		
		assertEquals(4, arrayCollection.size());
		arrayCollection.clear();
		assertEquals(0, arrayCollection.size());
	}

}
