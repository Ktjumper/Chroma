package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets;

public class Level1 extends Level {
    public Level1(SpriteBatch sb, ScreenGame gameScreen)
    {
        super(sb, gameScreen);
    }

    @Override
    void renderSequence() {
        super.renderSequence();

        int size = (int)(currentNode.size * 1.7);
        double seqY = Gdx.graphics.getHeight() * 0.8;
        int widthOffset = (Gdx.graphics.getWidth() - 3 * size) / 2;

        Texture match;
        if (sequence.get(seqI).equals("r"))
            match = Assets.manager.get("matchthiscolor1.png", Texture.class);
        else if (sequence.get(seqI).equals("g"))
            match = Assets.manager.get("matchthiscolor3.png", Texture.class);
        else if (sequence.get(seqI).equals("b"))
            match = Assets.manager.get("matchthiscolor2.png", Texture.class);
        else
            match = Assets.manager.get("matchthiscolor1.png", Texture.class);

        //match this color!
        match.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sb.draw(match, 20, (int)(seqY - size), size, size);

        //get to here!
        Texture here = Assets.manager.get("gettohere.png", Texture.class);
        here.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sb.draw(here, Gdx.graphics.getWidth() - size - 20, currentNode.renderedY - size, size, size);

    }

    void createLevel()
    {
        sequence.add("r");
        sequence.add("g");
        sequence.add("b");

        Node r, g, b, rand, blank;

        r = new Node("r", this);
        g = new Node("g", this);
        b = new Node("b", this);
        //rand = new NodeRandom("0", this);
        blank = new NodeBlank("blank", this);
        Node start = new NodeStart("s", this);

        insertNode(blank, 5);
        nextRow();
        insertNode(blank, 5);
        nextRow();

        insertNode(start);
        insertNode(r);
        insertNode(g);
        insertNode(b);
        insertNode(new NodeExit("e", this));

        level = 1;

    }
}
