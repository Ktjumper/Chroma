package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Assets;

/**
 * Created by Dylan on 8/21/2015.
 */
public class ScreenMainMenu implements Screen {

    private Stage stage;
    private Table table;

    private TextureAtlas atlas;
    private Skin skin;
    private Image background;
    private Stage backStage;

    private Music music;
    private Sound click;

    private AssetManager manager;

    public ScreenMainMenu()
    {
        super();
    }

    @Override
    public void show() {
        click = Gdx.audio.newSound(Gdx.files.internal("load.wav"));
        //music = Gdx.audio.newMusic(Gdx.files.internal("win.mp3"));
        //music.setLooping(true);
        //music.play();

        atlas = Assets.manager.get("menubuttons.pack", TextureAtlas.class);
        skin =  Assets.manager.get("mainmenu.json", Skin.class);

        table =  new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Texture backgroundT = Assets.manager.get("soft blue bg.jpg", Texture.class);
        backgroundT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        background = new Image(backgroundT);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        backStage = new Stage();
        backStage.addActor(background);

        stage = new Stage();
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        Texture iconT = Assets.manager.get("logoname.png", Texture.class);
        iconT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image icon = new Image(iconT);
        table.add(icon).width(Gdx.graphics.getWidth() / 1.5f).height(Gdx.graphics.getWidth() / 1.2f).spaceBottom(Gdx.graphics.getWidth() / 16);
        table.row();

        Texture start = Assets.manager.get("UI start.png", Texture.class);
        start.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Button play = new Button(new TextureRegionDrawable(new TextureRegion(start)));

        //Button play = new Button(skin, "menuStart");
        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //music.stop();
                click.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenGame(Gdx.app.getPreferences("LevelData").getInteger("lastPlayed", 1)));
                    }
                })));
            }
        });

        Texture select = Assets.manager.get("UI levelselect.png", Texture.class);
        select.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Button levelselect = new Button(new TextureRegionDrawable(new TextureRegion(select)));

        levelselect.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // music.stop();
                click.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenLevelSelectEasy());
                    }
                })));
            }
        });

        Texture settingT = Assets.manager.get("UI settings.png", Texture.class);
        settingT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Button settings = new Button(new TextureRegionDrawable(new TextureRegion(settingT)));

        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //music.stop();
                click.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenSettings());
                    }
                })));
            }
        });

        Texture storeT = Assets.manager.get("UI store.png", Texture.class);
        storeT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Button store = new Button(new TextureRegionDrawable(new TextureRegion(storeT)));

        store.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //music.stop();
                click.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenStore());
                    }
                })));
            }
        });

        table.add(play).width(Gdx.graphics.getWidth() / 2.5f).height(Gdx.graphics.getHeight() / 16);
        table.row();
        table.add(levelselect).width(Gdx.graphics.getWidth() / 2.5f).height(Gdx.graphics.getHeight() / 16).spaceTop(Gdx.graphics.getHeight() / 32);
        table.row();
        table.add(settings).width(Gdx.graphics.getWidth() / 2.5f).height(Gdx.graphics.getHeight() / 16).spaceTop(Gdx.graphics.getHeight() / 32);
        table.row();
        table.add(store).width(Gdx.graphics.getWidth() / 2.5f).height(Gdx.graphics.getHeight() / 16).spaceTop(Gdx.graphics.getHeight() / 32);

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
        //music.dispose();
        click.dispose();
        skin.dispose();
        stage.dispose();
    }
}
