package com.mygdx.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets;

public class NodeExit extends Node {
    Texture exitNode;

    public NodeExit(String color, Level level)
    {
        super(color, level);
        exitNode = Assets.manager.get("exit.png", Texture.class);
        this.img = exitNode;
        this.img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @ Override
    public Node clone()
    {
        return new NodeExit(color, this.level);
    }
    public boolean isExit()     {   return true;    }

}
