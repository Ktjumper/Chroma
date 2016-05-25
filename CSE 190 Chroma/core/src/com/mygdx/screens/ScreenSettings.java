package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;

/**
 * Created by wangd_000 on 8/25/2015.
 */
public class ScreenSettings implements Screen {
    private Stage stage;
    private Table table;

    private TextureAtlas atlas;
    private Skin skin;
    private Image background;
    private Stage backStage;

    @Override
    public void show() {
        atlas = Assets.manager.get("uiskin.pack", TextureAtlas.class);
        skin = Assets.manager.get("uiskin.json", Skin.class);
        skin.getFont("settingsFont").getData().setScale(Gdx.graphics.getWidth() * 1.3f / 1080);

        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Texture backgroundT = Assets.manager.get("soft blue bg.jpg", Texture.class);
        background = new Image(backgroundT);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        backStage = new Stage();
        backStage.addActor(background);

        stage = new Stage();
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        Texture titleT = Assets.manager.get("settingsText.png", Texture.class);
        Image title = new Image(titleT);
        table.add(title).center();
        table.row();

        TextButton reset = new TextButton("Reset Progress", skin, "settings");
        reset.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("SETTINGS", "RESET");
                Preferences data = Gdx.app.getPreferences("LevelData");
                int currentLevel = data.getInteger("currentLevel", 1);
                for(int i = 1; i <= currentLevel; i++) {
                    data.putInteger(Integer.toString(i), 0);
                }
                data.putInteger("currentLevel", 1);
                data.putInteger("lastPlayed", 1);
                data.flush();
            }
        });
        table.add(reset).width(Gdx.graphics.getWidth() / 4).height(Gdx.graphics.getWidth() / 8).spaceTop(Gdx.graphics.getWidth() / 10);

        table.row();

        TextButton about = new TextButton("About", skin, "settings");
        about.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("SETTINGS", "ABOUT");
            }
        });
        table.add(about).width(Gdx.graphics.getWidth() / 4).height(Gdx.graphics.getWidth() / 8).spaceTop(Gdx.graphics.getWidth() / 10);

        table.row();

        TextButton main = new TextButton("Main Menu", skin, "settings");
        main.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new ScreenMainMenu());
                    }
                })));
            }
        });
        table.add(main).width(Gdx.graphics.getWidth() / 4).height(Gdx.graphics.getWidth() / 8).spaceTop(Gdx.graphics.getWidth() / 10);

        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.5f)));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        atlas.dispose();
        skin.dispose();
        stage.dispose();
    }
}
