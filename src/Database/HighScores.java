/**
 * Highscores - A database that fetches highscores from local disk
 * 				and writes them back when necessary
 * 
 * 
 * @author CS319 - Section 2 - Group 9
 */

package Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import GameAssets.EpisodeType;
import GameManagement.MenuManager;

public class HighScores
{
	// VARIABLES
	private ArrayList<Integer> bioScores;
	private ArrayList<String> bioNames;
	private ArrayList<Integer> chemScores;
	private ArrayList<String> chemNames;
	private ArrayList<Integer> vocabScores;
	private ArrayList<String> vocabNames;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public HighScores()
	{
		bioScores = new ArrayList<Integer>();
		bioNames = new ArrayList<String>();
		chemScores = new ArrayList<Integer>();
		chemNames = new ArrayList<String>();
		vocabScores = new ArrayList<Integer>();
		vocabNames = new ArrayList<String>();
		loadHighScores();
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public ArrayList<Integer> getScores( EpisodeType episode )
	{
		switch( episode )
		{
			case BIOLOGY: return bioScores;
			case CHEMISTRY: return chemScores;
			case VOCABULARY: return vocabScores;
			default: return new ArrayList<Integer>();
		}
	}
	
	public ArrayList<String> getNames( EpisodeType episode )
	{
		switch( episode )
		{
			case BIOLOGY: return bioNames;
			case CHEMISTRY: return chemNames;
			case VOCABULARY: return vocabNames;
			default: return new ArrayList<String>();
		}
	}
	
	public int getTotalScore( EpisodeType episode )
	{
		int result = 0;
		
		switch( episode )
		{
			case BIOLOGY: 
				for( Integer i : bioScores )
				{
					result += i;
				}
				break;
				
			case CHEMISTRY: 
				for( Integer i : chemScores )
				{
					result += i;
				}
				break;
				
			case VOCABULARY: 
				for( Integer i : vocabScores )
				{
					result += i;
				}
				break;
		}
		
		return result;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS	
	public void addHighScore( EpisodeType episode, int index, String name, int score )
	{
		// adds a new highscore to the table
		switch( episode )
		{
			case BIOLOGY: 
				if( score > bioScores.get( index ) )
				{
					// add the highscore only if its value is greater than the
					// current highscore
					bioScores.remove( index );
					bioScores.add( index, score );
					bioNames.remove( index );
					bioNames.add( index, name );
					
					// save changes
					saveHighScores();
				}
				break;
				
			case CHEMISTRY:
				if( score > chemScores.get( index ) )
				{
					// add the highscore only if its value is greater than the
					// current highscore
					chemScores.remove( index );
					chemScores.add( index, score );
					chemNames.remove( index );
					chemNames.add( index, name );
					
					// save changes
					saveHighScores();
				}
				break;
				
			case VOCABULARY:
				if( score > vocabScores.get( index ) )
				{
					// add the highscore only if its value is greater than the
					// current highscore
					vocabScores.remove( index );
					vocabScores.add( index, score );
					vocabNames.remove( index );
					vocabNames.add( index, name );
					
					// save changes
					saveHighScores();
				}
				break;
		}
	}
	
	public void resetHighScores()
	{
		// reset all highscores to 0
		bioNames = new ArrayList<String>();
		bioScores = new ArrayList<Integer>();
		chemNames = new ArrayList<String>();
		chemScores = new ArrayList<Integer>();
		vocabNames = new ArrayList<String>();
		vocabScores = new ArrayList<Integer>();
		
		for( int i = 0; i < MenuManager.LEVEL_COUNT; i++ )
		{
			bioNames.add( "MysteriousPerson" );
			bioScores.add( 0 );
			chemNames.add( "MysteriousPerson" );
			chemScores.add( 0 );
			vocabNames.add( "MysteriousPerson" );
			vocabScores.add( 0 );
		}
		
		// save changes to disk
		saveHighScores();
	}
	
	public void saveHighScores()
	{
		// Inspired from http://stackoverflow.com/questions/2885173/java
		// 				 -how-to-create-and-write-to-a-file
		// answered by: Bozho
		BufferedWriter buffer = null;

		try 
		{
		    buffer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( "highscores.dat" ), "UTF-8" ) );
		    
		    // Write biology scores to the file
		    buffer.write( "MODE: BIOLOGY" );
		    
		    for( int i = 0; i < bioNames.size(); i++ )
		    {
		    	buffer.newLine();
		    	buffer.write( bioNames.get( i ) + " " + bioScores.get( i ) );
		    }
		    
		    // Write chemistry scores to the file
		    buffer.write( "\n\nMODE: CHEMISTRY" );
		    
		    for( int i = 0; i < chemNames.size(); i++ )
		    {
		    	buffer.newLine();
		    	buffer.write( chemNames.get( i ) + " " + chemScores.get( i ) );
		    }
		    
		    // Write vocabulary scores to the file
		    buffer.write( "\n\nMODE: VOCABULARY" );
		    
		    for( int i = 0; i < vocabNames.size(); i++ )
		    {
		    	buffer.newLine();
		    	buffer.write( vocabNames.get( i ) + " " + vocabScores.get( i ) );
		    }
		} 
		catch( IOException ex ){} 
		finally 
		{
			try 
			{
				buffer.close();
			} 
			catch( Exception ex ){}
		}
	}
	public void loadHighScores()
	{
		// Inspired from http://docs.oracle.com/javase/tutorial/essential/io/file.html
		// and
		// http://stackoverflow.com/questions/14918188/reading
		// -text-file-with-utf-8-encoding-using-java
		// answered by: Shobit Sharma
		BufferedReader buffer = null;
		String result = "";
		try
		{
			// read the whole text in highscores.dat and store it in result
			buffer = new BufferedReader( new InputStreamReader( 
					new FileInputStream( "highscores.dat" ), "UTF-8" ) );
		    
			String line = buffer.readLine();
			if( line != null )
			{
				result = line;
			}
			
		    while( ( line = buffer.readLine() ) != null ) 
		    {
		        result += "\n" + line;
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
		
		String[] modes = result.split( "\\s*(MODE:)\\s*" );
		// skip the first element in modes, which is the part before
		// the first MODE: text
		for( int i = 1; i < modes.length; i++ )
		{
			String mode = modes[i];
			
			// split the lines
			String[] lines = mode.split( "\n" );
			if( lines[0].equalsIgnoreCase( "BIOLOGY" ) )
			{
				for( int j = 1; j < lines.length; j++ )
				{
					// split the words in the line
					String[] tokens = lines[j].split( " " );
					
					// make sure there is a name and a score
					if( tokens.length == 2 )
					{
						// try to fetch the score first so that if it is not valid, 
						// line is not valid as well
						try
			        	{
							bioScores.add( Integer.parseInt( tokens[1] ) );
							bioNames.add( tokens[0] );
			        	}
			        	catch( Exception e ){}
					}
				}
			}
			else if( lines[0].equalsIgnoreCase( "CHEMISTRY" ) )
			{
				for( int j = 1; j < lines.length; j++ )
				{
					// split the words in the line
					String[] tokens = lines[j].split( " " );
					
					// make sure there is a name and a score
					if( tokens.length == 2 )
					{
						// try to fetch the score first so that if it is not valid, 
						// line is not valid as well
						try
			        	{
							chemScores.add( Integer.parseInt( tokens[1] ) );
							chemNames.add( tokens[0] );
			        	}
			        	catch( Exception e ){}
					}
				}
			}
			else if( lines[0].equalsIgnoreCase( "VOCABULARY" ) )
			{
				for( int j = 1; j < lines.length; j++ )
				{
					// split the words in the line
					String[] tokens = lines[j].split( " " );
					
					// make sure there is a name and a score
					if( tokens.length == 2 )
					{
						// try to fetch the score first so that if it is not valid, 
						// line is not valid as well
						try
			        	{
							vocabScores.add( Integer.parseInt( tokens[1] ) );
							vocabNames.add( tokens[0] );
			        	}
			        	catch( Exception e ){}
					}
				}
			}
		}
		
		// if there size of the stored stores is not equal to the amount of levels
		// in an episode, fill the remaining scores as default
		for( int i = bioNames.size(); i < MenuManager.LEVEL_COUNT; i++ )
		{
			bioNames.add( "MysteriousPerson" );
			bioScores.add( 0 );
		}
		
		for( int i = chemNames.size(); i < MenuManager.LEVEL_COUNT; i++ )
		{
			chemNames.add( "MysteriousPerson" );
			chemScores.add( 0 );
		}
		
		for( int i = vocabNames.size(); i < MenuManager.LEVEL_COUNT; i++ )
		{
			vocabNames.add( "MysteriousPerson" );
			vocabScores.add( 0 );
		}
	}
	// END OF OTHER METHODS
}