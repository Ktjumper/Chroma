package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kenneth on 8/31/2015.
 */
public class Level12 extends Level {
    public Level12 (SpriteBatch sb, ScreenGame gameScreen) { super(sb, gameScreen);}

    void createLevel() {
        sequence.add("b");
        sequence.add("r");
        sequence.add("b");
        sequence.add("g");
        sequence.add("r");

        Node r, g, b, bout, start, rand, blank;

        r = new Node("r", this);
        g = new Node("g", this);
        b = new Node("b", this);
        bout = new NodeBlackout("blackout", this);
        start = new NodeStart("s", this);
        rand = new NodeRandom("0", this);
        blank = new NodeBlank("blank", this);

    }
}
