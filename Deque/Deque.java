
public class Deque<Item> implements Iterable<Item> {
	private Node first, last;
	private int size;
	
	private class Node {
		Item item;
		Node next;
		Node prev;
	}
	
	public Deque() {
		// construct an empty deque
		first = null;
		last = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		// is the deque empty?
		return (size == 0);
	}
	
	public int size() {
		// return the number of items on the deque
		return size;
	}
	
	public void addFirst(Item item) {
		// add the item to the front
		if (item == null) {
			throw new NullPointerException();
		}
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.prev = null;
		first.next = oldfirst;
		if (isEmpty()) {
			last = first;
		} else {
			oldfirst.prev = first;
		}
		size++;
	}
	
	public void addLast(Item item) {
		// add the item to the end
		if (item == null) {
			throw new NullPointerException();
		}
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.prev = oldlast;
		last.next = null;
		if (isEmpty()) {
			first = last;
		} else {
			oldlast.next = last;
		}
		size++;
	}
	
	public Item removeFirst() {
		// remove and return the item from the front
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		Item item = first.item;
		first = first.next;
		size--;
		if (isEmpty()) {
			last = null;
		}
		return item;
	}
	public Item removeLast() {
		// remove and return the item from the end
		if (size == 0) {
			throw new java.util.NoSuchElementException();
		}
		Item item = last.item;
		
		return null;
	}
	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return null;
	}
	public static void main(String[] args) {
		

	}

}
