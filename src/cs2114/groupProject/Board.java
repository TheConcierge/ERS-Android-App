package cs2114.groupProject;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;
import sofia.util.Random;



// -------------------------------------------------------------------------
/**
 *  This is the basic board class that supports the starting actions and
 *  the playing actions.
 *
 *  @author Ryan
 *  @version Nov 14, 2014
 */
public class Board
{
    private Queue<PlayingCard> p1Hand;
    private Queue<PlayingCard> p2Hand;
    private List<PlayingCard> startDeck;
    private List<PlayingCard> initialStartDeck; //used for testing
    /**
     * this pile is where cards are to be played
     */
    public Lab08Deque<PlayingCard> pile;

    private boolean topBottomRuleActive;
    private boolean marriagesRuleActive;

    // ----------------------------------------------------------
    /**
     * Create a new Board object.
     */
    public Board()
    {
        this.init();
    }

    // ----------------------------------------------------------
    /**
     * Initializes the game board
     */
    private void init()
    {
        marriagesRuleActive = false;
        topBottomRuleActive = false;

        startDeck = new ArrayList<PlayingCard>();
        p1Hand = new LinkedList<PlayingCard>();
        p2Hand = new LinkedList<PlayingCard>();
        pile = new Lab08Deque<PlayingCard>();

        this.makeDeck();
        deepCloneStartDeck(); //creates clone of initial deck for testing
        this.shuffleDeck();

        for (int i = 0; i < 26; i++)
        {
            p1Hand.add(startDeck.get(i));
        }
        for (int i = 26; i < 52; i++)
        {
            p2Hand.add(startDeck.get(i));
        }



    }

    // ----------------------------------------------------------
    /**
     * Makes the deck to be 52 cards in order
     */
    private void makeDeck()
    {
        for (int s = 1; s <= 4; s++)
        {
            for (int i = 1; i <= 13; i++)
            {
                PlayingCard card = new PlayingCard();
                card.setValue(i);
                card.setSuit(s);
                if (i == 1)
                {
                    card.setRestriction(4);
                }
                if (i == 11)
                {
                    card.setRestriction(1);
                }
                if (i == 12)
                {
                    card.setRestriction(2);
                }
                if (i == 13)
                {
                    card.setRestriction(3);
                }
                startDeck.add(card);

            }
        }
    }

    // ----------------------------------------------------------
    /**
     * shuffles the deck to make it random
     */
    private void shuffleDeck()
    {
        Random random = new Random();

        for (int i = 0; i < 52; i++)
        {
            int r = random.nextInt(52);
            PlayingCard temp = startDeck.remove(r);
            startDeck.add(temp);
        }


    }

    /**
     * returns the p1Hand
     * @return the p1Hand queue
     */

    public Queue<PlayingCard> getP1Hand()
    {
        return p1Hand;

    }

    /**
     * returns the p2Hand
     * @return the p2Hand queue
     */

    public Queue<PlayingCard> getP2Hand()
    {
        return p2Hand;

    }

    /**
     * pile
     * @return Lab08Deque<PlayingCard>
     */

    public Lab08Deque<PlayingCard> getPile()
    {
        return pile;

    }

    // ----------------------------------------------------------
    /**
     * Screen class sets if this rule is in place
     * @param toggle is whether marriage rules is active or not
     */
    public void setMarriagesRule(boolean toggle)
    {
        marriagesRuleActive = toggle;
    }

    /**
     * Screen class sets if this rule is in place
     * @param toggle is whether the topBottomRule is active or not
     */
    public void setTopBottomRule(boolean toggle)
    {
        topBottomRuleActive = toggle;
    }

    /**
     *
     * Decides if slapping the pile results in a win
     *
     *@return int what restriction the last card placed gives to other player
     *
     */
    public int restrictionPlacedOnPlayer()
    {
        PlayingCard card = pile.frontItem();
        return card.getRestriction();
    }

    /**
     *
     * Decides if slapping the pile results in a win
     *
     * @return if the slap was valid
     */
    public boolean isValidSlap()
    {
        if (pile.size() <= 1)
        {
            return false;
        }
        else
        {
            PlayingCard first = pile.dequeueAtFront();
            PlayingCard second = pile.dequeueAtFront();
            PlayingCard third = new PlayingCard(); //dummy initialization
            if (pile.size() > 0)
            {
                third = pile.frontItem();
            }
            pile.enqueueAtFront(second);
            pile.enqueueAtFront(first);
            if (topBottomRuleActive)
            {
                PlayingCard top = pile.frontItem();
                PlayingCard bottom = pile.rearItem();
                if (top.getValue() == bottom.getValue())
                {
                    return true;
                }
            }
            if (marriagesRuleActive)
            {
                if ((first.getValue() == 12 && second.getValue() == 13) ||
                    (second.getValue() == 12 && first.getValue() == 13))
                {
                    return true;
                }
            }
            if (first.getValue() == second.getValue())
            {
                return true;
            }
            if (pile.size() > 2)
            {
                if (first.getValue() == third.getValue())
                {
                    return true;
                }
            }
            return false;
        }
    }

    // ----------------------------------------------------------
    /**
     * This method gives the original deck. Used for testing only.
     * @return List<PlayingCard> the original deck
     */
    public List<PlayingCard> getInitialStartDeck()
    {
        return initialStartDeck;
    }

    // ----------------------------------------------------------
    /**
     * returns the original deck to be tested with.
     * @return List<PlayingCard> the original deck
     */
    public List<PlayingCard> getStartDeck()
    {
        return startDeck;
    }

    /**
     * Makes a deep clone of the start deck for testing purposes
     */
    private void deepCloneStartDeck()
    {
        initialStartDeck = new ArrayList<PlayingCard>();
        for (int i = 0; i < 52; i++)
        {
            PlayingCard card = startDeck.get(i);
            initialStartDeck.add(card);
        }
    }

    // ----------------------------------------------------------
    /**
     * Returns if the top-bottom rule is currently active. Used for testing
     * @return boolean if rule is active
     */
    public boolean getTopBottomRule()
    {
        return topBottomRuleActive;
    }

    // ----------------------------------------------------------
    /**
     * Returns if the marriage rule is currently active. Used for testing
     * @return boolean if rule is active
     */
    public boolean getMarriagesRule()
    {
        return marriagesRuleActive;
    }

}
