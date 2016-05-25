package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Dylan on 8/22/2015.
 */
public class ScreenLevelSelect implements Screen {
    private Stage stage;
    private Table table;

    private TextureAtlas atlas;
    private Skin skin;

    @Override
    public void show() {
        atlas = new TextureAtlas("uitext.pack");
        skin = new Skin(Gdx.files.internal("levelselect.json"), atlas);
        skin.getFont("levelSelectTitleFont").getData().setScale(Gdx.graphics.getWidth()* 2.5f / 1080);
        skin.getFont("levelSelectRowFont").getData().setScale(Gdx.graphics.getWidth()* 1.5f / 1080);

        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        Texture titleT = new Texture("easyText.png");
        Image title = new Image(titleT);
        table.add(title).center().colspan(5);
        table.row();

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 5; j++) {
                String row;
                switch(i) {
                    case 0: row = "levelSelectRow1"; break;
                    case 1: row = "levelSelectRow2"; break;
                    case 2: row = "levelSelectRow3"; break;
                    default: row = "levelSelectRow1"; break;
                }
                TextButton b = new TextButton(Integer.toString(((j + 1) + i * 5)), skin, row);
                b.setName("" + ((j + 1) + i * 5));
                b.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Gdx.app.log("LEVEL SELECT", event.getListenerActor().getName());
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new ScreenGame(Integer.parseInt(event.getListenerActor().getName())));
                    }
                });
                if (i != 0)
                    b.setDisabled(true);
                table.add(b).width(Gdx.graphics.getWidth() / 8).height(Gdx.graphics.getWidth() / 8).expandX().spaceTop(Gdx.graphics.getWidth() / 10);
            }
            table.row();
        }

        TextButton prev = new TextButton("<===", skin, "levelSelectRow1");
        prev.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("LEVEL SELECT", "PREV PAGE");
            }
        });
        table.add(prev).width(Gdx.graphics.getWidth() / 3).height(Gdx.graphics.getWidth() / 8).spaceTop(Gdx.graphics.getWidth() / 8).colspan(2);

        TextButton main = new TextButton("M", skin, "settings");
        main.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenMainMenu());
            }
        });
        table.add(main).width(Gdx.graphics.getWidth() / 8).height(Gdx.graphics.getWidth() / 8).spaceTop(Gdx.graphics.getWidth() / 8);

        TextButton next = new TextButton("===>", skin, "levelSelectRow1");
        next.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("LEVEL SELECT", "NEXT PAGE");
            }
        });
        table.add(next).width(Gdx.graphics.getWidth() / 3).height(Gdx.graphics.getWidth() / 8).spaceTop(Gdx.graphics.getWidth() / 8).colspan(2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
