package GameAssets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageContent implements Content
{
	// VARIABLES
	private BufferedImage image;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public ImageContent()
	{
		image = null;
	}
	
	public ImageContent( String path )
	{
		image = null;
		
		try 
		{
			image = ImageIO.read( new File( path ) );
		} 
		catch( IOException e ){}
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public void setImage( String path )
	{
		try 
		{
			image = ImageIO.read( new File( path ) );
		} 
		catch( IOException e ){}
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	public void draw( Graphics g, Bubble b )
	{
		g.drawImage( image, b.getX(), b.getY(), null );
	}
	// END OF OTHER METHODS
}