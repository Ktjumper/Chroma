package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level20 extends Level {
    public Level20 (SpriteBatch sb, ScreenGame gameScreen) { super(sb, gameScreen);}

    void createLevel()
    {
        sequence.add("b");
        sequence.add("r");
        sequence.add("g");
        sequence.add("r");
        sequence.add("g");

        Node start, exit, r, g, b, rand, blank, bout;

        r = new Node("r", this);
        g = new Node("g", this);
        b = new Node("b", this);

        rand = new NodeRandom("0", this);
        start = new NodeStart("s", this);
        exit = new NodeExit("e", this);
        blank = new NodeBlank("blank", this);
        bout = new NodeBlackout("black", this);

        insertNode(start);
        insertNode(rand, 6);

        nextRow();
        insertNode(b);
        insertNode(rand, 6);

        nextRow();
        insertNode(r);
        insertNode(rand, 2);
        insertNode(bout);
        insertNode(rand, 3);

        nextRow();
        insertNode(bout, 3);
        insertNode(r);
        insertNode(bout, 3);

        nextRow();
        insertNode(g);
        insertNode(rand, 2);
        insertNode(bout);
        insertNode(rand, 3);

        nextRow();
        insertNode(rand);
        insertNode(r);
        insertNode(rand, 5);

        nextRow();
        insertNode(rand, 2);
        insertNode(g);
        insertNode(new NodeExit("e", this));
        insertNode(rand, 3);

        level = 20;
    }
}
