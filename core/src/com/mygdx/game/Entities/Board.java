package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import com.mygdx.game.Game.InfoGame;

import com.mygdx.game.asset.Asset;

import static com.mygdx.game.Game.InfoGame.SCALE;

public class Board {

    private Cell[][] cells;
    private Snake snake;
    private String[] foodTypes = {
            "coin sprite"};

    public Board(Snake snake, int width, int height) {
        this.snake = snake;
        initBoard(width, height);
    }

    private void initBoard(int width, int height) {
        cells = new Cell[width / SCALE][height / SCALE];
        for (int rowGrass = 0; rowGrass < cells.length; rowGrass++) {
            for (int colGrass = 0; colGrass < cells[rowGrass].length; colGrass++) {
                Cell cell = new Cell(Asset.instance().getSprite(randomGrass(rowGrass, colGrass)), rowGrass * SCALE, colGrass * SCALE);
                cells[rowGrass][colGrass] = cell;
            }
        }
    }


    private String randomGrass(int row, int col) {
        if (col % 2 == 0) {
            if (row % 2 != 0) return "floor1";
            if (row % 2 == 0) return "floor2";
        } else if (col % 2 != 0) {
            if (row % 2 != 0) return "floor2";
            if (row % 2 == 0) return "floor1";
        }
        return "floor2";
    }

    public void render(SpriteBatch batch) {
        for (Cell[] cell : cells) {
            for (Cell aCell : cell) {
                aCell.draw(batch);
            }
        }
    }

    public GameObject generateFood() {

        int foodType = MathUtils.random(foodTypes.length - 1);
        System.out.println("Food type: " + foodType);
        GameObject food = new GameObject(Asset.instance().getSprite(foodTypes[foodType]));
        food.setPosition(foodRandX(), foodRandY());

        for (Cell body : snake.getBody()) {
            while (food.getX() == body.getX() && body.getY() == food.getY()) {
                food.setPosition(foodRandX(), foodRandY());
            }
        }
        return food;
    }

    private float foodRandX() {
        return MathUtils.random(1, InfoGame.BOARD_WIDTH - 1) * SCALE;
    }

    private float foodRandY() {
        return MathUtils.random(1, InfoGame.BOARD_HEIGHT - 1) * SCALE;
    }

    public void reset() {
        generateFood();
    }
}
