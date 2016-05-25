package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kenneth on 8/30/2015.
 */
public class Level16 extends Level {
    public Level16 (SpriteBatch sb, ScreenGame gameScreen) { super(sb, gameScreen);}

    void createLevel()
    {
            sequence.add("b");
            sequence.add("r");
            sequence.add("g");
            sequence.add("r");
            sequence.add("g");

            Node r, g, b, rand, auto;

            r = new Node("r", this);
            g = new Node("g", this);
            b = new Node("b", this);
            //auto = new NodeAuto("star", this);
            rand = new NodeRandom("0", this);
            Node start = new NodeStart("s", this);

            insertNode(start);
            insertNode(rand, 4);

            nextRow();
            insertNode(rand, 5);

            nextRow();
            insertNode(rand, 5);

            nextRow();
            insertNode(rand, 5);


            nextRow();
            insertNode(rand, 2);
            insertNode(new NodeExit("e", this));
            insertNode(rand, 2);

            level = 16;
            star3 = 7;
            star2 = 11;
    }
}
