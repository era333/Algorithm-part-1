import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @Author: xiaojiang
 * @DAtE: Created in 9:27 2020/9/30
 */
public class PercolationStats
{
    private final int dim;
    private final int t;
    private final double[] openSitesNumber;
    private final double CONFIDENCE_95 = 1.96;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if (n <= 0 || trials <=0 ) throw new IllegalArgumentException("error range");
        dim = n;
        t = trials;
        openSitesNumber = new double[t];

    }

    // sample mean of percolation threshold
    public double mean()
    { return StdStats.mean(openSitesNumber); }

    // sample standard deviation of percolation threshold
    public double stddev()
    { return StdStats.stddev(openSitesNumber); }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    { return (mean() - CONFIDENCE_95 * stddev() / Math.sqrt(t)); }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    { return (mean() + CONFIDENCE_95 * stddev() / Math.sqrt(t)); }
    // test client (see below)
    public static void main(String[] args)
    {
        PercolationStats p = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[0]));
        int row = 0;
        int col = 0;

        for (int i = 0; i < p.t; i++)
        {
            Percolation model = new Percolation(p.dim);
            while (!model.percolates())
            {
                row = StdRandom.uniform(1, p.dim + 1);
                col = StdRandom.uniform(1, p.dim + 1);
                if (model.isOpen(row, col)) continue;
                model.open(row, col);
            }
            p.openSitesNumber[i] = (double) model.numberOfOpenSites() / (p.dim * p.dim);
        }
        StdOut.printf("mean                            = %f\n", p.mean());
        StdOut.printf("stddev                          = %f\n", p.stddev());
        StdOut.printf("95%%confidence interval          = [%f,%f]", p.confidenceLo(),p.confidenceHi());
    }
}
