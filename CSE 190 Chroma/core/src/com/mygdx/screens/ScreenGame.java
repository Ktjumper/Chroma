package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.tween.SpriteAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class ScreenGame implements Screen, InputProcessor {
    SpriteBatch batch;
    Level currentLevel;
    int lvl;

    Stage stage;
    Image background;
    Skin skin;
    Table table;
    public boolean paused;
    private TweenManager tween;

    Stage fadeStage;
    Image fadeBackground;
    public boolean fadeIn;

    Stage overlayStage;
    Table overlayTable;

    Music beats;

    public ScreenGame(int level)
    {
        super();
        paused = false;
        this.lvl = level;
        //beats = Gdx.audio.newMusic(Gdx.files.internal("beats.ogg"));
        //beats.setLooping(true);
        //beats.play();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        switch(lvl)
        {
            case 1:
                currentLevel = new Level1(batch, this);
                break;
            case 2:
                currentLevel = new Level2(batch, this);
                break;
            case 3:
                currentLevel = new Level3(batch, this);
                break;
            case 4:
                currentLevel = new Level4(batch, this);
                break;
            case 5:
                currentLevel = new Level5(batch, this);
                break;
            case 6:
                currentLevel = new Level6(batch, this);
                break;
            case 7:
                currentLevel = new Level7(batch, this);
                break;
            case 16:
                currentLevel = new Level16(batch, this);
                break;
            case 17:
                currentLevel = new Level17(batch, this);
                break;
            case 18:
                currentLevel = new Level18(batch, this);
                break;
            case 19:
                currentLevel = new Level19(batch, this);
                break;
            case 20:
                currentLevel = new Level20(batch, this);
                break;
            case 31:
                currentLevel = new Level31(batch, this);
                break;
            case 32:
                currentLevel = new Level32(batch, this);
                break;
            case 33:
                currentLevel = new Level33(batch, this);
                break;
            case 34:
                currentLevel = new Level34(batch, this);
                break;
            case 35:
                currentLevel = new Level35(batch, this);
                break;
            default:
                currentLevel = new Level1(batch, this);
                break;
        }

        Texture backgroundT = Assets.manager.get("UI pauseBG.png", Texture.class);
        background = new Image(backgroundT);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();
        table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        skin = Assets.manager.get("uiskin.json", Skin.class);
        skin.getFont("settingsFont").getData().setScale(Gdx.graphics.getWidth() * 1.3f / 1080);
        skin.getFont("levelNumFont").getData().setScale(Gdx.graphics.getWidth() * 2f / 1080);

        TextButton resume = new TextButton("Resume", skin, "pause");
        resume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                unpauseGame();
            }
        });
        table.add(resume).width(Gdx.graphics.getWidth() / 4).height(Gdx.graphics.getWidth() / 8).spaceTop(Gdx.graphics.getWidth() / 10);

        table.row();

        TextButton restart = new TextButton("Restart", skin, "pause");
        restart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        fadeIn = true;
                        Gdx.input.setInputProcessor(fadeStage);
                        fadeStage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.5f), Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenGame(lvl));
                            }
                        })));
                    }
                })));
            }
        });
        table.add(restart).width(Gdx.graphics.getWidth() / 4).height(Gdx.graphics.getWidth() / 8).spaceTop(Gdx.graphics.getWidth() / 10);

        table.row();

        TextButton levelselect = new TextButton("Level Select", skin, "pause");
        levelselect.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        fadeIn = true;
                        Gdx.input.setInputProcessor(fadeStage);
                        fadeStage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.5f), Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                if(currentLevel.level > 30)
                                    ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenLevelSelectHard());
                                else if(currentLevel.level > 15)
                                    ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenLevelSelectMedium());
                                else
                                    ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenLevelSelectEasy());
                            }
                        })));
                    }
                })));
            }
        });
        table.add(levelselect).width(Gdx.graphics.getWidth() / 4).height(Gdx.graphics.getWidth() / 8).spaceTop(Gdx.graphics.getWidth() / 10);

        table.row();

        TextButton menu = new TextButton("Main Menu", skin, "pause");
        menu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        fadeIn = true;
                        Gdx.input.setInputProcessor(fadeStage);
                        fadeStage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.5f), Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenMainMenu());
                            }
                        })));
                    }
                })));
            }
        });
        table.add(menu).width(Gdx.graphics.getWidth() / 4).height(Gdx.graphics.getWidth() / 8).spaceTop(Gdx.graphics.getWidth() / 10);

        stage.addActor(background);
        stage.addActor(table);

        tween = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        Texture fadeT = Assets.manager.get("soft blue bg.jpg", Texture.class);
        fadeT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fadeBackground = new Image(fadeT);
        fadeBackground.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        fadeIn = true;
        fadeStage = new Stage();
        fadeStage.addActor(fadeBackground);
        fadeStage.addAction(Actions.sequence(Actions.alpha(1), Actions.run(new Runnable() {
            @Override
            public void run() {
                endFade();
            }
        })));

        Gdx.input.setInputProcessor(fadeStage);

        overlayTable = new Table();
        overlayTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Label levelNum = new Label("" + lvl, skin, "levelNumTitle");
        overlayTable.add(levelNum).expand().center().top();
        overlayStage = new Stage();
        overlayStage.addActor(overlayTable);
    }

    @Override
    public void render(float delta) {
        //Gdx.app.log("asdf", "dTime: + " + Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.enableBlending();
        batch.begin();

        currentLevel.renderBG();
        currentLevel.renderNodes();
        currentLevel.renderPause();
        currentLevel.renderSequence();

        batch.end();

        overlayStage.act(delta);
        overlayStage.draw();

        tween.update(delta);

        if(paused) {
            stage.act(delta);
            stage.draw();
        }

        if(fadeIn) {
            fadeStage.act(delta);
            fadeStage.draw();
        }
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
       // beats.stop();
    }

    @Override
    public void dispose() {

    }

    //use touchdown - feels snappier
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("asdf", "touched " + screenX + ", " + screenY);

        Node n = currentLevel.touchNode(screenX, screenY);

        return true;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //Gdx.app.log("asdf", "touched " + screenX + ", " + screenY);

        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    public void pauseGame() {
        paused = true;
        Gdx.input.setInputProcessor(stage);
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.5f)));
    }

    public void unpauseGame() {
        Gdx.input.setInputProcessor(this);
        stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
            @Override
            public void run() {
                paused = false;
            }
        })));
    }

    public void endFade() {
        Gdx.input.setInputProcessor(this);
        fadeStage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
            @Override
            public void run() {
                fadeIn = false;
            }
        })));
    }

    public void endGame() {
        fadeIn = true;
        Gdx.input.setInputProcessor(fadeStage);

        fadeStage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.5f), Actions.run(new Runnable() {
            @Override
            public void run() {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenWin(currentLevel));
            }
        })));
    }
}
