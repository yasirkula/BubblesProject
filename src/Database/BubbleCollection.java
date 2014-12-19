/*
 * Animal images are taken from:
 * http://www.animalcorner.co.uk
 *
 * Vocabulary words are taken from:
 * http://examples.yourdictionary.com/
 * examples-of-antonyms-synonyms-and-homonyms.html
 * http://examples.yourdictionary.com/
 * examples-of-antonyms.html
 * http://www.englishleap.com/vocabulary/antonym
 * 
 * Chemistry words are taken from:
 * periodic table (obviously)
 */

package Database;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
		
		// Inspired from http://docs.oracle.com/javase/tutorial/essential/io/file.html
		// and
		// http://stackoverflow.com/questions/14918188/reading
		// -text-file-with-utf-8-encoding-using-java
		// answered by: Shobit Sharma
		
		//
		// Fetch BIOLOGY bubble data from the disk
		//
		BufferedReader buffer = null;
		
		try
		{
			buffer = new BufferedReader( new InputStreamReader( 
					new FileInputStream( "dataBiology.dat" ), "UTF-8" ) );
		    
			String line = null;
		    while( ( line = buffer.readLine() ) != null ) 
		    {
		        String[] tokens = line.split( " " );

	        	try
	        	{
	        		Bubble b = new Bubble( getContent( ContentType.IMAGE ) );
	    			( (ImageContent) b.getContent() ).setImage( tokens[1] );
	    			bioBubbles.add( b );
	    			
	    			b = new Bubble( getContent( ContentType.TEXT ) );
	    			( (TextContent) b.getContent() ).setText( tokens[0] );
	    			bioMatchBubbles.add( b );
	        	}
	        	catch( Exception e ){}
		    }
		} 
		catch( IOException x ){}
		finally 
		{
		   try 
		   {
			   buffer.close();
		   } 
		   catch( Exception ex ){}
		}
		
		//
		// Fetch VOCABULARY bubble data from the disk
		//
		try
		{
			buffer = new BufferedReader( new InputStreamReader( 
					new FileInputStream( "dataVocabulary.dat" ), "UTF-8" ) );
		    
			String line = null;
		    while( ( line = buffer.readLine() ) != null ) 
		    {
		        String[] tokens = line.split( " " );

	        	try
	        	{
	        		Bubble b = new Bubble( getContent( ContentType.TEXT ) );
	    			( (TextContent) b.getContent() ).setText( tokens[1] );
	    			vocabBubbles.add( b );
	    			
	    			b = new Bubble( getContent( ContentType.TEXT ) );
	    			( (TextContent) b.getContent() ).setText( tokens[0] );
	    			vocabMatchBubbles.add( b );
	        	}
	        	catch( Exception e ){}
		    }
		} 
		catch( IOException x ){}
		finally 
		{
		   try 
		   {
			   buffer.close();
		   } 
		   catch( Exception ex ){}
		}
		
		//
		// Fetch CHEMISTRY bubble data from the disk
		//
		try
		{
			buffer = new BufferedReader( new InputStreamReader( 
					new FileInputStream( "dataChemistry.dat" ), "UTF-8" ) );
		    
			String line = null;
		    while( ( line = buffer.readLine() ) != null ) 
		    {
		        String[] tokens = line.split( " " );

	        	try
	        	{
	        		Bubble b = new Bubble( getContent( ContentType.TEXT ) );
	    			( (TextContent) b.getContent() ).setText( tokens[1] );
	    			chemBubbles.add( b );
	    			
	    			b = new Bubble( getContent( ContentType.TEXT ) );
	    			( (TextContent) b.getContent() ).setText( tokens[0] );
	    			chemMatchBubbles.add( b );
	        	}
	        	catch( Exception e ){}
		    }
		} 
		catch( IOException x ){}
		finally 
		{
		   try 
		   {
			   buffer.close();
		   } 
		   catch( Exception ex ){}
		}
	}
	
	public void getVocabBubbles( int size, int trapBubblesSize,
										   ArrayList<Bubble> bubbles, 
										   ArrayList<Bubble> matchBubbles,
										   ArrayList<Bubble> trapBubbles )
	{
		for( int i = 0; i < size; i++ )
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
		for( int i = 0; i < 20; i++ )
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
		for( int i = 0; i < size; i++ )
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