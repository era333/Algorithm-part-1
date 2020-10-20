import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
/**
 * @Author: xiaojiang
 * @DATE: Created in 14:49 2020/10/5
 */
public class Deque<Item> implements Iterable<Item>
{
    private Node first;
    private Node last;
    private int N;
    private class Node
    {
        Item item;
        Node left;
        Node right;

    }

    // construct an empty deque
    public Deque()
    { }

    // is the deque empty?
    public boolean isEmpty() { return N == 0; }

    // return the number of items on the deque
    public int size() { return N; }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null) throw new IllegalArgumentException("the argument is null");

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.right = oldFirst;
        if (!isEmpty()) oldFirst.left = first;
        else last = first;
        N++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null) throw new IllegalArgumentException("the argument is null");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.right = null;
        if (!isEmpty()) {
            last.left = oldLast;
            oldLast.right = last;
        }
        else first = last;
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty()) throw new java.util.NoSuchElementException("the deque is empty");
        Item item = first.item;
        first = first.right;
        N--;
        if(isEmpty()) last = null;
        else first.left = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty()) throw new java.util.NoSuchElementException("the deque is empty");
        Item item = last.item;
        last = last.left;
        N--;
        if(isEmpty()) first = null;
        else last.right = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public Item next()
        {
            if (current == null) throw new java.util.NoSuchElementException("there are no more items to return");
            Item item = current.item;
            current = current.right;
            return item;
        }
        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("illegal operation");
        }
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<String > s = new Deque<String>();
        s.addLast("a");
        for (Object i : s)
            StdOut.print(i);

        StdOut.print(" ");
        s.addFirst("b");
        for (Object i : s)
            StdOut.print(i);
        StdOut.print(" ");
        s.addLast("c");
        for (Object i : s)
            StdOut.print(i);
        StdOut.print(" ");
        StdOut.print(s.removeFirst());
        StdOut.print(" ");
        StdOut.print(s.removeLast() );
        StdOut.print(" ");
        StdOut.print(s.size());
        StdOut.print(s.removeLast() );
        StdOut.print(s.size());


    }

}
