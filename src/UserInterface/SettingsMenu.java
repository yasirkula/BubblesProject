/**
 * SettingsMenu - Draws the settings menu where
 * 				  several settings can be changed and
 * 				  SettingsManager can be notified of
 * 				  these changes
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
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import GameManagement.GameEngine;
import GameManagement.MenuManager;

public class SettingsMenu extends Menu implements ChangeListener
{
	// VARIABLES
	private JColorChooser colorPicker;
	private JSlider soundLevel;
	private JButton backButton;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public SettingsMenu()
	{
		super();
		
		BorderLayout layout = new BorderLayout( 0, 25 );
		setLayout( layout );

		JPanel centerPanel = new JPanel();
		JPanel centerBottomPanel = new JPanel();
		JPanel centerTopPanel = new JPanel();
		JLabel bgColorLabel = new JLabel( "BACKGROUND COLOR", SwingConstants.CENTER );
		JLabel soundLevelLabel = new JLabel( "SOUND LEVEL", SwingConstants.CENTER );
		
		// Set font
        Font f = new Font( "Default", Font.PLAIN, 23 );
        bgColorLabel.setFont( f );
        soundLevelLabel.setFont( f );
        
		GridLayout centerLayout = new GridLayout( 2, 1, 0, 25 );
		BorderLayout centerTopLayout =  new BorderLayout();
		BorderLayout centerBottomLayout =  new BorderLayout( 0, 15 );
		
		centerPanel.setLayout( centerLayout );
		centerTopPanel.setLayout( centerTopLayout );
		centerBottomPanel.setLayout( centerBottomLayout );
		
		centerTopPanel.add( soundLevelLabel, BorderLayout.PAGE_START );
		centerTopPanel.add( soundLevel, BorderLayout.CENTER );
		centerTopPanel.add( new JSeparator(), BorderLayout.PAGE_END );
		centerBottomPanel.add( bgColorLabel, BorderLayout.PAGE_START );
		centerBottomPanel.add( colorPicker, BorderLayout.CENTER );
		
		centerPanel.add( centerTopPanel );
		centerPanel.add( centerBottomPanel );
		
		centerPanel.setBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED ) );
		
		// put some white space at the edges so that interface
		// looks nicer
		Dimension gap = new Dimension( 25, 25 );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.PAGE_START );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_START );
		add( centerPanel, BorderLayout.CENTER );
		add( new Box.Filler( gap, gap, gap ), BorderLayout.LINE_END );
		add( backButton, BorderLayout.PAGE_END );
		
		setBackground( MenuManager.getInstance().getSettings().getBackgroundColor() );
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	protected void initComponents()
	{
		// Initialize components
		colorPicker = new JColorChooser( MenuManager.getInstance().getSettings().getBackgroundColor() );
		soundLevel = new JSlider( JSlider.HORIZONTAL, 0, 100, 
				(int)( MenuManager.getInstance().getSettings().getSoundLevel() * 100 ) ); // min, max, initial
		backButton = new JButton();
		
		colorPicker.setPreviewPanel( new JPanel() );
		colorPicker.getSelectionModel().addChangeListener( this );
		AbstractColorChooserPanel[] colorPickerPanels = new AbstractColorChooserPanel[1];
		for( int i = 0; i < colorPicker.getChooserPanels().length; i++ )
		{
			if( colorPicker.getChooserPanels()[i].getClass().getName().equals("javax.swing.colorchooser.DefaultSwatchChooserPanel") )
				colorPickerPanels[0] = colorPicker.getChooserPanels()[i];
		}
		colorPicker.setChooserPanels( colorPickerPanels );
		
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( 0 ), new JLabel( "SILENT" ) );
		labelTable.put( new Integer( 100 ), new JLabel( "LOUD" ) );
		soundLevel.setLabelTable( labelTable );
		soundLevel.setMajorTickSpacing( 25 );
		soundLevel.setMinorTickSpacing( 5 );
		soundLevel.setPaintTicks( true );
		soundLevel.setPaintLabels( true );
		soundLevel.addChangeListener( this );
		
		backButton.setIcon( new ImageIcon( "buttons/BackButton.png" ) );
        backButton.setBorderPainted( false );
        backButton.setContentAreaFilled( false );
        backButton.setFocusPainted( false );
        backButton.addActionListener( this );
	}
	
	public void actionPerformed( ActionEvent e ) 
	{
		if( e.getSource() == backButton )
		{
			GameEngine.getInstance().playSound( "sounds/buttonClick.wav" );
			
			// save settings and return to main menu
			MenuManager.getInstance().getSettings().writeSettings();
    		MenuManager.getInstance().changeMenu( new MainMenu() );
		}
	}
	
	public void stateChanged( ChangeEvent e ) 
	{
		if( e.getSource() == soundLevel )
		{
			// sound level is changed, notify the settings manager
			MenuManager.getInstance().getSettings().setSoundLevel( soundLevel.getValue() / 100f );
		}
		else if( e.getSource() == colorPicker.getSelectionModel() )
		{
			// background color is changed, notify the settings manager
			Color c = colorPicker.getColor();
			MenuManager.getInstance().getSettings().setBackgroundColor( c );
			setBackground( c );
		}
	}
	// END OF OTHER METHODS
}