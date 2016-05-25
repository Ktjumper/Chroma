package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kenneth on 8/31/2015.
 */
public class Level6 extends Level {
    public Level6 (SpriteBatch sb, ScreenGame gameScreen) { super(sb, gameScreen);}

    void createLevel() {

        sequence.add("b");
        sequence.add("r");
        sequence.add("g");
        sequence.add("r");
        sequence.add("g");

        Node r, g, b, rand, auto, blank, pblue;

        r = new Node("r", this);
        g = new Node("g", this);
        b = new Node("b", this);
        //auto = new NodeAuto("star", this);
        rand = new NodeRandom("0", this);
        Node start = new NodeStart("s", this);
        blank = new NodeBlank("blank", this);
        pblue = new NodePortal("pblue", this, "blue");

        insertNode(blank, 5);
        nextRow();
        insertNode(blank, 5);
        nextRow();

        insertNode(start);
        insertNode(b);
        insertNode(r);
        insertNode(g);
        insertNode(pblue);

        nextRow();
        insertNode(blank, 5);


        nextRow();
        insertNode(new NodeExit("e", this));
        insertNode(b);
        insertNode(g);
        insertNode(r);
        insertNode(pblue);

        level = 6;
        star3 = 100;
    }
}
