package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level23 extends Level {
    public Level23 (SpriteBatch sb, ScreenGame gameScreen) { super(sb, gameScreen);}

    void createLevel()
    {
        sequence.add("b");
        sequence.add("r");
        sequence.add("g");
        sequence.add("r");
        sequence.add("g");

        Node r, g, b, rand, auto, blank, shift;

        r = new Node("r", this);
        g = new Node("g", this);
        b = new Node("b", this);
        //auto = new NodeAuto("star", this);
        rand = new NodeRandom("0", this);
        Node start = new NodeStart("s", this);
        blank = new NodeBlank("blank", this);
        shift = new NodeShifter("star", this, true, 4);

        insertNode(blank, 5);

        nextRow();
        insertNode(start);
        insertNode(rand);
        insertNode(shift);
        insertNode(rand, 2);

        nextRow();
        insertNode(rand, 2);
        insertNode(shift);
        insertNode(shift);
        insertNode(rand);


        nextRow();
        insertNode(rand, 4);
        insertNode(shift);

        nextRow();
        insertNode(rand);
        insertNode(blank);
        insertNode(new NodeExit("e", this));
        insertNode(blank);
        insertNode(rand);

        level = 23;
    }
}
