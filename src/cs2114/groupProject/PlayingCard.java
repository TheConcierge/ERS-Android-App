package cs2114.groupProject;

// -------------------------------------------------------------------------
/**
 *  This class creates the playing card objects to be used for the game.
 *  Each suit corresponds to an integer.
 *  1 = diamonds, 2 = clubs, 3 = hearts, 4 = spades.
 *  Furthermore, the restriction the card places on the game are part of the
 *  object as well.
 *
 *  @author Ryan Grant
 *  @version Nov 13, 2014
 */
public class PlayingCard
{
    private int suit;
    private int value;
    private int restriction;

    // ----------------------------------------------------------
    /**
     * Create a new PlayingCard object.
     */
    public PlayingCard()
    {
        suit = 0;
        value = 0;
        restriction = 0;
    }

    // ----------------------------------------------------------
    /**
     * Sets the suit for a card object
     * @param s the suit to be set on the card
     *
     * PRECONDITION- 1<=s<=4
     */
    public void setSuit(int s)
    {
        suit = s;
    }

    /**
     * Sets the value for a card object
     * @param v the value to be set on the card
     *
     * PRECONDITION- 1<=v<=13
     */
    public void setValue(int v)
    {
        value = v;
    }

    /**
     * Sets the suit for a card object
     * @param r the restriction to be set on the card
     *
     * PRECONDITION- 1<=s<=4
     */
    public void setRestriction(int r)
    {
        restriction = r;
    }

    // ----------------------------------------------------------
    /**
     * Returns the restriction assigned to the card
     * @return int the restriction the card sets
     */
    public int getRestriction()
    {
        return restriction;
    }


    // ----------------------------------------------------------
    /**
     * Returns the value assigned to the card
     * @return int the value of the card
     */
    public int getValue()
    {
        return value;
    }


    // ----------------------------------------------------------
    /**
     * returns the suit of the card
     * @return int the suit of the card
     */
    public int getSuit()
    {
        return suit;
    }
}
