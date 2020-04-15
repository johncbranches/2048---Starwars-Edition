package org.academiadecodigo.simplegraphics.test;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Paparasciis implements KeyboardHandler {

    private Rectangle sprite;

    public Paparasciis() {
        sprite = new Rectangle(40,25,50,50);
        sprite.draw();
    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        sprite.translate(10,0);
    }

    @Override
    public void keyReleased(KeyboardEvent e) {

    }
}
