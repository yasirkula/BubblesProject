package Driver;

import GameManagement.MenuManager;
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
		gameFrame = new JFrame();
		MenuManager menuManager = new MenuManager();
		activePanel = (JPanel) new MainMenu();
		gameFrame.add( activePanel );
		gameFrame.setSize( new Dimension( 800, 600 ) );
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
}
