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
 * Created by Dylan on 8/26/2015.
 */
public class ScreenLevelSelectHard implements Screen {
    private Stage stage;
    private Table table;

    private TextureAtlas atlas;
    private Skin skin;
    private Image background;
    private Stage backStage;

    @Override
    public void show() {
        Preferences data = Gdx.app.getPreferences("LevelData");

        atlas = Assets.manager.get("uiskin.pack", TextureAtlas.class);
        skin = Assets.manager.get("uiskin.json", Skin.class);
        skin.getFont("levelSelectTitleFont").getData().setScale(Gdx.graphics.getWidth()* 2.5f / 1080);
        skin.getFont("levelSelectRowFont").getData().setScale(Gdx.graphics.getWidth()* 1.5f / 1080);

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

        Texture titleT = Assets.manager.get("hardText.png", Texture.class);
        Image title = new Image(titleT);
        table.add(title).center().colspan(5);
        table.row();

        int currentLevel = data.getInteger("currentLevel", 1);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 5; j++) {
                String row;
                switch(i) {
                    case 0: row = "levelSelectRow1"; break;
                    case 1: row = "levelSelectRow2"; break;
                    case 2: row = "levelSelectRow3"; break;
                    default: row = "levelSelectRow1"; break;
                }
                TextButton b = new TextButton(Integer.toString((((j + 1) + i * 5)) + 15 * 2), skin, row);
                b.setName("" + ((((j + 1) + i * 5)) + 15 * 2));
                b.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if(!((TextButton)event.getListenerActor()).isDisabled()) {
                            Gdx.app.log("LEVEL SELECT", event.getListenerActor().getName());
                            final int level = Integer.parseInt(event.getListenerActor().getName());
                            stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenGame(level));
                                }
                            })));
                        }
                    }
                });
                int score = data.getInteger(Integer.toString((((j + 1) + i * 5)) + 15 * 2), 0);
                //if (((((j + 1) + i * 5)) + 15 * 2) > currentLevel)
                    //b.setDisabled(true);
                table.add(b).width(Gdx.graphics.getWidth() / 8).height(Gdx.graphics.getWidth() / 8).expandX().spaceTop(Gdx.graphics.getWidth() / 10);
            }
            table.row();
            for(int j = 0; j < 5; j++) {
                int levelNum = (((j + 1) + i * 5)) + 15 * 2;
                Texture scoreT;
                int score = data.getInteger(Integer.toString(levelNum), 0);
                if(score == 0 || data.getInteger("currentLevel") == levelNum) scoreT = Assets.manager.get("score0.png", Texture.class);
                else if(score <= Assets.star3[levelNum]) scoreT = Assets.manager.get("score3.png", Texture.class);
                else if(score <= Assets.star2[levelNum]) scoreT = Assets.manager.get("score2.png", Texture.class);
                else scoreT = Assets.manager.get("score1.png", Texture.class);
                scoreT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Image scoreImage = new Image(scoreT);
                table.add(scoreImage).width(Gdx.graphics.getWidth() / 8).height(Gdx.graphics.getWidth() / 20).expandX();
            }
            table.row();
        }

        TextButton prev = new TextButton("<===", skin, "levelSelectRow1");
        prev.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("LEVEL SELECT", "PREV PAGE");
                stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenLevelSelectMedium());
                    }
                })));
            }
        });
        table.add(prev).width(Gdx.graphics.getWidth() / 3).height(Gdx.graphics.getWidth() / 8).spaceTop(Gdx.graphics.getWidth() / 8).colspan(2);

        TextButton main = new TextButton("M", skin, "settings");
        main.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenMainMenu());
                    }
                })));
            }
        });
        table.add(main).width(Gdx.graphics.getWidth() / 8).height(Gdx.graphics.getWidth() / 8).spaceTop(Gdx.graphics.getWidth() / 8);

        table.add().width(Gdx.graphics.getWidth() / 3).height(Gdx.graphics.getWidth() / 8).spaceTop(Gdx.graphics.getWidth() / 8).colspan(2);

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
