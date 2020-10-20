import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * @Author: xiaojiang
 * @DATE: Created in 14:52 2020/10/5
 */
public class RandomizedQueue<Item> implements Iterable<Item>
{
    private  Item[] a;
    private  int N;
    // construct an empty randomized queue
    public RandomizedQueue()
    { a = (Item[]) new Object[1]; }

    // is the randomized queue empty?
    public boolean isEmpty() { return N == 0; }

    // return the number of items on the randomized queue
    public int size() { return N; }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null) throw new IllegalArgumentException("the argument is null");
        if (N == a.length) resize(2*a.length);
        a[N++] = item;
    }

    // resize the array
    private void resize(int max)
    {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] =  a[i];
        a = temp;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty()) throw new java.util.NoSuchElementException("the queue is empty");
        int n = StdRandom.uniform(N);
        Item item = a[n];
        for (int i = n; i < N-1; i++) a[i] = a[i+1];
        --N;
        a[N] = null;
        if (N > 0 && N == a.length / 4) resize(a.length/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (isEmpty()) throw new java.util.NoSuchElementException("the queue is empty");
        int n = StdRandom.uniform(N);
        Item item = a[n];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomQueue();
    }
    private class RandomQueue implements Iterator<Item>
    {
        private int i = N;
        int[] b;
        public RandomQueue()
        {
            b = new int[N];
            for (int j = 0; j < N; j++)
                b[j] = j;
            StdRandom.shuffle(b);
        }
        public boolean hasNext() { return i > 0; }
        public Item next() { return a[b[--i]]; }
        public  void remove()
        {
            throw new UnsupportedOperationException("illegal operation");
        }

    }

    // unit testing (required)
    public static void main(String[] args)
    {
        RandomizedQueue<String> a = new RandomizedQueue<String>();
        a.enqueue("1");
        a.enqueue("2");
        a.enqueue("3");
        a.enqueue("5");
        for (Object i : a)
            StdOut.print(i + " ");
        StdOut.print(a.dequeue() + " ");

        for (Object i : a)
            StdOut.print(i + " ");




    }

}
