package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level21 extends Level {
    public Level21 (SpriteBatch sb, ScreenGame gameScreen) { super(sb, gameScreen);}

    void createLevel()
    {
        sequence.add("b");
        sequence.add("r");
        sequence.add("g");
        sequence.add("r");
        sequence.add("g");

        Node r, g, b, rand, auto, bout, shift;

        r = new Node("r", this);
        g = new Node("g", this);
        b = new Node("b", this);
        //auto = new NodeAuto("star", this);
        bout = new NodeBlackout("blackout", this);
        rand = new NodeRandom("0", this);
        Node start = new NodeStart("s", this);
        shift = new NodeShifter("star", this, true, 2);

        insertNode(start);
        insertNode(rand, 4);

        nextRow();
        insertNode(rand, 2);
        insertNode(bout);
        insertNode(g);
        insertNode(r);

        nextRow();
        insertNode(rand);
        insertNode(shift);
        insertNode(bout);
        insertNode(rand, 2);

        nextRow();
        insertNode(rand, 3);
        insertNode(bout, 2);

        nextRow();
        insertNode(rand, 5);

        nextRow();
        insertNode(rand);
        insertNode(shift);
        insertNode(new NodeExit("e", this));
        insertNode(shift);
        insertNode(rand);

        level = 21;
    }
}
