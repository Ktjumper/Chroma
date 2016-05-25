package com.mygdx.screens;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Assets;

public class NodeBlank extends Node {
    public NodeBlank(String color, Level lvl)
    {
        super(color, lvl);
        img = Assets.manager.get("blank.png", Texture.class);
    }

    public Node clone()
    {
        return new NodeBlank(this.color, this.level);
    }

}
