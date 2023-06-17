package com.mygdx.game.Game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Entities.Board;
import com.mygdx.game.Entities.GameObject;
import com.mygdx.game.Entities.Snake;
import com.mygdx.game.Entities.Cell;
import com.mygdx.game.Scorer;

public class Snake_Game {

    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = Gdx.graphics.getHeight();

    private Board board;
    private Snake snake;
    private float timeState;
    private BitmapFont font;

    private GameObject food;
    private boolean isGameOver;

    public Snake_Game() {
        TextureAtlas atlas = Asset.instance().get(Asset.SNAKE_PACK);
        font = Asset.instance().get(Asset.PIXEL_FONT);
        snake = new Snake(atlas);
        board = new Board(snake, WIDTH, HEIGHT);
        food = board.generateFood();
        init();
    }

    private void init() {
        SoundPlayer.init();
        SoundPlayer.playMusic(Asset.MEMO_SOUND, false);
    }

    public void update(float delta) {
        if (snake.hasLive()) {
            timeState += delta;
            snake.handleEvents();
            if (timeState >= .09f) {
                snake.moveBody();
                timeState = 0;
            }
            if (snake.isCrash()) {
                snake.reset();
                snake.popLife();
                SoundPlayer.playSound(Asset.CRASH_SOUND, false);
            }
            if (snake.isFoodTouch(food)) {
                SoundPlayer.playSound(Asset.EAT_FOOD_SOUND, false);
                Scorer.score();
                snake.grow();
                food = board.generateFood();
            }
        } else {
            gameOver();
            if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) start();
        }
    }

    private void gameOver() {
        isGameOver = true;
        SoundPlayer.stopMusic(Asset.MEMO_SOUND);
        SoundPlayer.playMusic(Asset.GAME_OVER_SOUND, false);
    }

    private void start() {
        SoundPlayer.playMusic(Asset.MEMO_SOUND, false);
        SoundPlayer.stopMusic(Asset.GAME_OVER_SOUND);

        isGameOver = false;
        snake.reset();
        snake.restoreHealth();
        food = board.generateFood();
        Scorer.reset();
    }

    public void render(SpriteBatch batch) {
        board.render(batch);
        food.draw(batch);
        snake.render(batch);
        font.setColor(Color.BLACK);

        if (isGameOver) {
            font.draw(batch, "GAME OVER", (WIDTH - 100) / 2, (HEIGHT + 100) / 2);
            font.draw(batch, "Press any key to continue", (WIDTH - 250) / 2, (HEIGHT + 50) / 2);
        }

        font.draw(batch, "Score: " + Scorer.getScore(), InfoGame.SCALE / 2, InfoGame.SCREEN_HEIGHT - 10);
        font.draw(batch, "Size: " + snake.getBody().size(), InfoGame.SCALE / 2, InfoGame.SCREEN_HEIGHT - 40);
    }

}

