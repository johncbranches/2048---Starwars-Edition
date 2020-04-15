package org.academiadecodigo.simplegraphics.game2048;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;


public class Game {

    //Properties
    private int[][] grid = new int[4][4];
    private int[][] grid2 = new int[4][4];

    private Sound sound = new Sound();
    private Background background = new Background(this);
    private Animations animations = new Animations();
    private EndAnimations endAnimations = new EndAnimations(sound);

    private Text displayScore = new Text(550, 715, "Score");
    private Text displayScoreGameOver;
    private Text displayHighScore = new Text(550, 760, "highscore");

    private Highscore hS = new Highscore();

    private int score = 0;
    private int highscore = 0;
    private boolean isGrown = false;
    private boolean gameOver = true;
    private boolean check = false;
    private boolean finished = false;
    private boolean initEnded = false;
    private boolean doneStart = false;

    public int mvUp = 0, mvDown = 0, mvLeft = 0, mvRight = 0;

    /*Animations//
     * private Picture introPic;
     * private boolean started = false;
     */

    /*Sound//
    * Clip audioClipBackground;
    * Clip audioClipGameOver;
    * Clip audioClipIntro;
    * Clip audioClipStartReset;
    */

    /*Background//
     * private Picture gameBackground;
     * private Rectangle mainSquare;
     * private Rectangle[][] squares = new Rectangle[4][4];
     * private Picture[][] pics = new Picture[4][4];
     * private boolean firstShowSquares = true;
     */

    //Methods

    public void init() {

        sound.startIntroMusic();

        animations.startAnimations();

        initEnded = true;
        if (animations.isStarted()) {
            start();
        }
    }

    public void fillGridTest() {
        grid[0][0] = 2048;
        grid[0][1] = 4;
        grid[0][2] = 64;
        grid[0][3] = 2;
        grid[1][0] = 16;
        grid[1][1] = 128;
        grid[1][2] = 32;
        grid[1][3] = 0;
        grid[2][0] = 32;
        grid[2][1] = 8;
        grid[2][2] = 64;
        grid[2][3] = 0;
        grid[3][0] = 2048;
        grid[3][1] = 4;
        grid[3][2] = 32;
        grid[3][3] = 0;

        score = 12372;
        highscore = 11818;

    }

    public void start() {

        if (!doneStart) {
            doneStart = !doneStart;

          //  fillGridTest();
            /*try {
                audioClipIntro.close();
            } catch (Exception e) {

            }*/
            sound.endIntroSoundForSure();
            background.createSuperSquare();
            background.createMainSquare();
            background.createSquares();
            score = 0;
          try {
              highscore = hS.readHS();
          } catch (Exception ex) {
          }
            showScores();

            randomGenerator();
            background.showSquares();
            sound.startBgMusic();
        }
    }

    public void showScores() {


        try {
            displayScore.delete();
            displayHighScore.delete();
        } catch (Exception e) {

        }
        displayScore.setColor(Color.BLACK);
        displayScore.draw();
        displayHighScore.setColor(Color.BLACK);
        displayHighScore.draw();

        if (isGrown == false) {
            displayScore.grow(40, 20);
            displayHighScore.grow(40, 20);
        }
        isGrown = true;

        displayScore.setText(String.format("%05d", score));
        displayHighScore.setText(String.format("%05d", highscore));
    }

    public void addEqualsMoveUp() {

        copyArray();
        moveUp();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {
                if (grid[i][j] == grid[i + 1][j]) {
                    grid[i][j] = grid[i][j] + grid[i][j];
                    if (!check) {
                        score += (grid[i][j]);
                        displayScore.setText(String.format("%05d", score));
                    }
                    grid[i + 1][j] = 0;

                }

            }
        }
        moveUp();
        if (equalsArray()) {
            mvUp = 1;
        }
        if (!equalsArray()) {
            gameOver = false;
            if (!check) {
                randomGenerator();
                background.showSquares();
                //gameover not
                mvDown = 0;
                mvUp = 0;
                mvLeft = 0;
                mvRight = 0;
            } else {
                copyArrayReverse();
            }
        }
    }

    public void addEqualsMoveLeft() {

        copyArray();

        moveLeft();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == grid[i][j + 1]) {
                    grid[i][j] = grid[i][j] + grid[i][j];
                    if (!check) {
                        score += (grid[i][j]);
                        displayScore.setText(String.format("%05d", score));
                    }
                    grid[i][j + 1] = 0;
                }
            }
        }
        moveLeft();
        if (equalsArray()) {
            mvLeft = 1;
        }
        if (!equalsArray()) {
            gameOver = false;
            if (!check) {
                randomGenerator();
                background.showSquares();
                //gameover not
                mvDown = 0;
                mvUp = 0;
                mvLeft = 0;
                mvRight = 0;
            } else {
                copyArrayReverse();
            }
        }
    }

    public void addEqualsMoveDown() {

        copyArray();

        moveDown();
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i > 0; i--) {
                if (grid[i][j] == grid[i - 1][j]) {
                    grid[i][j] = grid[i][j] + grid[i][j];
                    if (!check) {
                        score += (grid[i][j]);
                        displayScore.setText(String.format("%05d", score));
                    }
                    grid[i - 1][j] = 0;
                }
            }
        }
        moveDown();
        if (equalsArray()) {
            mvDown = 1;
        }
        if (!equalsArray()) {
            gameOver = false;
            if (!check) {
                randomGenerator();
                background.showSquares();
                //gameover not
                mvDown = 0;
                mvUp = 0;
                mvLeft = 0;
                mvRight = 0;
            } else {
                copyArrayReverse();
            }
        }
    }

    public void addEqualsMoveRight() {

        copyArray();

        moveRight();
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                if (grid[i][j] == grid[i][j - 1]) {
                    grid[i][j] = grid[i][j] + grid[i][j];
                    if (!check) {
                        score += (grid[i][j]);
                        displayScore.setText(String.format("%05d", score));
                    }
                    grid[i][j - 1] = 0;
                }
            }
        }
        moveRight();
        if (equalsArray()) {
            mvRight = 1;
        }
        if (!equalsArray()) {
            gameOver = false;
            if (!check) {
                randomGenerator();
                background.showSquares();
                //gameover not
                mvDown = 0;
                mvUp = 0;
                mvLeft = 0;
                mvRight = 0;
            } else {
                copyArrayReverse();
            }
        }
    }

    public void moveUp() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    if (grid[k][j] == 0) {
                        grid[k][j] = grid[k + 1][j];
                        grid[k + 1][j] = 0;
                    }
                }
            }
            background.showSquares();
        }
    }

    public void moveDown() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 3; k > 0; k--) {
                    if (grid[k][j] == 0) {
                        grid[k][j] = grid[k - 1][j];
                        grid[k - 1][j] = 0;
                    }
                }
            }
            background.showSquares();
        }
    }

    public void moveRight() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 3; k > 0; k--) {
                    if (grid[j][k] == 0) {
                        grid[j][k] = grid[j][k - 1];
                        grid[j][k - 1] = 0;
                    }
                }
            }
            background.showSquares();
        }
    }

    public void moveLeft() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    if (grid[j][k] == 0) {
                        grid[j][k] = grid[j][k + 1];
                        grid[j][k + 1] = 0;
                    }
                }
            }
            background.showSquares();
        }
    }

    public void randomGenerator() {
        int randomNumber1 = 2;
        int randomNumber2 = 4;
        int random = 0;


        int randomGenerator = (int) (Math.random() * 3);

        switch (randomGenerator) {
            case 0:
                random = randomNumber1;
                break;
            case 1:
                random = randomNumber1;
                break;
            case 2:
                random = randomNumber2;
                break;
        }
        //Acima escolher o número que aparece
        int emptySquare = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == 0) {
                    emptySquare++;
                }
            }
        }
        int newSlot = (int) (Math.random() * emptySquare) + 1;

        int randomSquare = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == 0) {
                    randomSquare++;
                    if (randomSquare == newSlot) {
                        grid[i][j] = random;
                    }
                }
            }
        }
    }

    public void gameOver() {


        gameOver = false;
        if (noZero() && noPairs()) {
            gameOver = true;
        }
        if (gameOver) {
            if (score > highscore) {
                highscore = score;
                try {
                    hS.writeHS(score);
                } catch (Exception ex) {
                }
            }
            endAnimations.start(); //Adicionado na nova versão para substituir a linha seguinte;
            //endAnimations();
            displayFinalScore();
            background.setFirstShowSquares();
        }
    }

    public boolean noZero() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }

        }
        return true;
    }

    public boolean noPairs() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == grid[i][j + 1]) {
                    return false;
                }

            }

        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[j][i] == grid[j + 1][i]) {
                    return false;
                }

            }

        }
        return true;

    }

    public void setGridZero() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = 0;
            }

        }
    }

    public void reset() {
        doneStart = !doneStart;
        /*try {
            audioClipGameOver.close();
        } catch (Exception e) {

        }
*/
        sound.endSoundForSure();
        start();
    }

    public void copyArray() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid2[i][j] = grid[i][j];

            }

        }
    }

    public void copyArrayReverse() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = grid2[i][j];

            }

        }
    }

    public boolean equalsArray() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] != grid2[i][j]) {
                    return false;
                }

            }

        }
        return true;
    }

    public void setStarted() {
        animations.setStarted();
    }

    public boolean getStarted() {
        return animations.isStarted();
    }

    public boolean getInitended() {
        return initEnded;
    }

    public boolean getGameover() {
        return gameOver;
    }

    public void setGameOver() {
        gameOver = !gameOver;
    }

    public void displayFinalScore() {
        displayScoreGameOver = new Text(400, 715, score + "");
        displayScoreGameOver.setText(String.format("%05d", score));
        displayScoreGameOver.setColor(Color.WHITE);
        displayScoreGameOver.draw();
        displayScoreGameOver.grow(40, 20);
    }

    public int[][] getGrid(){
        return grid;
    }

    public Sound getSound(){
        return sound;
    }

    /*public void createSuperSquare() {

        gameBackground = new Picture(10, 10, "simple-graphics/resources/starWarsBluePrint_MUTE_EXIT.png");
        gameBackground.draw();

    }*/

    /*public void createMainSquare() {
        mainSquare = new Rectangle(110, 160, 500, 500);
        mainSquare.setColor(Color.GRAY);
        mainSquare.fill();

    }*/

    /*public void createSquares() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squares[i][j] = new Rectangle((110 + (20 * (j + 1)) + (100 * j)), (160 + (20 * (i + 1)) + (100 * i)), 100, 100);
                squares[i][j].setColor(Color.LIGHT_GRAY);
                squares[i][j].fill();
            }
        }
    }*/

   /* public void showSquares() {
        if (firstShowSquares) {

            firstShowSquares = false;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    pics[i][j] = new Picture((110 + (20 * (j + 1)) + (100 * j)), (160 + (20 * (i + 1)) + (100 * i)), ("simple-graphics/resources/" + grid[i][j] + ".png"));
                    pics[i][j].draw();
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                pics[i][j].load("simple-graphics/resources/" + grid[i][j] + ".png");
                pics[i][j].draw();
            }
        }
    } */

   /*public void startAnimations() {
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
    }*/

    /*public void endAnimations() {

        muteSound();

        startGameOvermusic();

        Picture overPic = new Picture(10, 10, ("simple-graphics/resources/6177.jpg"));
        overPic.draw();

/*
        for (int i = 6001; i < 6178; i++) {

            overPic.load (("simple-graphics/resources/" + i + ".jpg"));

            overPic.draw();

        }
        displayFinalScore();

    }*/

    /*public void startResetSound() {
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
    }*/

    /*public void muteSound() {
        try {
            audioClipBackground.close();
        } catch (Exception e) {

        }
    }*/

   /* public void startBgMusic() {
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
    } */

    /*public void startGameOvermusic() {
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
    }*/

    /*public void startIntroMusic() {
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
    }*/


}
