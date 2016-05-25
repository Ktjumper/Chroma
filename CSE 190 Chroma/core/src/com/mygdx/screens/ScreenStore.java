package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.tween.SpriteAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Dylan on 8/30/2015.
 */
public class ScreenStore implements Screen {

    private Stage stage, purchasedStage, backStage;
    private Table table, purchasedTable;
    private Image background;
    private Image purchased;
    private Skin skin;
    private TextureAtlas atlas;
    private boolean showPurchased;

    @Override
    public void show() {
        showPurchased = false;
        purchasedTable = new Table();
        purchasedTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        purchasedStage = new Stage();
        purchasedStage.addActor(purchasedTable);

        table =  new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Texture backgroundT = Assets.manager.get("soft blue bg.jpg", Texture.class);
        backgroundT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        background = new Image(backgroundT);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Texture purchasedT = Assets.manager.get("UI purchased.png", Texture.class);
        purchasedT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        purchased = new Image(purchasedT);
        purchasedTable.add(purchased).width(Gdx.graphics.getWidth() / 1.5f).height(Gdx.graphics.getHeight() / 8f);

        backStage = new Stage();
        backStage.addActor(background);

        stage = new Stage();
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        atlas = Assets.manager.get("uistore.pack", TextureAtlas.class);
        skin = Assets.manager.get("uistore.json", Skin.class);
        skin.getFont("storeFont").getData().setScale(Gdx.graphics.getWidth() * 1.3f / 1080);

        Image title = new Image(skin.getDrawable("storeTitle"));
        table.add(title).center().spaceTop(Gdx.graphics.getHeight() / 20).width(Gdx.graphics.getWidth() / 3).height(Gdx.graphics.getHeight() / 16);
        table.row();

        for(int i = 0; i < 6; i++) {
            TextButton b;
            switch(i) {
                case 0: b = new TextButton("", skin, "storePack1Button"); break;
                case 1: b = new TextButton("", skin, "storePack2Button"); break;
                case 2: b = new TextButton("", skin, "storePack3Button"); break;
                case 3: b = new TextButton("", skin, "storePackSoundButton"); break;
                case 4: b = new TextButton("", skin, "storePackColorButton"); break;
                case 5: b = new TextButton("", skin, "storePackAdsButton"); break;
                default:  b = new TextButton("", skin, "storeButton"); break;
            }
            b.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.input.setInputProcessor(purchasedStage);
                    showPurchased = true;
                    purchasedStage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.5f), Actions.delay(1f), Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            showPurchased = false;
                            Gdx.input.setInputProcessor(stage);
                        }
                    })));
            }});
            table.add(b).height(Gdx.graphics.getHeight() / 30).spaceTop(Gdx.graphics.getHeight() / 16).fillX();
            table.row();
        }

        TextButton menu = new TextButton("Main Menu", skin, "storeButton");
        menu.addListener(new ClickListener() {
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

        table.add(menu).height(Gdx.graphics.getHeight() / 6).expandX();

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

        if (showPurchased) {
            purchasedStage.act(delta);
            purchasedStage.draw();
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

    }

    @Override
    public void dispose() {

    }
}
