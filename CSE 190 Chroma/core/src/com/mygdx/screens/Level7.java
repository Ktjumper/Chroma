package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kenneth on 8/31/2015.
 */
public class Level7 extends Level {
    public Level7 (SpriteBatch sb, ScreenGame gameScreen) { super(sb, gameScreen);}

    void createLevel() {
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

        insertNode(start);
        insertNode(blank, 4);

        nextRow();
        insertNode(blank);
        insertNode(b);
        insertNode(g);
        insertNode(blank, 2);

        nextRow();
        insertNode(blank);
        insertNode(r);
        insertNode(bout);
        insertNode(r);
        insertNode(blank);

        nextRow();
        insertNode(blank, 2);
        insertNode(g);
        insertNode(r);
        insertNode(b);


        nextRow();
        insertNode(blank, 4);
        insertNode(new NodeExit("e", this));

        level = 7;
    }
}
