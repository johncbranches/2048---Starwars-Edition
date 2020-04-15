package org.academiadecodigo.simplegraphics.game2048;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Animations {

    private Picture introPic;
    private boolean started = false;

    public void startAnimations() {
        introPic = new Picture(10, 10, ("simple-graphics/resources/5000.jpg"));
        introPic.draw();


        for (int i = 5001; i < 5358; i++) {
            introPic.load("simple-graphics/resources/" + i + ".jpg");
            introPic.draw();
            if (started) {
                introPic.load ("simple-graphics/resources/5357.jpg");
                introPic.draw();
                break;
            }
        }
    }

    public boolean isStarted(){
        return started;
    }

    public void setStarted(){
        started = true;
    }
}
