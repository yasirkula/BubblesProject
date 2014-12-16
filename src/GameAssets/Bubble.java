package GameAssets;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.Point2D;

public class Bubble
{
	// VARIABLES
	private Color bubbleColor;
	private float radius;
	private Point2D location;
	private Content content;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public Bubble()
	{
		
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public Content getContent()
	{
		return content;
	}
	
	public void setContent( Content c )
	{
		content = c;
	}
	
	public float getRadius()
	{
		return radius;
	}
	
	public void setRadius( float r )
	{
		radius = r;
	}
	
	public Point2D getLocation()
	{
		return location;
	}
	
	public void setLocation( Point2D l )
	{
		location = l;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	public void draw( Graphics g )
	{
	
	}
	
	public boolean contains( Point2D mousePos )
	{
		return true;
	}
	// END OF OTHER METHODS
}