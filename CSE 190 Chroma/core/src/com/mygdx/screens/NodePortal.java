package com.mygdx.screens;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Assets;

public class NodePortal extends Node{
    Node portal;
    String tag;

    Texture portalNode;

    public NodePortal(String color, Level level, String tag)
    {
        super(color, level);
        portalNode = Assets.manager.get("portalBlue.png", Texture.class);
        img = portalNode;
        this.img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.tag = tag;
    }

    public Node clone()
    {
        NodePortal pblue = new NodePortal(this.color, this.level, tag);
        return pblue;
    }

    public void tapped()
    {
        if (level.currentNode.isNeighborTo(this))
        {
            Node other;
            for (int i = 0; i < level.board.size(); i++)
            {
                for (int j = 0; j < level.board.get(i).size(); j++)
                {
                    other = level.board.get(i).get(j);
                    if (other.isPortal(this.tag) && other != this)
                    {
                        level.currentNode = other;
                        level.score++;
                        return;
                    }
                }
            }
        }
    }

    public boolean isPortal(String tag)
    {
        if (tag.equals(this.tag))
            return true;
        return false;
    }
}
