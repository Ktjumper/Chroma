package com.mygdx.screens;

import java.util.Random;

public class NodeRandom extends Node {
    public NodeRandom(String color, Level level)
    {
        super(color, level);
    }

    public Node clone()
    {
        Random rg = new Random();
        String color = "0";

        switch(rg.nextInt()%3)
        {
            case 0:
                color = "r";
                break;
            case 1:
                color = "g";
                break;
            default:
                color = "b";
                break;
        }

        return new Node(color, this.level);
    }
}
