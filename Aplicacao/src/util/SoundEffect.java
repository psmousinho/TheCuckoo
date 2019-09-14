package util;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum SoundEffect {
    
    CUCKOO("/resources/cuckoo.wav");
    
    private final String fileName;
    private final URL fileUrl;
    private Clip clip;
    private AudioInputStream audioStream;
    
    SoundEffect(String fileName) {
        this.fileName = fileName;
        fileUrl = getClass().getResource(fileName);
        try {
            audioStream = AudioSystem.getAudioInputStream(fileUrl);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void play() {
        if(clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.start();
    }
}