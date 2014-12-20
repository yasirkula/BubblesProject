package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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
    private JTextPane textPane;/*Bio;
    private JTextPane textPaneChem;
    private JTextPane textPaneVocab;*/
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
        /*textPaneBio = new JTextPane();
        textPaneChem = new JTextPane();
        textPaneVocab = new JTextPane();
        
        textPaneBio.setText( "bio" );
        textPaneChem.setText( "chem" );
        textPaneVocab.setText( "vocab" );  */      
        textPane.setText( MenuManager.getInstance().getScores().getNames( "biology" ).get( 0 ) );
        
        // Set colors and font
        Font f = new Font( "Default", Font.PLAIN, 23 );
        textPane.setEditable( false );
        textPane.setBackground( new Color( 237, 237, 237 ) );
        textPane.setForeground( Color.BLACK );
        textPane.setFont( f );
        /*textPaneBio.setEditable( false );
        textPaneBio.setBackground( new Color( 237, 237, 237 ) );
        textPaneBio.setForeground( Color.BLACK );
        textPaneBio.setFont( f );
        textPaneChem.setEditable( false );
        textPaneChem.setBackground( new Color( 237, 237, 237 ) );
        textPaneChem.setForeground( Color.BLACK );
        textPaneChem.setFont( f );
        textPaneVocab.setEditable( false );
        textPaneVocab.setBackground( new Color( 237, 237, 237 ) );
        textPaneVocab.setForeground( Color.BLACK );
        textPaneVocab.setFont( f );*/
        
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
	public void actionPerformed( ActionEvent e ) 
	{
		if( e.getSource() == backButton )
    		MenuManager.getInstance().changeMenu( (Menu) new MainMenu() );
		else if( e.getSource() == resetButton ) 
		{
			/*if( textPane.getText() == "Biology Scores" )
				System.out.println( "Reset bio" );
			else if( textPane.getText() == "Chemistry Scores" )
				System.out.println( "Reset chem" );
			else if( textPane.getText() == "Vocabulary Scores" )
				System.out.println( "Reset vocab" );*/
			System.out.println( "reset scores" );
		}
		else if( e.getSource() == bioScoresButton )
		{
			//scrollPane.setViewportView( textPaneBio );
			textPane.setText( MenuManager.getInstance().getScores().getNames( "biology" ).get( 0 ) );
		}
		else if( e.getSource() == chemScoresButton )
		{
			//scrollPane.setViewportView( textPaneChem );
			textPane.setText(MenuManager.getInstance().getScores().getNames( "chemistry" ).get( 0 ) );
		}
		else if( e.getSource() == vocabScoresButton )
		{
			//scrollPane.setViewportView( textPaneVocab );
			textPane.setText(MenuManager.getInstance().getScores().getNames( "vocabulary" ).get( 0 ) );
		}
	}
	// END OF OTHER METHODS
}