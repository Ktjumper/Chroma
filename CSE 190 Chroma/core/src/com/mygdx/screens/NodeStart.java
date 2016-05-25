package com.mygdx.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets;

public class NodeStart extends Node {
    Texture startNode;

    public NodeStart(String color, Level level)
    {
        super(color, level);
        startNode = Assets.manager.get("entrance.png", Texture.class);
        this.img = startNode;
        this.img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @ Override
    public Node clone()
    {
        return new NodeStart(color, this.level);
    }

    public boolean isStart()    {        return true;    }
}
