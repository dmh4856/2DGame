package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound 
{
	//Opens audio files.
	Clip clip;
	
	//Stores file path for sound files.
	URL soundURL[] = new URL[30];
	
	public Sound() 
	{
		//BlueBoyAdventure.wav is a placeholder theme song. Replace it with your own eventually.
		soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[1] = getClass().getResource("/sound/itemget.wav");
		soundURL[2] = getClass().getResource("/sound/powerup.wav");
		soundURL[3] = getClass().getResource("/sound/unlock.wav");
		soundURL[4] = getClass().getResource("/sound/fanfare.wav");
	}
	public void setFile(int i) 
	{
		try 
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}
		
		catch(Exception e)
		
		{
			
		}
	}
	public void play() 
	{
		clip.start();
	}
	public void loop()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() 
	{
		clip.stop();
	}
}
