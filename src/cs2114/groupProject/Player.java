package cs2114.groupProject;

import java.util.LinkedList;
import java.util.Queue;

// -------------------------------------------------------------------------
/**
 * This is the player class
 *
 * @author Patrick Brew
 * @version Nov 13, 2014
 */
public class Player
{
    private String      name = "";
    private int currentRestriction;
    private Queue<PlayingCard> holdings;


    // ----------------------------------------------------------
    /**
     * Create a new Player object.
     *
     * @param n
     *            the name
     */
    public Player(String n)
    {
        name = n;
        holdings = new LinkedList<PlayingCard>();
    }


    // ----------------------------------------------------------
    /**
     * this is useful if we want to use their name
     *
     * @return name of player
     */
    public String getName()
    {
        return name;
    }


    // ----------------------------------------------------------
    /**
     * This makes you win the cards in play
     *
     * @return that the hand was won
     */
    public boolean slap()
    {
        return true;
    }


    // ----------------------------------------------------------
    /**
     * This method takes cards off board and puts them in your hand
     *
     * @param board
     *            the indication to pick up cards
     */
    public void pickUp(Lab08Deque<PlayingCard> board)
    {
        int size = board.size();
        for (int i = 0; i < size; i++)
        {
            holdings.add(board.dequeueAtRear());
        }
    }


    // ----------------------------------------------------------
    /**
     * Only used on initial shuffled hand
     *
     * @param board
     *            the shuffled starting hand
     */
    public void pickUp(Queue<PlayingCard> board)
    {

        holdings = board;

    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @return Queue<PlayingCard> the hand of the player
     */
    public Queue<PlayingCard> getHoldings()
    {
        return holdings;
    }

    // ----------------------------------------------------------
    /**
     * Sets restriction on player
     * @param i the new restriction
     */
    public void setCurrentRestriction(int i)
    {
        currentRestriction = i;
    }

    // ----------------------------------------------------------
    /**
     * Gets restriction on player
     * @return int the restriction on the player
     */
    public int getCurrentRestriction()
    {
        return currentRestriction;
    }

}
