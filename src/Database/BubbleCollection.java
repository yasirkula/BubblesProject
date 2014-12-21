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

/**
 * BubbleCollection - A database that fetches bubble data from local disk
 * 
 * @author CS319 - Section 2 - Group 9
 */

package Database;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

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
		if( 2 * size + trapBubblesSize > 2 * vocabBubbles.size() )
		{
			if( size > vocabBubbles.size() )
			{
				size = vocabBubbles.size();
				trapBubblesSize = 0;
			}
			else
			{
				trapBubblesSize = 2 * ( vocabBubbles.size() - size );
			}
		}

		int[] randIndexes = generateRandomIndexes( size, vocabBubbles.size() );
		for( int i = 0; i < size; i++ )
		{
			bubbles.add( vocabBubbles.get( randIndexes[i] ) );
			matchBubbles.add( vocabMatchBubbles.get( randIndexes[i]) );
		}

		int[] randIndexesForTraps = generateRandomIndexesExceptForSelected( trapBubblesSize, vocabBubbles.size(),randIndexes );
		for( int i = 0; i < trapBubblesSize; i++ )
		{
			// fetch half of the trap bubbles from bubbles and other half from matchingBubbles
			if( i % 2  == 0 )
				trapBubbles.add( vocabBubbles.get( randIndexesForTraps[i] ) );
			else
				trapBubbles.add( vocabMatchBubbles.get( randIndexesForTraps[i] ) );
		}
	}

	public void getBioBubbles( int size, int trapBubblesSize,
			ArrayList<Bubble> bubbles, 
			ArrayList<Bubble> matchBubbles,
			ArrayList<Bubble> trapBubbles )
	{
		if( 2 * size + trapBubblesSize > 2 * bioBubbles.size() )
		{
			if( size > bioBubbles.size() )
			{
				size = bioBubbles.size();
				trapBubblesSize = 0;
			}
			else
			{
				trapBubblesSize = 2 * ( bioBubbles.size() - size );
			}
		}


		int[] randIndexes = generateRandomIndexes( size, bioBubbles.size() );
		for( int i = 0; i < 10; i++ )
		{
			bubbles.add( bioBubbles.get( randIndexes[i] ) );
			matchBubbles.add( bioMatchBubbles.get( randIndexes[i] ) );
		}

		int[] randIndexesForTraps = generateRandomIndexesExceptForSelected( trapBubblesSize, bioMatchBubbles.size(),randIndexes );
		for( int i = 0; i < trapBubblesSize; i++ )
		{
			// fetch half of the trap bubbles from bubbles and other half from matchingBubbles
			if( i % 2  == 0 )
				trapBubbles.add( bioBubbles.get( randIndexesForTraps[i] ) );
			else
				trapBubbles.add( bioMatchBubbles.get( randIndexesForTraps[i] ) );
		}

	}

	public void getChemBubbles( int size, int trapBubblesSize,
			ArrayList<Bubble> bubbles, 
			ArrayList<Bubble> matchBubbles,
			ArrayList<Bubble> trapBubbles )
	{
		if( 2 * size + trapBubblesSize > 2 * chemBubbles.size() )
		{
			if( size > chemBubbles.size() )
			{
				size = chemBubbles.size();
				trapBubblesSize = 0;
			}
			else
			{
				trapBubblesSize = 2 * ( chemBubbles.size() - size );
			}
		}

		int[] randIndexes = generateRandomIndexes( size, chemBubbles.size() );
		for( int i = 0; i < size; i++ )
		{
			bubbles.add( chemBubbles.get( randIndexes[i] ) );
			matchBubbles.add( chemMatchBubbles.get( randIndexes[i] ) );
		}

		int[] randIndexesForTraps = generateRandomIndexesExceptForSelected( trapBubblesSize, chemMatchBubbles.size(),randIndexes );
		for( int i = 0; i < trapBubblesSize; i++ )
		{
			// fetch half of the trap bubbles from bubbles and other half from matchingBubbles
			if( i % 2  == 0 )
				trapBubbles.add( chemBubbles.get( randIndexesForTraps[i] ) );
			else
				trapBubbles.add( chemMatchBubbles.get( randIndexesForTraps[i] ) );
		}
	}

	// generate output_size random integers from a range between 0 to total_size-1
	public int[] generateRandomIndexes( int output_size, int total_size )
	{
		Random r = new Random( System.currentTimeMillis() );
		HashSet<Integer> randomSet = new HashSet<Integer>(); 
		
		// to not regenerate the already generated index
		// it will keep previously generated numbers
		int[] randomIndexes = new int[output_size]; // empty array for <output_size> numbers
		for( int i = 0; i < output_size; i++ )
		{
			int x;
			do
			{
				x = r.nextInt( total_size );
			} while( randomSet.contains( x ) );
			
			randomSet.add( x );
			randomIndexes[i] = x;
		}
		
		return randomIndexes;
	}
	
	public int[] generateRandomIndexesExceptForSelected( int output_size, int total_size, int[] selected )
	{
		Random r = new Random( System.currentTimeMillis() );
		HashSet<Integer> randomSet = new HashSet<Integer>();
		
		for( int i=0; i < selected.length; i++ )
		{	
			// add previously generated numbers from selected[]
			randomSet.add( selected[i] ); 
		}
		
		int[] randomIndexes = new int[output_size];
		for( int i = 0; i < output_size; i++ )
		{
			int x;
			do
			{
				x = r.nextInt( total_size );
			} while( randomSet.contains( x ) );
			
			randomSet.add( x );
			randomIndexes[i] = x;
		}
		
		return randomIndexes;
	}


	public Content getContent( ContentType type )
	{
		return factory.getContent( type );
	}
	// END OF OTHER METHODS
}
