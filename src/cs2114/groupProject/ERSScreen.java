package cs2114.groupProject;

import android.app.Dialog;
import android.widget.ToggleButton;
import android.widget.EditText;
import android.widget.TextView;
import sofia.app.Screen;
import android.widget.Button;
import sofia.widget.ImageView;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Ryan
 *  @version Nov 30, 2014
 */
public class ERSScreen
    extends Screen
{

    //private ImageView UserDeck;
    private Button    Slap1;
    private Button    Play1;
    private Button    Play2;
    private ImageView Played;
    //private ImageView Deck2;
    private Button    Slap2;
    private ToggleButton topBottom;
    private Button    Start;
    private ToggleButton marriageToggle;
    private TextView oneName;
    private TextView twoName;
    private TextView oneNumCards;
    private TextView twoNumCards;
    private EditText oneNameIn;
    private EditText twoNameIn;
    private Board     b;
    private Player    one;
    private Player    two;
    private boolean playerOneTurn;


    // private int played=0;

    public void initialize()
    {
        b = new Board();
        playerOneTurn = true;
        Play1.setEnabled(false);
        Play2.setEnabled(false);
        Slap1.setEnabled(false);
        Slap2.setEnabled(false);

        oneName.setText("");
        twoName.setText("");
    }


    // ----------------------------------------------------------
    /**
     * Sets up the game for when the start button is clicked
     */
    public void StartClicked()
    {
        one = new Player(oneNameIn.getText().toString());
        two = new Player(twoNameIn.getText().toString());

        one.pickUp(b.getP1Hand());
        two.pickUp(b.getP2Hand());

        topBottom.setEnabled(false);
        marriageToggle.setEnabled(false);
        Play1.setEnabled(true);
        Slap1.setEnabled(true);
        Slap2.setEnabled(true);
        Start.setEnabled(false);
        oneNameIn.setEnabled(false);
        twoNameIn.setEnabled(false);



        oneName.setText(oneNameIn.getText());
        twoName.setText(twoNameIn.getText());

        oneNumCards.setText("Number of Cards:" + one.getHoldings().size());
        twoNumCards.setText("Number of Cards:" + two.getHoldings().size());

    }


    // ----------------------------------------------------------
    /**
     * Updates current turn and keeps track of restrictions/chances on player
     */
    public void update()
    {
        if (b.getP1Hand().size() == 0)
        {
            gameWon(two.getName());
        }
        else if (b.getP2Hand().size() == 0)
        {
            gameWon(one.getName());
        }
        else if (playerOneTurn)
        {
            int value = b.pile.frontItem().getValue();
            if (one.getCurrentRestriction() == 0 || value == 1 || value == 11 ||
                value == 12 || value == 13)
            {
                playerOneTurn = false;
                Play1.setEnabled(false);
                Play2.setEnabled(true);
                one.setCurrentRestriction(0);
                two.setCurrentRestriction(b.pile.frontItem().getRestriction());
            }
            else
            {
                int chance = one.getCurrentRestriction();
                if (chance == 1)
                {
                    two.pickUp(b.pile);
                    twoNumCards.setText("Number of Cards: " +
                        b.getP2Hand().size());
                    playerOneTurn = false;
                    Play1.setEnabled(false);
                    Play2.setEnabled(true);
                    one.setCurrentRestriction(0);
                    two.setCurrentRestriction(0);
                    Played.setImageResource(getResources().getIdentifier(
                        "ec", "drawable", getPackageName()));
                }
                else
                {
                    one.setCurrentRestriction(chance - 1);
                }
            }

        }
        else
        {
            int value = b.pile.frontItem().getValue();
            if (two.getCurrentRestriction() == 0 || value == 1 || value == 11 ||
                value == 12 || value == 13)
            {
                playerOneTurn = true;
                Play1.setEnabled(true);
                Play2.setEnabled(false);
                two.setCurrentRestriction(0);
                one.setCurrentRestriction(b.pile.frontItem().getRestriction());
            }
            else
            {
                int chance = two.getCurrentRestriction();
                if (chance == 1)
                {
                    one.pickUp(b.pile);
                    oneNumCards.setText("Number of Cards: " +
                        b.getP1Hand().size());
                    playerOneTurn = true;
                    Play1.setEnabled(true);
                    Play2.setEnabled(false);
                    one.setCurrentRestriction(0);
                    two.setCurrentRestriction(0);
                    Played.setImageResource(getResources().getIdentifier(
                        "ec", "drawable", getPackageName()));
                }
                else
                {
                    two.setCurrentRestriction(chance - 1);
                }
            }
        }

        oneNumCards.setText("Number of Cards:" + one.getHoldings().size());
        twoNumCards.setText("Number of Cards:" + two.getHoldings().size());
    }


    // ----------------------------------------------------------
    /**
     * Is called when player 2 clicks play
     */
    public void Play2Clicked()
    {
        PlayingCard card = b.getP2Hand().remove();
        b.pile.enqueueAtFront(card);
        String loadImage = card2String(card);
        Played.setImageResource(getResources().getIdentifier(
            loadImage, "drawable", getPackageName()));
        update();

    }


    // ----------------------------------------------------------
    /**
     * Is called when player one clicks play
     */
    public void Play1Clicked()
    {
        PlayingCard card = b.getP1Hand().remove();
        b.pile.enqueueAtFront(card);
        String loadImage = card2String(card);
        Played.setImageResource(getResources().getIdentifier(
            loadImage, "drawable", getPackageName()));
        update();
    }


    // ----------------------------------------------------------
    /**
     * Sets rule if clicked
     */
    public void topBottomClicked()
    {
        b.setTopBottomRule(topBottom.isChecked());
    }


    // ----------------------------------------------------------
    /**
     * sets the marriage mode to the value of the toggle button
     */
    public void marriageToggleClicked()
    {
        b.setMarriagesRule(marriageToggle.isChecked());
    }


    // ----------------------------------------------------------
    /**
     * What happens when player one slaps
     */
    public void Slap1Clicked()
    {
        if (b.pile.size() >= 2)
        {
            if (b.isValidSlap())
            {
                one.pickUp(b.getPile());
                oneNumCards.setText("Number of Cards: " +
                    b.getP1Hand().size());
                playerOneTurn = true;
                Play1.setEnabled(true);
                Play2.setEnabled(false);
                one.setCurrentRestriction(0);
                two.setCurrentRestriction(0);
                Played.setImageResource(getResources().getIdentifier(
                    "ec", "drawable", getPackageName()));
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * What happens when player two slaps
     */
    public void Slap2Clicked()
    {
        if (b.pile.size() >= 2)
        {
            if (b.isValidSlap())
            {
                two.pickUp(b.getPile());
                twoNumCards.setText("Number of Cards: " +
                    b.getP2Hand().size());
                playerOneTurn = false;
                Play1.setEnabled(false);
                Play2.setEnabled(true);
                one.setCurrentRestriction(0);
                two.setCurrentRestriction(0);
                Played.setImageResource(getResources().getIdentifier(
                    "ec", "drawable", getPackageName()));
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Converts a card object to a usable string object
     * @param c is the card being turn into string
     * @return string to be sent into drawable command
     */
    public String card2String(PlayingCard c)
    {
        String hester = "";
        switch (c.getSuit())
        {
            case 1:
                hester = "d";
                break;
            case 2:
                hester = "c";
                break;
            case 3:
                hester = "h";
                break;
            case 4:
                hester = "s";
                break;
        }

        switch (c.getValue())
        {
            case 11:
                hester = hester + "j";
                break;
            case 12:
                hester = hester + "q";
                break;
            case 13:
                hester = hester + "k";
                break;
            default:
                hester = hester + c.getValue();
                break;
        }

        return hester;
    }

    // ----------------------------------------------------------
    /**
     * Returns the board being used
     * @return board being used
     */
    public Board getBoard()
    {
        return b;
    }

    // ----------------------------------------------------------
    /**
     * Creates a dialog of the winning player
     * @param playerName the name of the winning player
     */
    public void gameWon(String playerName)
    {
        Play1.setEnabled(false);
        Play2.setEnabled(false);
        Slap1.setEnabled(false);
        Slap2.setEnabled(false);
        Dialog winMessage = new Dialog(this);
        TextView winningPlayer = new TextView(this);
        winningPlayer.setText(playerName + " has won!");
        winMessage.setTitle("Winner!");
        winMessage.setContentView(winningPlayer);
        winMessage.show();
    }

}
