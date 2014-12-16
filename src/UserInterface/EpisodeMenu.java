package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import GameManagement.MenuManager;

public class EpisodeMenu extends Menu
{
	// VARIABLES
	private JButton bioButton;
	private JButton chemButton;
	private JButton vocabButton;
	private JButton backButton;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public EpisodeMenu()
	{
		super();
		
		BorderLayout outerLayout = new BorderLayout( 0, 25 );
        GridLayout innerLayout = new GridLayout( 1, 3, 10, 50 );
        JPanel middlePanel = new JPanel();
		
        setLayout( outerLayout );
		middlePanel.setLayout( innerLayout );
		middlePanel.setBackground( new Color( 255, 255, 255 ) );
		
		middlePanel.add( bioButton );
		middlePanel.add( chemButton );
		middlePanel.add( vocabButton );
		
		Dimension gap = new Dimension( 25, 25 );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.PAGE_START );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_START );
		add( middlePanel, BorderLayout.CENTER );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_END );
		add( backButton, BorderLayout.PAGE_END );
		
		setBackground( new Color( 255, 255, 255 ) );
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	protected void initComponents()
	{
		// Initialize components
		bioButton = new JButton( "BIOLOGY" );
		chemButton = new JButton( "CHEMISTRY" );
		vocabButton = new JButton( "VOCABULARY" );
        backButton = new JButton();
        
        // Set colors and font
        Font f = new Font( "Default", Font.BOLD, 23 );
        /*textPane.setEditable( false );
        textPane.setBackground( new Color( 237, 237, 237 ) );
        textPane.setForeground( Color.BLACK );
        textPane.setFont( f );*/
        
        bioButton.setFont( f );
        chemButton.setFont( f );
        vocabButton.setFont( f );
        
        bioButton.setBackground( new Color( 170, 157, 255 ) );
        chemButton.setBackground( new Color( 174, 255, 157 ) );
        vocabButton.setBackground( new Color( 240, 255, 157 ) );
        
        backButton.setIcon( new ImageIcon( getClass().getResource("/images/BackButton.png") ) );
        backButton.setBorderPainted( false );
        backButton.setContentAreaFilled( false );
        backButton.setFocusPainted( false );
        
        bioButton.addActionListener( this );
        chemButton.addActionListener( this );
        vocabButton.addActionListener( this );
        backButton.addActionListener( this );
	}
	
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == backButton )
		{
			MenuManager.getInstance().changeMenu( (Menu) new MainMenu() );
		}
		else if( e.getSource() == bioButton )
		{
			System.out.println( "BIOLOGY" );
		}
		else if( e.getSource() == chemButton )
		{
			System.out.println( "CHEMISTRY" );
		}
		else if( e.getSource() == vocabButton )
		{
			System.out.println( "VOCABULARY" );
		}
	}
	// END OF OTHER METHODS
}