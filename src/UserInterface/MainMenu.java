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

        newGameButton.setIcon( new ImageIcon( getClass().getResource("/images/NewGameButton.png") ) );
        newGameButton.setBorderPainted( false );
        newGameButton.setContentAreaFilled( false );
        newGameButton.setFocusPainted( false );
        newGameButton.addActionListener( this );

        settingsButton.setIcon( new ImageIcon( getClass().getResource("/images/SettingsButton.png") ) );
        settingsButton.setBorderPainted( false );
        settingsButton.setContentAreaFilled( false );
        settingsButton.setFocusPainted( false );
        settingsButton.addActionListener( this );

        creditsButton.setIcon( new ImageIcon( getClass().getResource("/images/CreditsButton.png") ) );
        creditsButton.setBorderPainted( false );
        creditsButton.setContentAreaFilled( false );
        creditsButton.setFocusPainted( false );
        creditsButton.addActionListener( this );

        highscoresButton.setIcon( new ImageIcon( getClass().getResource("/images/HighScoresButton.png") ) );
        highscoresButton.setBorderPainted( false );
        highscoresButton.setContentAreaFilled( false );
        highscoresButton.setFocusPainted( false );
        highscoresButton.addActionListener( this );

        helpButton.setIcon( new ImageIcon( getClass().getResource("/images/HelpButton.png") ) );
        helpButton.setBorderPainted( false );
        helpButton.setContentAreaFilled( false );
        helpButton.setFocusPainted( false );
        helpButton.addActionListener( this );

        exitButton.setIcon( new ImageIcon( getClass().getResource("/images/ExitButton.png") ) );
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
        	Menu m = new CreditsMenu();
        	String creditsText = "\n(-(-_(-_-)_-)-)\n\n" +
        						 "Four brilliant students worked on this project\n\n" +
        						 "Esra Zeynep Kurt / 21101384\nRana Kösterit / 21100715\n" +
        						 "Süleyman Yasir Kula / 21200823\nTan Küçükoðlu / 21201893\n" +
        						 "\n(>'.')>   <('.'<)";
        	( (CreditsMenu) m ).setCreditsText( creditsText );
        	MenuManager.getInstance().changeMenu( m );
        }
        else if( e.getSource() == highscoresButton )
        {
        	Menu m = new HighScoresMenu();
        	MenuManager.getInstance().changeMenu( m );
        }
        else if( e.getSource() == helpButton )
        {
        	Menu m = new HelpMenu();
        	String helpText = "\nSuch help document\n\n" +
        						 "many content\n\n" +
        						 "so helpful\n\n" +
        						 "wow\n";
        	( (HelpMenu) m ).setHelpText( helpText );
        	MenuManager.getInstance().changeMenu( m );
        }
        else if( e.getSource() == exitButton )
        {
        	MenuManager.getInstance().exitGame();
        }
    } 
	// END OF OTHER METHODS
}