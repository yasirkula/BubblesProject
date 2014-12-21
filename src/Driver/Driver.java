/**
 * Driver - The class that holds the main function and JFrame,
 * 		    also responsible for changing JPanels on the JFrame
 * 
 * @author CS319 - Section 2 - Group 9
 */

package Driver;

import UserInterface.MainMenu;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Driver
{
	private static JFrame gameFrame;
	private static JPanel activePanel;
	
	public static void main( String[] args )
	{
		// Initialize the game frame and add a MainMenu panel on it
		gameFrame = new JFrame( "BubblesProject" );
		activePanel = (JPanel) new MainMenu();
		gameFrame.add( activePanel );
		
		// Set frame's size and make it visible
		gameFrame.setMinimumSize( new Dimension( 1024, 640 ) );
		gameFrame.setSize( new Dimension( 1024, 768 ) );
		gameFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		gameFrame.setVisible( true );		
	}
	
	public static void changeActivePanel( JPanel p )
	{
		// Change active JPanel on the JFrame
		// (i.e. transition between JPanels)
		gameFrame.remove( activePanel );
		activePanel = p;
		gameFrame.add( activePanel );
		gameFrame.validate();
		gameFrame.repaint();
	}
	
	public static int getFrameWidth()
	{
		return gameFrame.getWidth();
	}
	
	public static int getFrameHeight()
	{
		return gameFrame.getHeight();
	}
}
