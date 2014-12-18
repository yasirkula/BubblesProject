/*
 * Animal images taken from:
 * http://www.animalcorner.co.uk
 * 
 */

package Database;

import java.util.ArrayList;

import GameAssets.Bubble;
import GameAssets.Content;
import GameAssets.ContentType;
import GameAssets.ImageContent;
import GameAssets.TextContent;
import GameAssets.ContentFactory;

public class BubbleCollection
{
	// VARIABLES
	private ArrayList<Bubble> bioBubbles;
	private ArrayList<Bubble> bioMatchBubbles;
	private ArrayList<Bubble> chemBubbles;
	private ArrayList<Bubble> chemMatchBubbles;
	private ArrayList<Bubble> vocabBubbles;
	private ArrayList<Bubble> vocabMatchBubbles;
	
	private ContentFactory factory;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public BubbleCollection()
	{
		factory = new ContentFactory();
		// Fetch all the bubble data from the disk
		loadBubbles();
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	public void loadBubbles()
	{
		// Load the bubble data from the disk
		bioBubbles = new ArrayList<Bubble>();
		bioMatchBubbles = new ArrayList<Bubble>();
		chemBubbles = new ArrayList<Bubble>();
		chemMatchBubbles = new ArrayList<Bubble>();
		vocabBubbles = new ArrayList<Bubble>();
		vocabMatchBubbles = new ArrayList<Bubble>();
		
		for( int i = 0; i < 4; i++ )
		{
			Bubble b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Good" );
			vocabBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Bad" );
			vocabMatchBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Early" );
			vocabBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Late" );
			vocabMatchBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Big" );
			vocabBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Small" );
			vocabMatchBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Left" );
			vocabBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Right" );
			vocabMatchBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Güzel" );
			vocabBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Çirkin" );
			vocabMatchBubbles.add( b );
		}
		
		for( int i = 0; i < 4; i++ )
		{
			Bubble b = new Bubble( getContent( ContentType.IMAGE ) );
			( (ImageContent) b.getContent() ).setImage( "/images/biology/dog.png" );
			bioBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Dog" );
			bioMatchBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.IMAGE ) );
			( (ImageContent) b.getContent() ).setImage( "/images/biology/tiger.png" );
			bioBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Tiger" );
			bioMatchBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.IMAGE ) );
			( (ImageContent) b.getContent() ).setImage( "/images/biology/mouse.png" );
			bioBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Mouse" );
			bioMatchBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.IMAGE ) );
			( (ImageContent) b.getContent() ).setImage( "/images/biology/cat.png" );
			bioBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Cat" );
			bioMatchBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.IMAGE ) );
			( (ImageContent) b.getContent() ).setImage( "/images/biology/hamster.png" );
			bioBubbles.add( b );
			
			b = new Bubble( getContent( ContentType.TEXT ) );
			( (TextContent) b.getContent() ).setText( "Hamster" );
			bioMatchBubbles.add( b );
		}
	}
	
	public void getVocabBubbles( int size, int trapBubblesSize,
										   ArrayList<Bubble> bubbles, 
										   ArrayList<Bubble> matchBubbles,
										   ArrayList<Bubble> trapBubbles )
	{
		for( int i = 0; i < vocabBubbles.size(); i++ )
		{
			bubbles.add( vocabBubbles.get( i ) );
			matchBubbles.add( vocabMatchBubbles.get( i ) );
		}
	}
	
	public void getBioBubbles( int size, int trapBubblesSize,
			   ArrayList<Bubble> bubbles, 
			   ArrayList<Bubble> matchBubbles,
			   ArrayList<Bubble> trapBubbles )
	{
		for( int i = 0; i < bioBubbles.size(); i++ )
		{
			bubbles.add( bioBubbles.get( i ) );
			matchBubbles.add( bioMatchBubbles.get( i ) );
		}
	}
	
	public void getChemBubbles( int size, int trapBubblesSize,
			   ArrayList<Bubble> bubbles, 
			   ArrayList<Bubble> matchBubbles,
			   ArrayList<Bubble> trapBubbles )
	{
		for( int i = 0; i < chemBubbles.size(); i++ )
		{
			bubbles.add( chemBubbles.get( i ) );
			matchBubbles.add( chemMatchBubbles.get( i ) );
		}
	}
	
	public Content getContent( ContentType type )
	{
		return factory.getContent( type );
	}
	// END OF OTHER METHODS
}