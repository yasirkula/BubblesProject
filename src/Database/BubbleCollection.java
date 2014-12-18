package Database;

import java.util.ArrayList;
import GameAssets.Bubble;
import GameAssets.TextContent;

public class BubbleCollection
{
	// VARIABLES
	private ArrayList<Bubble> bioBubbles;
	private ArrayList<Bubble> bioMatchBubbles;
	private ArrayList<Bubble> chemBubbles;
	private ArrayList<Bubble> chemMatchBubbles;
	private ArrayList<Bubble> vocabBubbles;
	private ArrayList<Bubble> vocabMatchBubbles;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public BubbleCollection()
	{
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
		
		vocabBubbles.add( new Bubble( new TextContent( "Good" ) ) );
		vocabMatchBubbles.add( new Bubble( new TextContent( "Bad" ) ) );
		vocabBubbles.add( new Bubble( new TextContent( "Early" ) ) );
		vocabMatchBubbles.add( new Bubble( new TextContent( "Late" ) ) );
		vocabBubbles.add( new Bubble( new TextContent( "Big" ) ) );
		vocabMatchBubbles.add( new Bubble( new TextContent( "Small" ) ) );
		vocabBubbles.add( new Bubble( new TextContent( "Left" ) ) );
		vocabMatchBubbles.add( new Bubble( new TextContent( "Right" ) ) );
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
	// END OF OTHER METHODS
}