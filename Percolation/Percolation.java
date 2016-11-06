import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private boolean[][] cells;
	private int top = 0;
	private int bottom;
	private int size;
	private WeightedQuickUnionUF percolation;
	
	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		size = n;
		cells = new boolean[size][size];
		bottom = size*size + 1;
		percolation = new WeightedQuickUnionUF(size*size+2); //need to add top and bottom	
	}

	public void open(int row, int col) {
		if (row < 1 || row > size || col < 1 || col > size) {
			throw new IndexOutOfBoundsException();
		}
		cells[row-1][col-1] = true;
		if (row == 1) {
			percolation.union(top, getIndex(row, col));
		}
		
		if (row == size) {
			percolation.union(bottom, getIndex(row,col));
		}
		
		if (row > 1 && isOpen(row-1, col)) {
			percolation.union(getIndex(row, col), getIndex(row-1, col));
		}
		
		if (row < size && isOpen(row+1, col)) {
			percolation.union(getIndex(row, col), getIndex(row+1, col));
		}
		
		if (col > 1 && isOpen(row, col-1)) {
			percolation.union(getIndex(row, col), getIndex(row, col-1));
		}
		
		if (col < size && isOpen(row, col+1)) {
			percolation.union(getIndex(row, col), getIndex(row, col+1));
		}
	}
	
	public boolean isOpen(int row, int col) {
		if (row<1 || row>size || col<1 || col>size) {
			throw new IndexOutOfBoundsException();
		}
		return cells[row-1][col-1];
	}
	
	public boolean isFull(int row, int col) {
		if (row<1 || row>size || col<1 || col>size) {
			throw new IndexOutOfBoundsException();
		}
		return percolation.connected(top, getIndex(row, col));
	}
	
	private int getIndex(int row, int col) {
		return (row-1)*size + col;
	}
	
	public boolean percolates() {
		return percolation.connected(top, bottom);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
