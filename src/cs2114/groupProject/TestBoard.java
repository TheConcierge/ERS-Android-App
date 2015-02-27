package cs2114.groupProject;

import java.util.Queue;
import java.util.List;
import sofia.util.Random;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Ryan Grant
 *  @version Nov 27, 2014
 */
public class TestBoard extends student.TestCase
{
    private Board board;

    public void setUp()
    {
        Random.setNextInts(0, 7, 4, 8, 9, 51, 51, 51, 51, 51, 51, 51, 51, 51,
            51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51,
                51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51,
                    51, 51, 51, 51, 51);
        board = new Board();
    }

    // ----------------------------------------------------------
    /**
     * Tests creating the initial board for the deck
     */
    public void testMakeDeck()
    {
        List<PlayingCard> deck = board.getInitialStartDeck();
        int deckCounter = 0;
        for (int s = 1; s <= 4; s++)
        {
            for (int i = 1; i <= 13; i++)
            {
                PlayingCard card = deck.get(deckCounter);
                assertEquals(s, card.getSuit());
                assertEquals(i, card.getValue());
                deckCounter++;
            }
        }
    }

    // ----------------------------------------------------------
    /**
     * Tests shuffling the deck.
     */
    public void testShuffleDeck()
    {
        List<PlayingCard> deck = board.getStartDeck();

        PlayingCard card = deck.get(0);
        assertEquals(1, card.getSuit());
        assertEquals(2, card.getValue());

        card = deck.get(47);
        assertEquals(1, card.getSuit());
        assertEquals(1, card.getValue());

        card = deck.get(48);
        assertEquals(1, card.getSuit());
        assertEquals(9, card.getValue());

        card = deck.get(49);
        assertEquals(1, card.getSuit());
        assertEquals(6, card.getValue());

        card = deck.get(50);
        assertEquals(1, card.getSuit());
        assertEquals(12, card.getValue());

        card = deck.get(51);
        assertEquals(2, card.getSuit());
        assertEquals(1, card.getValue());
    }

    // ----------------------------------------------------------
    /**
     * Tests creating and returning the hand of the first player.
     */
    public void testP1Hand()
    {
        Queue<PlayingCard> hand = board.getP1Hand();
        int loopCounter = 0;
        //for each loop adds one to the counter for each card in the hand.
        //each player starts with 26 cards.
        for (@SuppressWarnings("unused") PlayingCard card: hand)
        {
            loopCounter++;
        }
        assertEquals(26, loopCounter);
    }

 // ----------------------------------------------------------
    /**
     * Tests creating and returning the hand of the second player.
     */
    public void testP2Hand()
    {
        Queue<PlayingCard> hand = board.getP2Hand();
        int loopCounter = 0;
        //for each loop adds one to the counter for each card in the hand.
        //each player starts with 26 cards.
        for (@SuppressWarnings("unused") PlayingCard card: hand)
        {
            loopCounter++;
        }
        assertEquals(26, loopCounter);
    }

    // ----------------------------------------------------------
    /**
     * Tests returning the pile
     */
    public void testGetPile()
    {
        PlayingCard card1 = new PlayingCard();
        card1.setSuit(1);
        card1.setValue(1);

        PlayingCard card2 = new PlayingCard();
        card2.setSuit(2);
        card2.setValue(2);

        board.pile.enqueueAtFront(card1);
        board.pile.enqueueAtRear(card2);

        assertEquals(1, board.pile.frontItem().getSuit());
        assertEquals(1, board.pile.frontItem().getValue());

        assertEquals(2, board.pile.rearItem().getSuit());
        assertEquals(2, board.pile.rearItem().getValue());
    }


    // ----------------------------------------------------------
    /**
     * Test the activation of marriage rule
     */
    public void testSetMarriageRule()
    {
        assertFalse(board.getMarriagesRule());
        board.setMarriagesRule(true);
        assertTrue(board.getMarriagesRule());
        board.setMarriagesRule(false);
        assertFalse(board.getMarriagesRule());
    }

    // ----------------------------------------------------------
    /**
     * Test the activation of top-bottom rule
     */
    public void testSetTopBottomRule()
    {
        assertFalse(board.getTopBottomRule());
        board.setTopBottomRule(true);
        assertTrue(board.getTopBottomRule());
        board.setTopBottomRule(false);
        assertFalse(board.getTopBottomRule());
    }

    // ----------------------------------------------------------
    /**
     * tests returning the restriction based on the card last played
     * on top of pile
     */
    public void testRestricitonPlacedOnPlayer()
    {
        PlayingCard card1 = new PlayingCard();
        card1.setValue(13);
        card1.setSuit(2);
        card1.setRestriction(3);

        board.pile.enqueueAtFront(card1);
        assertEquals(3, board.restrictionPlacedOnPlayer());
    }

    // ----------------------------------------------------------
    /**
     * Tests the possibilities of slapping and if it is correct or not
     */
    public void testIsValidSlap()
    {
        //only value is set because only value is needed for the method
        PlayingCard card1 = new PlayingCard();
        card1.setValue(13);

        PlayingCard card2 = new PlayingCard();
        card2.setValue(12);

        PlayingCard card3 = new PlayingCard();
        card3.setValue(12);

        PlayingCard card4 = new PlayingCard();
        card4.setValue(8);

        PlayingCard card5 = new PlayingCard();
        card5.setValue(7);

        //tests doubles, more than two card
        board.pile.enqueueAtFront(card5);
        board.pile.enqueueAtFront(card3);
        board.pile.enqueueAtFront(card2);

        assertTrue(board.isValidSlap());
        board.pile.clear();

        //tests doubles, more than two cards
        board.pile.enqueueAtFront(card5);
        board.pile.enqueueAtFront(card3);
        board.pile.enqueueAtFront(card2);

        assertTrue(board.isValidSlap());
        board.pile.clear();

        //tests marriages
        board.pile.enqueueAtFront(card5);
        board.pile.enqueueAtFront(card1);
        board.pile.enqueueAtFront(card2);

        assertFalse(board.isValidSlap());
        board.setMarriagesRule(true);
        assertTrue(board.isValidSlap());
        board.pile.clear();

        //tests marriages, rule already set
        board.pile.enqueueAtFront(card5);
        board.pile.enqueueAtFront(card2);
        board.pile.enqueueAtFront(card1);

        assertTrue(board.isValidSlap());
        board.pile.clear();

        //tests top-bottom
        board.pile.enqueueAtFront(card2);
        board.pile.enqueueAtFront(card5);
        board.pile.enqueueAtFront(card4);
        board.pile.enqueueAtFront(card3);

        assertFalse(board.isValidSlap());
        board.setTopBottomRule(true);
        assertTrue(board.isValidSlap());
        board.pile.clear();

        //tests top-bottom, rule already set
        board.pile.enqueueAtFront(card3);
        board.pile.enqueueAtFront(card5);
        board.pile.enqueueAtFront(card4);
        board.pile.enqueueAtFront(card2);

        assertTrue(board.isValidSlap());
        board.pile.clear();

        //tests doubles
        board.pile.enqueueAtFront(card1);
        board.pile.enqueueAtFront(card3);
        board.pile.enqueueAtFront(card2);

        assertTrue(board.isValidSlap());
        board.pile.clear();

        //tests doubles
        board.pile.enqueueAtFront(card1);
        board.pile.enqueueAtFront(card2);
        board.pile.enqueueAtFront(card3);

        assertTrue(board.isValidSlap());
        board.pile.clear();

        //tests sandwiches
        board.pile.enqueueAtFront(card1);
        board.pile.enqueueAtFront(card3);
        board.pile.enqueueAtFront(card5);
        board.pile.enqueueAtFront(card2);

        assertTrue(board.isValidSlap());
        board.pile.clear();

        //tests sandwiches
        board.pile.enqueueAtFront(card1);
        board.pile.enqueueAtFront(card2);
        board.pile.enqueueAtFront(card5);
        board.pile.enqueueAtFront(card3);

        assertTrue(board.isValidSlap());
        board.pile.clear();

        //tests no slappability
        board.pile.enqueueAtFront(card1);
        board.pile.enqueueAtFront(card3);
        board.pile.enqueueAtFront(card5);

        assertFalse(board.isValidSlap());
        board.pile.clear();

        //tests doubles, two cards only
        board.pile.enqueueAtFront(card3);
        board.pile.enqueueAtFront(card2);

        assertTrue(board.isValidSlap());
        board.pile.clear();

        //slapping an empty board
        assertFalse(board.isValidSlap());
    }
}
