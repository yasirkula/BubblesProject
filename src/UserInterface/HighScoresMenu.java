/**
 * HighScoresMenu - Shows the highscores for each episode
 * 
 * @author CS319 - Section 2 - Group 9
 */

package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import GameAssets.EpisodeType;
import GameManagement.MenuManager;

public class HighScoresMenu extends Menu
{
	// VARIABLES
	private JButton backButton;
	private JButton resetButton;
	private JButton bioScoresButton;
	private JButton chemScoresButton;
	private JButton vocabScoresButton;
	private JScrollPane scrollPane;
    private JTextPane textPane;
    
    private EpisodeType currentlyShownEpisode;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public HighScoresMenu()
	{
		super();
		
		BorderLayout outerLayout = new BorderLayout( 0, 25 );
		GridLayout topLayout = new GridLayout( 1, 3 );
		GridLayout bottomLayout = new GridLayout( 1, 2 );
		
		JPanel topPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		
		setLayout( outerLayout );
		topPanel.setLayout( topLayout );
		topPanel.setOpaque( false );
		lowerPanel.setLayout( bottomLayout );
		lowerPanel.setOpaque( false );
		
		topPanel.add( bioScoresButton );
		topPanel.add( chemScoresButton );
		topPanel.add( vocabScoresButton );
		
		lowerPanel.add( resetButton );
		lowerPanel.add( backButton );
		
		// put some white space at the edges so that interface
		// looks nicer
		Dimension gap = new Dimension( 25, 25 );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_START );
		add( topPanel, BorderLayout.PAGE_START );
		add( scrollPane, BorderLayout.CENTER );
		add( lowerPanel, BorderLayout.PAGE_END );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_END );
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	protected void initComponents() 
	{
		// Initialize components
        backButton = new JButton();
        resetButton = new JButton();
        bioScoresButton = new JButton( "Biology Scores" );
        chemScoresButton = new JButton( "Chemistry Scores" );
        vocabScoresButton = new JButton( "Vocabulary Scores" );
        scrollPane = new JScrollPane();
        textPane = new JTextPane();
        
        // Initially, show the highscores for biology episode
        showScoresOf( EpisodeType.BIOLOGY );
        
        // Set colors and font
        Font f = new Font( "Default", Font.PLAIN, 20 );
        textPane.setEditable( false );
        textPane.setBackground( new Color( 237, 237, 237 ) );
        textPane.setForeground( Color.BLACK );
        textPane.setFont( f );
        
        // Center the text
        StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment( center, StyleConstants.ALIGN_CENTER );
		doc.setParagraphAttributes( 0, doc.getLength(), center, false );
		
        scrollPane.setViewportView( textPane );

        backButton.setIcon( new ImageIcon( getClass().getResource("/images/BackButton.png") ) );
        backButton.setBorderPainted( false );
        backButton.setContentAreaFilled( false );
        backButton.setFocusPainted( false );
        backButton.addActionListener( this );
        
        resetButton.setIcon( new ImageIcon( getClass().getResource("/images/ResetButton.png") ) );
        resetButton.setBorderPainted( false );
        resetButton.setContentAreaFilled( false );
        resetButton.setFocusPainted( false );
        resetButton.addActionListener( this );
        
        bioScoresButton.setFont( f );
        chemScoresButton.setFont( f );
        vocabScoresButton.setFont( f );
        
        bioScoresButton.setBackground( new Color( 170, 157, 255 ) );
        chemScoresButton.setBackground( new Color( 174, 255, 157 ) );
        vocabScoresButton.setBackground( new Color( 240, 255, 157 ) );
        
        bioScoresButton.addActionListener( this );
        chemScoresButton.addActionListener( this );
        vocabScoresButton.addActionListener( this );
	}
	
	private void showScoresOf( EpisodeType episode )
	{
		// Loads the highscores of the selected episode from the 
		// HighScores object
		if( episode != null )
		{
			currentlyShownEpisode = episode;
			String text = "";
			
			switch( episode )
			{
				case BIOLOGY: text = "- BIOLOGY -\n\n"; break;
				case CHEMISTRY: text = "- CHEMISTRY -\n\n"; break;
				case VOCABULARY: text = "- VOCABULARY -\n\n"; break;
			}
			
			ArrayList<Integer> scores = MenuManager.getInstance()
					.getScores().getScores( episode );
			ArrayList<String> names = MenuManager.getInstance()
					.getScores().getNames( episode );
			
			for( int i = 0; i < scores.size(); i++ )
			{
				text += ( i + 1 ) + ") " + names.get( i ) + " " + scores.get( i );
				
				if( i != scores.size() - 1 )
					text += "\n\n";
			}
			
			textPane.setText( text );
		}
	}
	
	public void actionPerformed( ActionEvent e ) 
	{
		if( e.getSource() == backButton )
    		MenuManager.getInstance().changeMenu( new MainMenu() );
		else if( e.getSource() == resetButton ) 
		{
			// reset highscores
			MenuManager.getInstance().getScores().resetHighScores();
			
			// refresh the highscores table
			showScoresOf( currentlyShownEpisode );
		}
		else if( e.getSource() == bioScoresButton )
		{
			showScoresOf( EpisodeType.BIOLOGY );
		}
		else if( e.getSource() == chemScoresButton )
		{
			showScoresOf( EpisodeType.CHEMISTRY );
		}
		else if( e.getSource() == vocabScoresButton )
		{
			showScoresOf( EpisodeType.VOCABULARY );
		}
	}
	// END OF OTHER METHODS
}