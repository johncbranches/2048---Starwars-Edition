package org.academiadecodigo.simplegraphics.game2048;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Main {

    public static void main(String[] args) {

        Game newGame = new Game();

        KeyboardHandler camelCase = new KeyBinds(newGame);

        Keyboard k = new Keyboard(camelCase);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        KeyboardEvent reset = new KeyboardEvent();
        reset.setKey(KeyboardEvent.KEY_R);
        reset.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent mute = new KeyboardEvent();
        mute.setKey(KeyboardEvent.KEY_M);
        mute.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent start = new KeyboardEvent();
        start.setKey(KeyboardEvent.KEY_S);
        start.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent exiting = new KeyboardEvent();
        exiting.setKey(KeyboardEvent.KEY_E);
        exiting.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        k.addEventListener(left);
        k.addEventListener(right);
        k.addEventListener(up);
        k.addEventListener(down);
        k.addEventListener(reset);
        k.addEventListener(start);
        k.addEventListener(exiting);
        k.addEventListener(mute);

        newGame.init();

    }
}
