package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.screens.ScreenSplash;

public class MyGdxGame extends Game {

	@Override
	public void create () {
		Music bgm = Gdx.audio.newMusic(Gdx.files.internal("Reflections.ogg"));
		bgm.setLooping(true);
		bgm.play();
		setScreen(new ScreenSplash());
	}
}
