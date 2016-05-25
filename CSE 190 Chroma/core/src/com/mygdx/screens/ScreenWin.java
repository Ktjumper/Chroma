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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;

/**
 * Created by Dylan on 8/26/2015.
 */
public class ScreenWin implements Screen {
    private Stage stage;
    private Table table;

    private TextureAtlas atlas;
    private Skin skin;
    private Image background;
    private Stage backStage;
    private Texture scoreT;

    Level level;

    public ScreenWin(Level level) {
        this.level = level;
    }

    @Override
    public void show() {
        Preferences data =  Gdx.app.getPreferences("LevelData");

        int prevScore = data.getInteger(Integer.toString(level.level), 0);
        if(prevScore > level.score || prevScore == 0)
            data.putInteger(Integer.toString(level.level), level.score);

        int currentLevel = data.getInteger("currentLevel", 1);
        if(level.level >= currentLevel) {
            data.putInteger("currentLevel", level.level + 1);
            data.putInteger("lastPlayed", level.level + 1);
        }

        data.flush();

        atlas = Assets.manager.get("uiskin.pack", TextureAtlas.class);
        skin = Assets.manager.get("uiskin.json", Skin.class);
        skin.getFont("winFont").getData().setScale(Gdx.graphics.getWidth()* 1.3f / 1080);
        skin.getFont("levelNumFont").getData().setScale(Gdx.graphics.getWidth() * 3f / 1080);

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

        Label levelNum = new Label("" + level.level, skin, "levelNumTitle");
        table.add(levelNum).center().spaceBottom(Gdx.graphics.getHeight() / 24);
        table.row();

        Texture titleT = Assets.manager.get("clearedText.png", Texture.class);
        Image title = new Image(titleT);
        table.add(title).center().height(Gdx.graphics.getHeight() / 10f).width(Gdx.graphics.getWidth() / 1.2f);
        table.row();

        Gdx.app.log("SDDSDSD", Integer.toString(level.score));
        if(level.score <= Assets.star3[level.level]) scoreT = Assets.manager.get("score3.png", Texture.class);
        else if(level.score <= Assets.star2[level.level]) scoreT = Assets.manager.get("score2.png", Texture.class);
        else scoreT = Assets.manager.get("score1.png", Texture.class);
        scoreT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Image score = new Image(scoreT);
        table.add(score).center().spaceTop(Gdx.graphics.getHeight() / 32).height(Gdx.graphics.getHeight() / 12f).width(Gdx.graphics.getWidth() / 2f);
        table.row();

        /*Label scoreLabel = new Label("Score: " + Integer.toString(level.score), skin, "winTitle");
        table.add(scoreLabel).center();
        table.row();*/

        TextButton next = new TextButton("Next", skin, "winNext");
        next.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenGame(level.level + 1));
                    }
                })));
            }
        });
        table.add(next).spaceTop(Gdx.graphics.getHeight() / 16);

        table.row();

        TextButton menu = new TextButton("Level Select", skin, "winNext");
        menu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        if(level.level > 30)
                            ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenLevelSelectHard());
                        else if(level.level > 15)
                            ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenLevelSelectMedium());
                        else
                            ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenLevelSelectEasy());
                    }
                })));
            }
        });
        table.add(menu).spaceTop(Gdx.graphics.getHeight() / 16);

        table.row();

        TextButton mainmenu = new TextButton("Main Menu", skin, "winNext");
        mainmenu.addListener(new ClickListener() {
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
        table.add(mainmenu).spaceTop(Gdx.graphics.getHeight() / 16);

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
