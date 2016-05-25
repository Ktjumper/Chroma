package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets;

import java.util.ArrayList;
import java.util.Random;

public class Level {
    SpriteBatch sb;
    public ArrayList<ArrayList<Node>> board;
    int rowI;
    public double timer;
    public ArrayList<String> sequence;
    public int seqI = 0;
    public int level;
    public int score;
    public int star3, star2;
    ScreenGame screenGame;

    Node currentNode, startNode;

    Texture selectedNode, rNode, gNode, bNode, faded, grayed, pause;
    AssetManager assets;
    int pauseX, pauseY, pauseSize;

    //special node stuff
    int blackedOut = 0;

    //animated current node
    int frame = 0;

    public Level(SpriteBatch sb, ScreenGame screenGame)
    {
        assets = new AssetManager();
        board = new ArrayList<ArrayList<Node>>();
        board.add(new ArrayList<Node>());   //first row
        sequence = new ArrayList<String>();

        selectedNode = Assets.manager.get("current.png", Texture.class);
        rNode = Assets.manager.get("red.png", Texture.class);
        gNode = Assets.manager.get("green.png", Texture.class);
        bNode = Assets.manager.get("blue.png", Texture.class);
        faded = Assets.manager.get("faded.png", Texture.class);
        grayed = Assets.manager.get("grayed.png", Texture.class);
        pause = Assets.manager.get("pausebutton.png", Texture.class);

        selectedNode.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        rNode.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        gNode.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bNode.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        faded.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        grayed.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        pause.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        
        this.sb = sb;
        this.screenGame = screenGame;

        createLevel();

        score = 0;
        Preferences data = Gdx.app.getPreferences("LevelData");
        data.putInteger("lastPlayed", level);
        data.flush();

        //search the board for the start node and set currentNode to it.
        for (int i = 0; i < board.size(); i++)
            for (int j = 0; j < board.get(i).size(); j++)
                if (board.get(i).get(j).isStart()) {
                    startNode = board.get(i).get(j);
                    Gdx.app.log("asdf", "found start node at: " + startNode.row + ", " + startNode.col);
                }
        currentNode = startNode;
    }

    void renderBG() {
        Texture bg = Assets.manager.get("soft blue bg.jpg", Texture.class);
        sb.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    void renderPause()
    {
        pauseSize = Gdx.graphics.getWidth()/8;
        sb.draw(pause, Gdx.graphics.getWidth() - pauseSize - 10, Gdx.graphics.getHeight() - pauseSize - 10, pauseSize, pauseSize);
        pauseX = Gdx.graphics.getWidth() - pauseSize - 10;
        pauseY = Gdx.graphics.getHeight() - pauseSize - 10;
    }

    void renderSequence() {
        int size = currentNode.size;
        double seqY = Gdx.graphics.getHeight() * 0.8;
        String curSeq = sequence.get(seqI);
        Texture renderT;

        int widthOffset = (Gdx.graphics.getWidth() - 3 * size) / 2;

        if (curSeq.equals("r"))
            renderT = rNode;
        else if (curSeq.equals("g"))
            renderT = gNode;
        else
            renderT = bNode;

        if (blackedOut < 1)
            sb.draw(renderT, widthOffset, (int) seqY, size, size, 0, 0, 200, 200, false, false);
        else
            sb.draw(grayed, widthOffset, (int) seqY, size, size, 0, 0, 200, 200, false, false);

        curSeq = sequence.get((seqI + 1) % sequence.size());

        if (curSeq.equals("r"))
            renderT = rNode;
        else if (curSeq.equals("g"))
            renderT = gNode;
        else
            renderT = bNode;

        if (blackedOut < 1)
        {
            sb.draw(renderT, size + widthOffset, (int)seqY, size, size, 0, 0, 200, 200, false, false);
            sb.draw(faded, size + widthOffset, (int)seqY, size, size, 0, 0, 200, 200, false, false);
        }
        else
            sb.draw(grayed, size + widthOffset, (int)seqY, size, size, 0, 0, 200, 200, false, false);

        curSeq = sequence.get((seqI+2)%sequence.size());

        if (curSeq.equals("r"))
            renderT = rNode;
        else if (curSeq.equals("g"))
            renderT = gNode;
        else
            renderT = bNode;

        if (blackedOut < 1)
        {
            sb.draw(renderT, size * 2 + widthOffset, (int) seqY, size, size, 0, 0, 200, 200, false, false);
            sb.draw(faded, size * 2 + widthOffset, (int) seqY, size, size, 0, 0, 200, 200, false, false);
        }
        else
            sb.draw(grayed, size * 2 + widthOffset, (int)seqY, size, size, 0, 0, 200, 200, false, false);
    }

    void renderNodes()
    {
        for (int i=0; i < board.size(); i++)
        {
            ArrayList<Node> curRow = board.get(i);
            for (int j = 0; j < curRow.size(); j++)
            {
                Node n = curRow.get(j);

                int shorter = Gdx.graphics.getWidth() > Gdx.graphics.getHeight()*0.7 ? (int)(Gdx.graphics.getHeight()*0.7) : Gdx.graphics.getWidth();
                n.size = shorter / board.get(0).size();
                n.renderedX = j * n.size;
                n.renderedY = i * n.size + (int)(Gdx.graphics.getHeight()*0.1);
                n.row = i;
                n.col = j;

                n.render(sb);

                //render at the center of the screen by additionally offsetting (board height - (node height * #of rows) ) / 2
                //do the same for width as well.
            }
        }

        if (frame < 7)
            sb.draw(selectedNode, currentNode.renderedX, currentNode.renderedY, currentNode.size, currentNode.size, 133*(frame%7), 0, 133, 134, false, false);
        else if (frame < 14)
            sb.draw(selectedNode, currentNode.renderedX, currentNode.renderedY, currentNode.size, currentNode.size, 133*(frame%7), 134, 133, 134, false, false);
        else if (frame < 21)
            sb.draw(selectedNode, currentNode.renderedX, currentNode.renderedY, currentNode.size, currentNode.size, 133*(frame%7), 134*2, 133, 134, false, false);
        else
            sb.draw(selectedNode, currentNode.renderedX, currentNode.renderedY, currentNode.size, currentNode.size, 133*(frame%7), 134*3, 133, 134, false, false);

        frame++;

        if (frame > 27)
            frame = 0;
    }

    //linear search q_q for which node got tapped
    Node touchNode(int screenX, int screenY)
    {
        //screen coordinates' origin is top left while rendering's origin is bottom left.
        screenY = Gdx.graphics.getHeight() - screenY;

        if (screenX > pauseX && screenX < pauseX + pauseSize && screenY > pauseY && screenY < pauseY + pauseSize) {
            Gdx.app.log("asdf", "pause button pressed");
            screenGame.pauseGame();
            return null;
        }

        int maxRows = board.size();
        int maxCols = board.get(0).size();  //change this as needed in the future for non-square boards

        if (screenX > board.get(0).get(maxCols - 1).renderedX + board.get(0).get(0).size || screenX < board.get(0).get(0).renderedX)
            return null;

        if (screenY > board.get(maxRows-1).get(0).renderedY + board.get(0).get(0).size || screenY < board.get(0).get(0).renderedY)
            return null;

        int row = 0;
        Node searchNode = board.get(row).get(0);
        while (screenY > searchNode.renderedY + searchNode.size) {
            row++;
            searchNode = board.get(row).get(0);
        }

        int col = 0;
        searchNode = board.get(row).get(col);
        while (screenX > searchNode.renderedX + searchNode.size) {
            col++;
            searchNode = board.get(row).get(col);
        }

        //found the tapped node!
        /* play tone here
        switch(musicToneCounter)
        {
            case 0:
                doTune.play();
                musicToneCounter++;
                break;
            case 1:
                reTune.play();
                musicToneCounter++;
                break;
            default:
                miTune.play();
                musicToneCounter = 0;
                break;
        }
        */

        searchNode.tapped();

        return searchNode;
    }

    void createLevel()
    {}

    void insertNode(Node n)
    {
        board.get(rowI).add(n.clone());
    }

    void insertNode(Node n, int count)
    {
        for(int i = 0; i < count; i++)
        {
            board.get(rowI).add(n.clone());
        }
    }

    void nextRow()
    {
        rowI += 1;
        board.add(new ArrayList<Node> ());
    }

    void resetBoard() {
        rowI = 0;
        board.clear();
        board.add(new ArrayList<Node>());
        sequence.clear();

        createLevel();
    }
}
