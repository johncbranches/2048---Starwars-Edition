package org.academiadecodigo.simplegraphics.game2048;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyBinds implements KeyboardHandler {

    private Game newGame;
    private boolean mute = false;

    public KeyBinds(Game newGame) {
        this.newGame = newGame;
    }

    @Override
    public void keyReleased(KeyboardEvent e) {

    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        if (newGame.getStarted()) {

            if (KeyboardEvent.KEY_LEFT == e.getKey()) {
                newGame.gameOver();

                if (!newGame.getGameover()) {
                    newGame.addEqualsMoveLeft();
                }
            }

            if (KeyboardEvent.KEY_M == e.getKey()) {
                if (!mute) {
                    newGame.getSound().endBackgroundSoundForSure();
                    mute = !mute;
                } else {
                    newGame.getSound().startBgMusic();
                    mute = !mute;
                }

            }

            if (KeyboardEvent.KEY_RIGHT == e.getKey()) {
                newGame.gameOver();
                if (!newGame.getGameover()) {
                    newGame.addEqualsMoveRight();
                }
            }
            if (KeyboardEvent.KEY_UP == e.getKey()) {
                newGame.gameOver();
                if (!newGame.getGameover()) {
                    newGame.addEqualsMoveUp();
                }
            }
            if (KeyboardEvent.KEY_DOWN == e.getKey()) {
                newGame.gameOver();
                if (!newGame.getGameover()) {
                    newGame.addEqualsMoveDown();
                }
            }

            if (newGame.getGameover()) {
                if (KeyboardEvent.KEY_R == e.getKey()) {

                        newGame.setGameOver();
                        newGame.setGridZero();
                        newGame.getSound().startResetSound();
                        newGame.reset();

                }
            }
        }
        if (KeyboardEvent.KEY_E == e.getKey()) {
            System.exit(1);
        }

        if (KeyboardEvent.KEY_S == e.getKey()) {
            newGame.setStarted();
            newGame.getSound().startResetSound();
            if (newGame.getInitended()) {
                newGame.start();
            }
        }
    }
}
