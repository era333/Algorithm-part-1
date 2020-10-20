import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Author: xiaojiang
 * @DATE: Created in 14:52 2020/10/5
 */
public class Permutation
{
    public static void main(String[] args)
    {
        RandomizedQueue<String> a = new RandomizedQueue<String>();
        int number = Integer.parseInt(args[0]);
        int i = 1;
        while (!StdIn.isEmpty())
        {
            a.enqueue(StdIn.readString());
        }
        for (Object j : a)
        {
            StdOut.println(j);
            if (i >= number) break;
            i += 1;
        }
    }
}
