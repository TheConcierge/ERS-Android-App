package cs2114.groupProject;

import student.*;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Ryan Grant
 *  @version Nov 13, 2014
 */
public class PlayingCardTest extends TestCase
{
    private PlayingCard card;

    public void setUp()
    {
        card = new PlayingCard();
    }

    // ----------------------------------------------------------
    /**
     * Tests setting and getting value
     */
    public void testValue()
    {
        assertEquals(0, card.getValue());
        card.setValue(2);
        assertEquals(2, card.getValue());
    }

    /**
     * Tests setting and getting suit
     */
    public void testSuit()
    {
        assertEquals(0, card.getSuit());
        card.setSuit(2);
        assertEquals(2, card.getSuit());
    }

    /**
     * Tests setting and getting restriction
     */
    public void testRestriction()
    {
        assertEquals(0, card.getRestriction());
        card.setRestriction(2);
        assertEquals(2, card.getRestriction());
    }
}
