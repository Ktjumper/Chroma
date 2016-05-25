package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kenneth on 8/30/2015.
 */
public class Level17 extends Level {
    public Level17 (SpriteBatch sb, ScreenGame gameScreen) { super(sb, gameScreen);}

    void createLevel()
    {
        sequence.add("r");
        sequence.add("b");
        sequence.add("r");

        Node r, g, b, rand, auto, start, exit;

        r = new Node("r", this);
        g = new Node("g", this);
        b = new Node("b", this);
        rand = new NodeRandom("0", this);
        start = new NodeStart("s", this);
        exit = new NodeExit("e", this);

        insertNode(start);
        insertNode(b, 4);

        nextRow();

        insertNode(r, 5);

        nextRow();

        insertNode(new NodeBlackout("black", this), 5);

        nextRow();

        insertNode(b, 5);

        nextRow();

        insertNode(r, 4);
        insertNode(exit);

        level = 17;

        star3 = 5;
        star2 = 8;
    }
}
