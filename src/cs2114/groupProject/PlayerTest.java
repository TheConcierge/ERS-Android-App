package cs2114.groupProject;

import java.util.Queue;
import java.util.LinkedList;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Ryan Grant
 *  @version Nov 29, 2014
 */
public class PlayerTest extends student.TestCase
{
    private Player player;

    public void setUp()
    {
        player = new Player("Elizabeth");
    }

    // ----------------------------------------------------------
    /**
     * Tests method for getting the player's name
     */
    public void testGetName()
    {
        assertEquals("Elizabeth", player.getName());
    }

    // ----------------------------------------------------------
    /**
     * Tests method for indicating that the player has slapped
     */
    public void testSlap()
    {
        assertEquals(true, player.slap());
    }

    // ----------------------------------------------------------
    /**
     * Tests to make sure the cards get picked up
     */
    public void testPickUpNewCards()
    {
        Lab08Deque<PlayingCard> pile = new Lab08Deque<PlayingCard>();

        PlayingCard card1 = new PlayingCard();
        card1.setValue(1);

        PlayingCard card2 = new PlayingCard();
        card2.setValue(2);

        PlayingCard card3 = new PlayingCard();
        card3.setValue(3);

        pile.enqueueAtFront(card1);
        pile.enqueueAtFront(card2);
        pile.enqueueAtFront(card3);

        player.pickUp(pile);

        Queue<PlayingCard> hand = new LinkedList<PlayingCard>();
        hand = player.getHoldings();
        assertEquals(card1.getValue(), hand.remove().getValue());
        assertEquals(card2.getValue(), hand.remove().getValue());
        assertEquals(card3.getValue(), hand.remove().getValue());
    }

}
