package org.example.projectunknown;

import org.example.projectunknown.menuscreens.Options;

import android.content.Context;
import android.media.MediaPlayer;

public class Music
{
	private static MediaPlayer mp = null;

	/** Stop old song and start new one */
	public static void play(Context context, int resource)
	{
		stop(context);
		// Start music only if not disabled in preferences
		if (Options.getMusic(context))
		{
			mp = MediaPlayer.create(context, resource);
			mp.setLooping(true);
			mp.start();
		}
	}

	/** Stop the music */
	public static void stop(Context context)
	{
		if (mp != null)
		{
			mp.stop();
			mp.release();
			mp = null;
		}
	}
}