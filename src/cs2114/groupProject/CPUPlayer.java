package cs2114.groupProject;

import java.util.Deque;
import java.util.Queue;

// -------------------------------------------------------------------------
/**
 * This represents the computer player
 *
 * @author Patrick Brew
 * @version Nov 14, 2014
 */
public class CPUPlayer
    extends Player
{

    private Queue<PlayingCard> holdings;
    private String      name = "";


    // ----------------------------------------------------------
    /**
     * Create a new CPUPlayer object.
     *
     * @param n
     *            the name
     */
    public CPUPlayer(String n)
    {
        super(n);
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
     * @param in
     *            if it is slappable
     * @return that the hand was won
     */
    public boolean slap(boolean in)
    {

        int randomNum = 500 + (int)(Math.random() * 3000);
        try
        {
            Thread.sleep(randomNum);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        return in;
    }


    // ----------------------------------------------------------
    /**
     * This method takes cards off board and puts them in your hand
     *
     * @param board
     *            the indication to pick up cards
     */
    public void pickUp(Deque<PlayingCard> board)
    {

        while (!board.isEmpty())
        {
            holdings.add(board.remove());
        }

    }


    public void pickUp(Queue<PlayingCard> board)
    {

        holdings = board;

    }

}
