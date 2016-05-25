package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.tween.SpriteAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class ScreenSplash implements Screen {

    private Stage stage;
    private Table table;
    private Image background;
    private Stage backStage;

    @Override
    public void show() {
        table =  new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Texture iconT = new Texture("logoname.png");
        iconT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image icon = new Image(iconT);
        table.add(icon).width(Gdx.graphics.getWidth() / 1.5f).height(Gdx.graphics.getWidth() / 1.3f).spaceBottom(Gdx.graphics.getWidth() / 16);

        Texture backgroundT = new Texture("soft blue bg.jpg");
        backgroundT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        background = new Image(backgroundT);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        backStage = new Stage();
        backStage.addActor(background);

        stage = new Stage();
        stage.addActor(table);

        Assets.load();

        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.5f)));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Assets.manager.update()) {
            stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenMainMenu());
                }
            })));
        }

        backStage.act(delta);
        backStage.draw();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
