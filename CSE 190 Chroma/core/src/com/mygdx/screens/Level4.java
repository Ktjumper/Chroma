package com.mygdx.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level4 extends Level {
    public Level4(SpriteBatch sb, ScreenGame gameScreen)
    {
        super(sb, gameScreen);
    }

    void createLevel()
    {
        sequence.add("g");
        sequence.add("r");
        sequence.add("b");

        Node r, g, b, rand, auto, blank;

        r = new Node("r", this);
        g = new Node("g", this);
        b = new Node("b", this);
        //auto = new NodeAuto("star", this);
        rand = new NodeRandom("0", this);
        Node start = new NodeStart("s", this);
        Node bout = new NodeBlackout("blackout", this);
        NodePortal pblue = new NodePortal("pBlue", this, "blue");
        blank = new NodeBlank("blank", this);

        insertNode(start);
        insertNode(g);
        insertNode(r);
        insertNode(b);
        insertNode(g);

        nextRow();
        insertNode(blank, 4);
        insertNode(r);

        nextRow();
        insertNode(g);
        insertNode(b);
        insertNode(r);
        insertNode(g);
        insertNode(b);

        nextRow();
        insertNode(r);
        insertNode(blank, 4);

        nextRow();
        insertNode(b);
        insertNode(g);
        insertNode(r);
        insertNode(b);
        insertNode(new NodeExit("e", this));

        level = 4;

    }
}
