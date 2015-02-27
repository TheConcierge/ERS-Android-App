package cs2114.groupProject;

import java.util.ArrayDeque;
import java.util.Deque;

// -------------------------------------------------------------------------
/**
 * This class creats a new deque based on the DoubleLinkDequeClass
 *
 * @param <E>
 * @author Ryan Grant
 * @version Oct 24, 2014
 */
public class Lab08Deque<E> extends DoubleLinkDeque<E>
{

    private Deque<E> deque;
    // ----------------------------------------------------------
    /**
     * Create a new Lab08Deque object.
     */
    public Lab08Deque()
    {
        deque = new ArrayDeque<E>();
    }


    /**
     * Insert a new item at the front (the head) of the deque.
     * @param value the item to insert.
     * @postcondition [new-contents] = [value] * [old-contents]
     */
    public void enqueueAtFront(E value)
    {
        deque.addFirst(value);
    }

    /**
     * Remove the item at the front (the head) of the deque.
     * @return The item that was removed
     * @precondition |[old-contents]| > 0
     * @postcondition [old-contents] == [result] * [new-contents]
     */
    public E dequeueAtFront()
    {
        return deque.removeFirst();
    }


    /**
     * Insert a new item at the rear (the tail) of the deque.
     * @param value the item to insert.
     * @postcondition [new-contents] == [old-contents] * [value]
     */
    public void enqueueAtRear(E value)
    {
        deque.addLast(value);

    }


    /**
     * Remove the item at the rear (the tail) of the deque.
     * @return The item that was removed
     * @precondition |[old-contents]| > 0
     * @postcondition [old-contents] = [new-contents] * [result]
     */
    public E dequeueAtRear()
    {
        return deque.removeLast();
    }


    /**
     * Get the item at the front (the head) of the deque.
     * Does not alter the deque.
     * @return the item at the front of the deque.
     * @precondition |[contents]| > 0
     * @postcondition [new-contents] == [old-contents] and
     *     [contents] = [result] * [rest-of-items]
     */
    public E frontItem()

    {

        return deque.getFirst();
    }


    /**
     * Get the item at the rear (the tail) of the deque.
     * Does not alter the deque.
     * @return the item at the rear of the deque.
     * @precondition |[contents]| > 0
     * @postcondition [new-contents] == [old-contents] and
     *     [contents] = [rest-of-items] * [result]
     */
    public E rearItem()
    {
        return deque.getLast();
    }


    /**
     * Empty the deque.
     * @postcondition [new-contents] = []
     */
    public void clear()
    {
        deque.clear();
    }


    /**
     * Returns a string representation of this deque. A deque's string
     * representation is written as a comma-separated list of its contents (in
     * front-to-rear order) surrounded by square brackets, like this:
     *
     * <pre>
     * [52, 14, 12, 119, 73, 80, 35]
     * </pre>
     * <p>
     * An empty deque is simply <code>[]</code>.
     * </p>
     *
     * @return String a string representation of the deque
     */
    public String toString()
    {
        return "";
    }

    public int size()
    {
        return deque.size();
    }

}
