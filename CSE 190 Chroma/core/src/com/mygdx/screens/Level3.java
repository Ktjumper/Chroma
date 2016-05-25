package com.mygdx.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level3 extends Level {
    public Level3(SpriteBatch sb, ScreenGame gameScreen)
    {
        super(sb, gameScreen);
    }

    void createLevel()
    {
                sequence.add("b");
        sequence.add("r");
        sequence.add("g");
        sequence.add("r");
        sequence.add("g");

        Node r, g, b, rand, auto, blank;

        r = new Node("r", this);
        g = new Node("g", this);
        b = new Node("b", this);
        //auto = new NodeAuto("star", this);
        rand = new NodeRandom("0", this);
        Node start = new NodeStart("s", this);
        blank = new NodeBlank("blank", this);

        insertNode(blank, 5);
        nextRow();
        insertNode(blank, 5);
        nextRow();

        insertNode(start);
        insertNode(b);
        insertNode(r);
        insertNode(g);
        insertNode(r);

        nextRow();
        insertNode(blank, 4);
        insertNode(g);

        nextRow();
        insertNode(new NodeExit("e", this));
        insertNode(r);
        insertNode(g);
        insertNode(r);
        insertNode(b);

        level = 3;

    }
}
