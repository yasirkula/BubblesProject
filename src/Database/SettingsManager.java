/**
 * SettingsManager - A database that fetches stored settings from local disk
 * 					 and writes them back when necessary
 * 
 * @author CS319 - Section 2 - Group 9
 */

package Database;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import GameAssets.EpisodeType;
import GameManagement.MenuManager;

public class SettingsManager
{
	// VARIABLES
	private float soundLevel;
	private Color backgroundColor;
	private int lockedLevelNumberForBio;
	private int lockedLevelNumberForChem;
	private int lockedLevelNumberForVocab;
	// END OF VARIABLES

	// CONSTRUCTORS
	public SettingsManager()
	{
		// load default values and then try to fetch values from database
		soundLevel = 1f;
		backgroundColor = Color.white;
		lockedLevelNumberForBio = MenuManager.LEVEL_COUNT - 1;
		lockedLevelNumberForChem = MenuManager.LEVEL_COUNT - 1;
		lockedLevelNumberForVocab = MenuManager.LEVEL_COUNT - 1;

		loadSettings();
	}
	// END OF CONSTRUCTORS

	// MUTATOR - ACCESSOR METHODS
	public float getSoundLevel()
	{
		return soundLevel;
	}

	public void setSoundLevel( float s )
	{
		soundLevel = s;
	}

	public Color getBackgroundColor()
	{
		return backgroundColor;
	}

	public void setBackgroundColor( Color c )
	{
		backgroundColor = c;
	}
	
	public int getLockedLevelNumber( EpisodeType episode )
	{
		switch( episode )
		{
			case BIOLOGY: return lockedLevelNumberForBio;
			case CHEMISTRY: return lockedLevelNumberForChem;
			case VOCABULARY: return lockedLevelNumberForVocab;
			default: return 0;
		}
	}
	
	public void setLockedLevelNumber( EpisodeType episode, int value )
	{
		switch( episode )
		{
			case BIOLOGY: lockedLevelNumberForBio = value; break;
			case CHEMISTRY: lockedLevelNumberForChem = value; break;
			case VOCABULARY: lockedLevelNumberForVocab = value; break;
		}
	}
	// END OF MUTATOR - ACCESSOR METHODS

	// OTHER METHODS
	public void loadSettings()
	{
		// Inspired from http://docs.oracle.com/javase/tutorial/essential/io/file.html
		// and
		// http://stackoverflow.com/questions/14918188/reading
		// -text-file-with-utf-8-encoding-using-java
		// answered by: Shobit Sharma
		BufferedReader buffer = null;

		try
		{
			buffer = new BufferedReader( new InputStreamReader( 
					new FileInputStream( "settings.dat" ), "UTF-8" ) );

			String line = null;
			while( ( line = buffer.readLine() ) != null ) 
			{
				String[] tokens = line.split( " " );

				if( tokens[0].equals( "Sound" ) )
				{
					// try to fetch sound level setting
					try
					{
						soundLevel = Integer.parseInt( tokens[1] ) / 100f;
					}
					catch( Exception e ){}
				}
				else if( tokens[0].equals( "Bg" ) )
				{
					// try to fetch background color setting
					try
					{
						for( int i = 0; i < tokens.length; i++ )
						{
							int r = Integer.parseInt( tokens[1] );
							int g = Integer.parseInt( tokens[2] );
							int b = Integer.parseInt( tokens[3] );

							backgroundColor = new Color( r, g, b );
						}
					}
					catch( Exception e ){}
				}
				else if( tokens[0].equals( "LockedLevel" ) )
				{
					// try to fetch locked levels
					try
					{
						lockedLevelNumberForBio = Integer.parseInt( tokens[1] );
						lockedLevelNumberForChem = Integer.parseInt( tokens[2] );
						lockedLevelNumberForVocab = Integer.parseInt( tokens[3] );
					}
					catch( Exception e ){}
				}
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

	public void writeSettings()
	{
		// Inspired from http://stackoverflow.com/questions/2885173/java
		// 				 -how-to-create-and-write-to-a-file
		// answered by: Bozho

		BufferedWriter buffer = null;

		try 
		{
			buffer = new BufferedWriter( new OutputStreamWriter(
					new FileOutputStream( "settings.dat" ), "UTF-8" ) );
			buffer.write( "Sound " + (int)( soundLevel * 100 ) );
			buffer.newLine();
			buffer.write( "Bg " +  backgroundColor.getRed() + " " +
					backgroundColor.getGreen() + " " + backgroundColor.getBlue() );
			buffer.newLine();
			buffer.write( "LockedLevel " +  lockedLevelNumberForBio + " " +
					lockedLevelNumberForChem + " " + lockedLevelNumberForVocab );

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
	// END OF OTHER METHODS
}
