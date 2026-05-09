package JernexMachineSystem.JernexSystemComponents;
import java.net.URL;
import javax.sound.sampled.*;

public class SoundManagement {
    public static void PlaySoundClipInThisJar(URL soundURL) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close(); // FECHA AQUI!
                }
            });

            clip.start();
        } catch (Exception e) {
            System.err.println("Deu ruim no som: " + e.getMessage());
        }
    }
}
