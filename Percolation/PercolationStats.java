import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
	private int trials;
	//private Percolation per;
	private double[] fractions;
	
	public PercolationStats(int n, int trials) { // perform trials independent experiments on an n-by-n grid
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException();
		}
		
		this.trials = trials;
		fractions = new double[this.trials];
		
		for (int test = 0; test < this.trials; test++) {
			Percolation per = new Percolation(n);
			int openned = 0;
			while(!per.percolates()) {
				int row = StdRandom.uniform(1, n+1);
				int col = StdRandom.uniform(1, n+1);
				if (!per.isOpen(row, col)) {
					per.open(row, col);
					openned++;
				}
			}
			double fraction = (double) openned / (n*n);
			fractions[test] = fraction;
		}
	}
	
	public double mean() {	// sample mean of percolation threshold
		return StdStats.mean(fractions);
	}
	
	public double stddev() {	// sample standard deviation of percolation threshold
		return StdStats.stddev(fractions);
	}
	
	public double confidenceLo() {	// low  endpoint of 95% confidence interval
		return mean() - 1.96 * stddev() / (Math.sqrt(trials));
	}
	
	public double confidenceHi() {	// high endpoint of 95% confidence interval
		return mean() + 1.96 * stddev() / (Math.sqrt(trials));
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		PercolationStats pers = new PercolationStats(n, trials);;
		
		StdOut.println("mean                    =" + pers.mean());
		StdOut.println("stddev                  =" + pers.stddev());
		StdOut.println("95% confidence interval =" + pers.confidenceLo() + ", " + pers.confidenceHi());
	}

}
