package org.academiadecodigo.simplegraphics.game2048;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Background {

    private Game game;
    private Picture gameBackground;
    private Rectangle mainSquare;
    private Rectangle[][] squares = new Rectangle[4][4];
    private Picture[][] pics = new Picture[4][4];
    private boolean firstShowSquares = true;

    public Background(Game game){
        this.game = game;
    }

    public void createSuperSquare() {

        gameBackground = new Picture(10, 10, "simple-graphics/resources/starWarsBluePrint_MUTE_EXIT.png");
        gameBackground.draw();

    }

    public void createMainSquare() {
        mainSquare = new Rectangle(110, 160, 500, 500);
        mainSquare.setColor(Color.GRAY);
        mainSquare.fill();

    }

    public void createSquares() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squares[i][j] = new Rectangle((110 + (20 * (j + 1)) + (100 * j)), (160 + (20 * (i + 1)) + (100 * i)), 100, 100);
                squares[i][j].setColor(Color.LIGHT_GRAY);
                squares[i][j].fill();
            }
        }
    }

    public void showSquares() {

        if (firstShowSquares) {

            firstShowSquares = false;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    pics[i][j] = new Picture((110 + (20 * (j + 1)) + (100 * j)), (160 + (20 * (i + 1)) + (100 * i)), ("simple-graphics/resources/" + game.getGrid()[i][j] + ".png"));
                    pics[i][j].draw();
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                pics[i][j].load("simple-graphics/resources/" + game.getGrid()[i][j] + ".png");
                pics[i][j].draw();
            }
        }
    }

    public void setFirstShowSquares(){
        firstShowSquares =true;
    }
}
