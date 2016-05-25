package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kenneth on 8/30/2015.
 */
public class Level18 extends Level {
    public Level18 (SpriteBatch sb, ScreenGame gameScreen) { super(sb, gameScreen);}

    void createLevel()
    {
        sequence.add("b");
        sequence.add("r");
        sequence.add("g");

        Node r, g, b, rand, bout;

        r = new Node("r", this);
        g = new Node("g", this);
        b = new Node("b", this);

        bout = new NodeBlackout("blackout", this);
        rand = new NodeRandom("0", this);
        Node start = new NodeStart("s", this);
        Node exit = new NodeExit("e", this);

        insertNode(start);
        insertNode(bout);
        insertNode(b);
        insertNode(r);
        insertNode(g);

        nextRow();

        insertNode(bout, 2);
        insertNode(b);
        insertNode(r);
        insertNode(g);

        nextRow();

        insertNode(b, 3);
        insertNode(r);
        insertNode(g);

        nextRow();

        insertNode(r, 4);
        insertNode(g);

        nextRow();

        insertNode(g, 4);
        insertNode(exit);

        level = 18;
        star3 = 4;
        star2 = 6;
    }
}
