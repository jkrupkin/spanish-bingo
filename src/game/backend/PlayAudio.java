package game.backend;
import java.io.IOException;

import javax.sound.sampled.*;
import shared.Word;
public class PlayAudio {

	public static void play(Word w) throws LineUnavailableException, IOException {
			System.out.println("Playing audio");
			Clip c = AudioSystem.getClip();
			c.open(w.getAudio());
			LineListener l = new LineListener() {

				@Override
				public void update(LineEvent e) {
					if(e.getType() == LineEvent.Type.STOP) {
						System.out.println("Playback stopped");
						c.close();
						try {
							w.resetAudioStream();
						} catch (IOException |  UnsupportedAudioFileException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						

					}
					
				}
				
			};
			c.addLineListener(l);
			c.start();
}
}

