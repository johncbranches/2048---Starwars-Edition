package org.academiadecodigo.simplegraphics.game2048;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Sound {

    Clip audioClipBackground;
    Clip audioClipGameOver;
    Clip audioClipIntro;
    Clip audioClipStartReset;

    public void endIntroSoundForSure() {
        try {
            audioClipIntro.close();
        } catch (Exception e) {

        }
    }

    public void endBackgroundSoundForSure() {
        try {
            audioClipBackground.close();
        } catch (Exception e) {

        }
    }

    public void endSoundForSure(){
        try {
            audioClipGameOver.close();
        } catch (Exception e) {

        }
    }

    public void startIntroMusic() {
        try {
            InputStream audiosrc = getClass().getResourceAsStream("/simple-graphics/resources/introStarWars20482.wav");
            InputStream bufferedIn = new BufferedInputStream(audiosrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClipIntro = (Clip) AudioSystem.getLine(info);
            audioClipIntro.open(audioStream);
            audioClipIntro.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void startBgMusic() {

        try {
            InputStream audiosrc = getClass().getResourceAsStream("/simple-graphics/resources/theForceSuite.wav");
            InputStream bufferedIn = new BufferedInputStream(audiosrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClipBackground = (Clip) AudioSystem.getLine(info);
            audioClipBackground.open(audioStream);
            audioClipBackground.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void startResetSound() {
        try {
            InputStream audiosrc = getClass().getResourceAsStream("/simple-graphics/resources/saberSound.wav");
            InputStream bufferedIn = new BufferedInputStream(audiosrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClipStartReset = (Clip) AudioSystem.getLine(info);
            audioClipStartReset.open(audioStream);
            audioClipStartReset.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void startGameOvermusic() {
        try {
            InputStream audiosrc = getClass().getResourceAsStream("/simple-graphics/resources/gameover2.wav");
            InputStream bufferedIn = new BufferedInputStream(audiosrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClipGameOver = (Clip) AudioSystem.getLine(info);
            audioClipGameOver.open(audioStream);
            audioClipGameOver.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
