package cs2114.groupProject;

import student.TestCase;


// -------------------------------------------------------------------------
/**
 *  This class tests the Lab08Deque class
 *
 *  @author Ryan Grant
 *  @version Oct 24, 2014
 */
public class Lab08DequeTest extends TestCase
{
    private Lab08Deque<String> deque;
    /**
     * Sets up
     */
    public void setUp()
    {
        deque = new Lab08Deque<String>();
    }

    // ----------------------------------------------------------
    /**
     * Tests method for adding to front
     */
    public void testEnqueAtFront()
    {
        String test = "Hello";
        deque.enqueueAtFront(test);
        assertEquals("Hello", deque.frontItem());
    }

    /**
     * Tests method for adding to rear
     */
    public void testEnqueAtRear()
    {
        deque.enqueueAtRear("Hello");
        assertEquals("Hello", deque.rearItem());
    }

    /**
     * Tests method for removing from rear
     */
    public void testDequeAtRear()
    {
        deque.enqueueAtRear("Hello");
        deque.enqueueAtRear("Hi");
        assertEquals("Hi", deque.dequeueAtRear());
        assertEquals("Hello", deque.dequeueAtRear());
    }

    /**
     * Tests method for removing from front
     */
    public void testDequeAtFront()
    {

        deque.enqueueAtFront("Hi");
        deque.enqueueAtFront("Hello");
        assertEquals("Hello", deque.dequeueAtFront());
        assertEquals("Hi", deque.dequeueAtFront());
    }

    /**
     * Tests method for determining first item
     */
    public void testFrontItem()
    {
        deque.enqueueAtFront("Sup");
        assertEquals("Sup", deque.frontItem());
    }

    /**
     * Tests method for determining last item
     */
    public void testRearItem()
    {
        deque.enqueueAtRear("hi");
        assertEquals("hi", deque.rearItem());
    }

    /**
     * Tests method for returning size of deque
     */
    public void testSize()
    {
        assertEquals(0, deque.size());
        deque.enqueueAtFront("Hi");
        assertEquals(1, deque.size());
    }

    /**
     * Tests method for clearing the deque
     */
    public void testClear()
    {
        deque.enqueueAtRear("Hi");
        deque.enqueueAtFront("Hello");
        deque.clear();
        assertEquals(0, deque.size());
    }

    /**
     * Tests method for turning list into a string
     */
    public void testToString()
    {
        assertEquals("", deque.toString());
    }
}
