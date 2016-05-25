package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kenneth on 8/30/2015.
 */
public class Level34 extends Level {
    public Level34(SpriteBatch sb, ScreenGame screenGame) { super(sb, screenGame); }

    void createLevel(){

        sequence.add("r");
        sequence.add("g");
        sequence.add("b");
        sequence.add("r");
        sequence.add("b");
        sequence.add("r");
        sequence.add("g");
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
        shift = new NodeShifter("star", this, false, 6);
        blank = new NodeBlank("blank", this);

        insertNode(start);
        insertNode(r);
        insertNode(rand, 2);
        //insertNode(r);
        insertNode(blank);
        insertNode(rand);
        insertNode(r);
        insertNode(rand, 2);

        nextRow();
        insertNode(r);
        insertNode(r);
        insertNode(b);
        insertNode(rand);
        //insertNode(g);
        insertNode(blank);
        insertNode(b);
        insertNode(pblue);
        insertNode(rand, 2);

        nextRow();
        insertNode(rand, 2);
        insertNode(g);
        insertNode(pblue);
        insertNode(blank);
        //insertNode(shift);
        insertNode(rand);
        insertNode(shift);
        insertNode(b);
        insertNode(rand);

        nextRow();
        insertNode(rand, 2);
        insertNode(b);
        insertNode(rand);
        insertNode(blank);
        //insertNode(b);
        insertNode(r);
        insertNode(rand);
        insertNode(r);
        insertNode(rand);

        nextRow();
        insertNode(blank, 9);
        /*insertNode(b);
        insertNode(bout);
        insertNode(rand);
        insertNode(g);
        insertNode(rand);
        insertNode(g);
        insertNode(rand);
        insertNode(b);
        insertNode(rand);*/

        nextRow();
        insertNode(rand, 2);
        insertNode(pred);
        insertNode(shift);
        insertNode(blank);
        //insertNode(rand);
        insertNode(r);
        insertNode(auto);
        insertNode(bout);
        insertNode(rand);

        nextRow();
        insertNode(rand, 2);
        insertNode(g);
        insertNode(rand);
        insertNode(blank);
        //insertNode(g);
        insertNode(b);
        insertNode(g);
        insertNode(rand);
        insertNode(pred);


        nextRow();
        insertNode(rand);
        insertNode(auto);
        insertNode(rand);
        insertNode(r);
        insertNode(blank);
        //insertNode(rand);
        insertNode(r);
        insertNode(rand);
        insertNode(bout);
        insertNode(rand);

        nextRow();
        insertNode(new NodeExit("e", this));
        insertNode(rand);
        insertNode(b);
        insertNode(g);
        insertNode(blank);
        insertNode(bout);
        insertNode(rand, 2);
        insertNode(auto);

        level = 34;
    }
}
