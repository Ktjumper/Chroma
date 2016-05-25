package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets;

public class NodeBlackout extends Node {
    public NodeBlackout(String color, Level level)
    {
        super(color, level);

        Texture blackout = Assets.manager.get("black.png", Texture.class);
        blackout.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.img = blackout;
    }

    public void tapped()
    {
        Gdx.app.log("asdf", "blackout");
        if (level.currentNode.isNeighborTo(this)) {
            level.blackedOut = 3;
            level.currentNode = this;
            level.score++;
        }
    }

    @Override
    public Node clone()
    {
        return new NodeBlackout("blackout", this.level);
    }
}
