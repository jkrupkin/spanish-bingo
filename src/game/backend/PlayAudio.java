package game.backend;
import java.io.IOException;

import javax.sound.sampled.*;
import shared.Word;
public class PlayAudio {
private static boolean isPlaying;
	public static void play(Word w) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
			System.out.println("Playing audio");
			w.resetAudioStream();
			Clip c = AudioSystem.getClip();
			if(w.getAudio() != null) {
			c.open(w.getAudio());
			LineListener l = new LineListener() {

				@Override
				public void update(LineEvent e) {
					if(e.getType() == LineEvent.Type.STOP) {
						isPlaying = false;
						System.out.println("Playback stopped");
						c.close();
		
						

					}
					if(e.getType() == LineEvent.Type.START) {
						isPlaying = true;
					}
					
				}
			};
			c.addLineListener(l);
			c.start();
			}

		}
	public static boolean isPlaying() {
		return isPlaying;
	}

	}


