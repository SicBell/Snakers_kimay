package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game.SnakerGame;
import com.mygdx.game.asset.Asset;
import com.mygdx.game.asset.SoundPlayer;

public class SSSnake extends Game{

    private SpriteBatch batch;
    private SnakerGame game;

    @Override
    public void create() {
        Asset.instance().loadAsset();
        batch = new SpriteBatch();
        game = new SnakerGame();
    }

    @Override
    public void render() {
        game.update(Gdx.graphics.getDeltaTime());
        clearScreen();
        batch.begin();
        game.render(batch);
        batch.end();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose() {
        batch.dispose();
        Asset.instance().dispose();
    }
}
