package org.academiadecodigo.simplegraphics.game2048;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class EndAnimations {

    private Sound sound;

    public EndAnimations(Sound sound) {
        this.sound = sound;


    }

    public void start(){
        sound.endBackgroundSoundForSure();

        sound.startGameOvermusic();

        Picture overPic = new Picture(10, 10, ("simple-graphics/resources/6177.jpg"));
        overPic.draw();

/*
        for (int i = 6001; i < 6178; i++) {

            overPic.load (("simple-graphics/resources/" + i + ".jpg"));

            overPic.draw();

        }

*/
    }

}
