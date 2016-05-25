package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level19 extends Level {
    public Level19 (SpriteBatch sb, ScreenGame gameScreen) { super(sb, gameScreen);}

    void createLevel()
    {
        sequence.add("g");
        sequence.add("r");
        sequence.add("b");

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
        insertNode(rand, 4);

        nextRow();

        insertNode(bout, 5);

        nextRow();

        insertNode(g, 5);

        nextRow();

        insertNode(bout, 5);

        nextRow();

        insertNode(rand, 2);
        insertNode(exit);
        insertNode(rand, 2);

        level = 19;
    }
}
