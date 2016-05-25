package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kenneth on 8/30/2015.
 */
public class Level33 extends Level {
    public Level33(SpriteBatch sb, ScreenGame screenGame) { super(sb, screenGame); }

    void createLevel(){

        sequence.add("r");
        sequence.add("g");
        sequence.add("b");
        sequence.add("g");
        sequence.add("b");
        sequence.add("g");
        sequence.add("r");
        sequence.add("g");
        sequence.add("b");

        Node r, g, b, rand, auto, pblue, bout, shift, start, pred, blank;

        r = new Node("r", this);
        g = new Node("g", this);
        b = new Node("b", this);
        auto = new NodeAuto("star", this);
        rand = new NodeRandom("0", this);
        start = new NodeStart("s", this);
        bout = new NodeBlackout("blackout", this);
        pblue = new NodePortal("pBlue", this, "blue");
        pred = new NodePortal("pBlue", this, "red");
        shift = new NodeShifter("star", this, true, 3);
        blank = new NodeBlank("blank", this);

        insertNode(start);
        insertNode(r);
        insertNode(rand, 2);
        insertNode(r);
        insertNode(rand);
        insertNode(r);
        insertNode(rand, 2);

        nextRow();
        insertNode(r);
        insertNode(r);
        insertNode(b);
        insertNode(rand);
        insertNode(g);
        insertNode(b);
        insertNode(pblue);
        insertNode(rand, 2);

        nextRow();
        insertNode(rand, 2);
        insertNode(g);
        insertNode(pblue);
        insertNode(shift);
        insertNode(rand);
        insertNode(shift);
        insertNode(b);
        insertNode(rand);

        nextRow();
        insertNode(rand, 2);
        insertNode(b);
        insertNode(blank, 3);
        insertNode(rand);
        insertNode(r);
        insertNode(rand);

        nextRow();
        insertNode(b);
        insertNode(bout);
        insertNode(rand);
        insertNode(blank, 3);
        insertNode(rand);
        insertNode(b);
        insertNode(rand);

        nextRow();
        insertNode(pred);
        insertNode(rand);
        insertNode(shift);
        insertNode(blank,3);
        insertNode(rand);
        insertNode(bout);
        insertNode(rand);

        nextRow();
        insertNode(rand, 2);
        insertNode(g);
        insertNode(pred);
        insertNode(rand);
        insertNode(g);
        insertNode(b);
        insertNode(new NodeExit("e", this));
        insertNode(rand);

        nextRow();
        insertNode(rand);
        insertNode(auto);
        insertNode(rand);
        insertNode(r);
        insertNode(rand);
        insertNode(r);
        insertNode(rand);
        insertNode(bout);
        insertNode(rand);

        nextRow();
        insertNode(rand, 2);
        insertNode(b);
        insertNode(g);
        insertNode(bout);
        insertNode(rand, 3);
        insertNode(auto);

        level = 33;
    }
}
