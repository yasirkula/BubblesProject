/**
 * MainMenu - Draws the main menu
 * 
 * @author CS319 - Section 2 - Group 9
 */

package UserInterface;

import GameManagement.MenuManager;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class MainMenu extends Menu
{
	// VARIABLES
	private JButton newGameButton;
    private JButton settingsButton;
    private JButton creditsButton;
    private JButton highscoresButton;
    private JButton helpButton;
    private JButton exitButton;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public MainMenu()
	{
		super();
		
		GridLayout layout = new GridLayout( 6, 1 );
		setLayout( layout );
		
		add( newGameButton );
		add( settingsButton );
		add( creditsButton );
		add( highscoresButton );
		add( helpButton );
		add( exitButton );
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	protected void initComponents() 
    {
		
        newGameButton = new JButton();
        settingsButton = new JButton();
        creditsButton = new JButton();
        highscoresButton = new JButton();
        helpButton = new JButton();
        exitButton = new JButton();

        newGameButton.setIcon( new ImageIcon( "buttons/NewGameButton.png" ) );
        newGameButton.setBorderPainted( false );
        newGameButton.setContentAreaFilled( false );
        newGameButton.setFocusPainted( false );
        newGameButton.addActionListener( this );

        settingsButton.setIcon( new ImageIcon( "buttons/SettingsButton.png" ) );
        settingsButton.setBorderPainted( false );
        settingsButton.setContentAreaFilled( false );
        settingsButton.setFocusPainted( false );
        settingsButton.addActionListener( this );

        creditsButton.setIcon( new ImageIcon( "buttons/CreditsButton.png" ) );
        creditsButton.setBorderPainted( false );
        creditsButton.setContentAreaFilled( false );
        creditsButton.setFocusPainted( false );
        creditsButton.addActionListener( this );

        highscoresButton.setIcon( new ImageIcon( "buttons/HighScoresButton.png" ) );
        highscoresButton.setBorderPainted( false );
        highscoresButton.setContentAreaFilled( false );
        highscoresButton.setFocusPainted( false );
        highscoresButton.addActionListener( this );

        helpButton.setIcon( new ImageIcon( "buttons/HelpButton.png" ) );
        helpButton.setBorderPainted( false );
        helpButton.setContentAreaFilled( false );
        helpButton.setFocusPainted( false );
        helpButton.addActionListener( this );

        exitButton.setIcon( new ImageIcon( "buttons/ExitButton.png" ) );
        exitButton.setBorderPainted( false );
        exitButton.setContentAreaFilled( false );
        exitButton.setFocusPainted( false );
        exitButton.addActionListener( this );
    }                       
	
	public void actionPerformed( ActionEvent e )
	{
        if( e.getSource() == newGameButton )
        {
        	MenuManager.getInstance().changeMenu( new EpisodeMenu() );
        }
        else if( e.getSource() == settingsButton )
        {
        	MenuManager.getInstance().changeMenu( new SettingsMenu() );
        }
        else if( e.getSource() == creditsButton )
        {
        	MenuManager.getInstance().changeMenu( new CreditsMenu() );
        }
        else if( e.getSource() == highscoresButton )
        {
        	MenuManager.getInstance().changeMenu( new HighScoresMenu() );
        }
        else if( e.getSource() == helpButton )
        {
        	MenuManager.getInstance().changeMenu( new HelpMenu( false ) );
        }
        else if( e.getSource() == exitButton )
        {
        	MenuManager.getInstance().exitGame();
        }
    } 
	// END OF OTHER METHODS
}