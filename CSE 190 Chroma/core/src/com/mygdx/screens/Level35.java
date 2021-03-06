package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kenneth on 8/30/2015.
 */
public class Level35 extends Level {
    public Level35(SpriteBatch sb, ScreenGame screenGame) { super(sb, screenGame); }

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

        Node r, g, b, rand, auto, pblue, bout, shift, start, pred, shift2;

        r = new Node("r", this);
        g = new Node("g", this);
        b = new Node("b", this);
        auto = new NodeAuto("star", this);
        rand = new NodeRandom("0", this);
        start = new NodeStart("s", this);
        bout = new NodeBlackout("blackout", this);
        pblue = new NodePortal("pBlue", this, "blue");
        pred = new NodePortal("pBlue", this, "red");
        shift = new NodeShifter("star", this, false, 2);
        shift2 = new NodeShifter("star", this, true, 5);

        insertNode(start);
        insertNode(r);
        insertNode(rand, 2);
        insertNode(shift);
        insertNode(rand);
        insertNode(r);
        insertNode(rand, 2);

        nextRow();
        insertNode(r);
        insertNode(g);
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
        insertNode(shift2);
        insertNode(rand);
        insertNode(shift);
        insertNode(b);
        insertNode(rand);

        nextRow();
        insertNode(rand, 2);
        insertNode(b);
        insertNode(rand);
        insertNode(b);
        insertNode(r);
        insertNode(rand);
        insertNode(r);
        insertNode(rand);

        nextRow();
        insertNode(b);
        insertNode(bout);
        insertNode(rand);
        insertNode(g);
        insertNode(rand);
        insertNode(g);
        insertNode(rand);
        insertNode(b);
        insertNode(rand);

        nextRow();
        insertNode(rand, 2);
        insertNode(pred);
        insertNode(shift2);
        insertNode(rand);
        insertNode(r);
        insertNode(auto);
        insertNode(bout);
        insertNode(rand);

        nextRow();
        insertNode(rand, 2);
        insertNode(g);
        insertNode(rand);
        insertNode(g);
        insertNode(b);
        insertNode(pred);
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

        level = 35;
    }
}
