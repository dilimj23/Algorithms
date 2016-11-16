import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] queue;
	private int N = 0;
	
	public RandomizedQueue() { 				// construct an empty randomized queue
		queue = (Item[]) new Object[1];	//Initialize the queue to be size 1
	}
	
	public boolean isEmpty() {
		// is the queue empty?
		return (N == 0);
	}
	
	public int size() {
		// return the number of items on the queue
		return N;
	}
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = queue[i];
		}
		queue = copy;
	}
	
	public void enqueue(Item item) {
		// add the item
		if (item == null) {
			throw new NullPointerException();
		}
		if (N == queue.length) {
			resize(2 * queue.length);
		}
		queue[N++] = item;
	}
	
	public Item dequeue() {
		// remove and return a random item
		if (size() == 0) {
			throw new java.util.NoSuchElementException();
		}
		int index = StdRandom.uniform(N);
		Item item = queue[index];
		queue[index] = queue[--N];
		queue[N] = null;
		if (N > 0 && N == queue.length/4) resize(queue.length/2);
		return item;
	}
	
	public Item sample() {
		// return (but do not remove) a random item
		if (size() == 0) {
			throw new java.util.NoSuchElementException();
		}
		int index = StdRandom.uniform(N);
		return queue[index];
	}
	
	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<Item> {
		private int index = 0;
		private int[] random = new int[N];
		
		public ArrayIterator() {
			for (int i = 0; i < N; i++) {
				random[i] = i;
			}
			StdRandom.shuffle(random);
		}
		
		public boolean hasNext() {
			return index < random.length;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			return queue[random[index++]];
		}
	}
	
	public static void main(String[] args)  {// unit testing
		
	}

}
