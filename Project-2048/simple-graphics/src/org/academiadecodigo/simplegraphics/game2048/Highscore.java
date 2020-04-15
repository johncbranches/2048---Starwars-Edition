package org.academiadecodigo.simplegraphics.game2048;

import java.io.*;

public class Highscore {



    public int readHS() throws IOException {

        FileReader reader = new FileReader("resources/highscore.txt");
        BufferedReader bReader = new BufferedReader(reader);

        String line;
        line = bReader.readLine();

        int savedHS = Integer.parseInt(line);

        bReader.close();

        return savedHS;
    }

    public void writeHS(int score) throws IOException {

        FileWriter writer = new FileWriter("resources/highscore.txt");
        BufferedWriter bWriter = new BufferedWriter(writer);

        bWriter.write(score + "");

        bWriter.flush();
        bWriter.close();



    }


}
