package com.mygdx.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kenneth on 8/26/2015.
 */
public class Level2 extends Level {
    public Level2(SpriteBatch sb, ScreenGame gameScreen)
    {
        super(sb, gameScreen);
    }

    void createLevel()
    {
        sequence.add("r");
        sequence.add("g");
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
        insertNode(blank);
        insertNode(r);
        insertNode(blank, 3);

        nextRow();
        insertNode(blank, 2);
        insertNode(g);
        insertNode(blank, 2);


        nextRow();
        insertNode(blank);
        insertNode(b);
        insertNode(blank, 4);

        nextRow();
        insertNode(r);
        insertNode(g);
        insertNode(b);
        insertNode(r);
        insertNode(new NodeExit("e", this));

        level = 2;




    }
}
