package org.academiadecodigo.simplegraphics.test;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Main {
    public static void main(String[] args) {

        Rectangle canvas = new Rectangle(0,0,400,400);
        //canvas.draw();                //Square canvas with border
        canvas.fill();                  //Draw canvas and fills it
        canvas.setColor(Color.YELLOW);  //Paints the canvas

        Text code = new Text(250, 110, "Simple Code Snippet.");
        code.draw();
        code.setColor(Color.RED);
        code.grow(30,30);

        KeyboardHandler paparascii = new Paparasciis();
        Keyboard k = new Keyboard(paparascii);
        KeyboardEvent eventRight = new KeyboardEvent();
        eventRight.setKey(KeyboardEvent.KEY_D);
        eventRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        k.addEventListener(eventRight);

    }
}
