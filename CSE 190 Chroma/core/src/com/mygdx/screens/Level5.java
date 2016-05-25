package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kenneth on 8/30/2015.
 */
public class Level5 extends Level {
    public Level5 (SpriteBatch sb, ScreenGame gameScreen) { super(sb, gameScreen);}

    void createLevel()
    {
        sequence.add("b");
        sequence.add("r");
        sequence.add("b");
        sequence.add("g");
        sequence.add("r");

        Node r, g, b, bout, start, rand, blank;

        r = new Node("r", this);
        g = new Node("g", this);
        b = new Node("b", this);
        bout = new NodeBlackout("blackout", this);
        start = new NodeStart("s", this);
        rand = new NodeRandom("0", this);
        blank = new NodeBlank("blank", this);

        insertNode(blank,5);

        nextRow();
        insertNode(start);
        insertNode(g);
        insertNode(rand);
        insertNode(bout);
        insertNode(rand);

        nextRow();
        insertNode(rand, 5);

        nextRow();
        insertNode(b);
        insertNode(r);
        insertNode(blank);
        insertNode(rand);

        nextRow();
        insertNode(rand, 4);
        insertNode(new NodeExit("e", this));

        level = 5;

    }
}
