package cs2114.groupProject;


import sofia.util.Random;
import android.widget.ToggleButton;
import cs2114.groupProject.ERSScreen;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import sofia.widget.ImageView;


// -------------------------------------------------------------------------
/**
 *  This class tests that the screen works
 *
 *  @author DavidEvans
 *  @version Dec 1, 2014
 */
public class ERSScreenTest extends student.AndroidTestCase<ERSScreen>
{
    //private ImageView UserDeck;
    private Button    Slap1;
    private Button    Play1;
    private Button    Play2;
    //private ImageView Played;
    //private ImageView Deck2;
    private Button    Slap2;
    private ToggleButton    marriageToggle;
    private ToggleButton    topBottom;
    private Button    Start;
    private TextView oneName;
    private TextView twoName;
    //private TextView oneNumCards;
    //private TextView twoNumCards;
    private EditText oneNameIn;
    private EditText twoNameIn;

    /**
     * sets up the basic testing
     */
    public void setUp()
    {
        Board board;


        Random.setNextInts(0, 7, 4, 8, 9, 51, 51, 51, 51, 51, 51, 51, 51, 51,
            51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51,
            51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51,
            51, 51, 51, 51, 51);
        board = new Board();

        this.getActivity().getBoard().getP1Hand().clear();
        this.getActivity().getBoard().getP1Hand().addAll(board.getP1Hand());
    }

    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public ERSScreenTest()
    {
        super(ERSScreen.class);
    }

    // ----------------------------------------------------------
    /**
     * Tests to see if the right values are initialized
     */
    public void testStart()
    {
        this.enterText(oneNameIn, "David");
        this.enterText(twoNameIn, "Daniel");

        this.click(Start);

        assertEquals(oneName.getText().toString(), "David");
        assertEquals(twoName.getText().toString(), "Daniel");


    }

    // ----------------------------------------------------------
    /**
     * Tests to see if the top bottom rule is initialized correctly
     */
    public void testTopBottom()
    {
        this.click(topBottom);

        assertTrue(this.getActivity().getBoard().getTopBottomRule());

        this.click(topBottom);

        assertFalse(this.getActivity().getBoard().getTopBottomRule());

    }

    /**
     * Tests to see if the top bottom rule is initialized correctly
     */
    public void testMarriageToggle()
    {

        assertFalse(this.getActivity().getBoard().getMarriagesRule());

        this.click(marriageToggle);

        assertTrue(this.getActivity().getBoard().getMarriagesRule());

        this.click(marriageToggle);


        assertFalse(this.getActivity().getBoard().getMarriagesRule());


    }

    /**
     * Tests to see if the play1 button works
     */
    public void testPlay1()
    {
        this.click(Start);

        PlayingCard card = this.getActivity().getBoard().getP1Hand().peek();

        this.click(Play1);

        assertEquals(this.getActivity().getBoard().pile.frontItem(), card);
    }

    /**
     * Tests to see if the play2 button works
     */
    public void testPlay2()
    {
        this.click(Start);

        PlayingCard card = this.getActivity().getBoard().getP2Hand().peek();

        this.click(Play1);
        this.click(Play2);

        assertEquals(this.getActivity().getBoard().pile.frontItem(), card);
    }

    /**
     * Tests to see if the the slap1 works
     */

    public void testSlap1()
    {
        this.click(Start);

        PlayingCard card = new PlayingCard();
        this.getActivity().getBoard().pile.enqueueAtFront(card);

        this.click(Slap1);

        PlayingCard card2 = new PlayingCard();
        this.getActivity().getBoard().pile.enqueueAtFront(card2);

        this.click(Slap1);

        assertEquals(this.getActivity().getBoard().pile.size(), 0);

    }

    /**
     * Tests to see if the the slap1 works
     */

    public void testSlap2()
    {
        this.click(Start);

        PlayingCard card = new PlayingCard();
        this.getActivity().getBoard().pile.enqueueAtFront(card);

        this.click(Slap2);

        PlayingCard card2 = new PlayingCard();
        this.getActivity().getBoard().pile.enqueueAtFront(card2);

        this.click(Slap2);

        assertEquals(this.getActivity().getBoard().pile.size(), 0);

    }

    /**
     * Tests to see if the to string method works
     */

    public void testCard2String()
    {
        PlayingCard card = new PlayingCard();

        card.setSuit(1);
        card.setValue(5);

        String str = this.getActivity().card2String(card);

        assertEquals(str, "d5");

        PlayingCard card2 = new PlayingCard();

        card2.setSuit(4);
        card2.setValue(13);

        String str2 = this.getActivity().card2String(card2);

        assertEquals(str2, "sk");

    }

    /**
     * tests to see if the game ends correctly
     */
    public void testGame()
    {
        this.click(Start);

        this.getActivity().getBoard().getP2Hand().clear();

        this.click(Play1);

        assertFalse(Play1.isEnabled());
        assertFalse(Play2.isEnabled());
        assertFalse(Slap1.isEnabled());
        assertFalse(Slap2.isEnabled());

    }

    /**
     * tests to see if the game ends correctly
     */
    public void testGame2()
    {
        this.click(Start);

        this.getActivity().getBoard().getP1Hand().clear();

        PlayingCard card = new PlayingCard();
        this.getActivity().getBoard().getP1Hand().add(card);

        this.click(Play1);

        assertFalse(Play1.isEnabled());
        assertFalse(Play2.isEnabled());
        assertFalse(Slap1.isEnabled());
        assertFalse(Slap2.isEnabled());

    }

}
