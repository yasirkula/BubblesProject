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
		gameFrame = new JFrame( "BubblesProject" );
		activePanel = (JPanel) new MainMenu();
		gameFrame.add( activePanel );
		gameFrame.setMinimumSize( new Dimension( 1024, 640 ) );
		gameFrame.setSize( new Dimension( 1024, 768 ) );
		gameFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		gameFrame.setVisible( true );		
	}
	
	public static void changeActivePanel( JPanel p )
	{
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
