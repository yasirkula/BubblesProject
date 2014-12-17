package UserInterface;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import GameManagement.MenuManager;

public abstract class Menu extends JPanel implements ActionListener
{
	// VARIABLES
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public Menu()
	{
		initComponents();
		
		setBackground( MenuManager.getInstance().getSettings().getBackgroundColor() );
	}
	// END OF CONSTRUCTORS
	
	// OTHER METHODS
	public void paintComponent( Graphics g )
	{
		// Gradient background
		// Inspired from: http://stackoverflow.com/questions/12220853/how
		//				  -to-make-the-background-gradient-of-a-jpanel
		// a written answer by: trashgod
		super.paintComponent( g );
        Graphics2D g2d = ( Graphics2D ) g;
        Color colorTop = getBackground();
        Color colorBottom = colorTop.darker();
        GradientPaint gra = new GradientPaint( 0, 0, colorTop, 0, getHeight(), colorBottom );
        g2d.setPaint( gra );
        g2d.fillRect( 0, 0, getWidth(), getHeight() );
	}
	protected abstract void initComponents(); 
	// END OF OTHER METHODS
}