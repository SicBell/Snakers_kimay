package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.LinkedList;
import java.util.Stack;



import com.mygdx.game.Game.InfoGame;



import static com.mygdx.game.Game.InfoGame.SCALE;
import com.mygdx.game.Direction;
import com.mygdx.game.asset.Asset;

public class Snake {

    private static final int INITIAL_BODY_COUNT = 3;

    private LinkedList<Cell> snakeBody;
    private Stack<GameObject> lives;
    private TextureAtlas atlas;
    private Direction dir;
    private Cell head;
    private Cell tail;
    private boolean Pause;
    private boolean Mute;
    private int LifeCount = lives.size();
    private SpriteBatch batch;


    public Snake(TextureAtlas atlas) {
        this.dir = Direction.RIGHT;
        this.atlas = atlas;
        lives = new Stack<GameObject>();
        snakeBody = new LinkedList<Cell>();
        restoreHealth();
        init();
    }

    public void restoreHealth() {
        for (int i = 0; i < 5; i++) {
            GameObject life = new GameObject(Asset.instance().getSprite("heart"));
            life.setSize(25, 25);
            life.setPosition((InfoGame.SCREEN_WIDTH - 25) - life.getWidth() * (i * 1.2f) , InfoGame.SCREEN_HEIGHT - life.getHeight() - 10);
            System.out.print((InfoGame.SCREEN_WIDTH - 25) - life.getWidth() * (i * 1.2f) + " ");
            System.out.println(InfoGame.SCREEN_HEIGHT - life.getHeight() - 10);
            lives.add(life);

        }
    }
    public void AddHealth() {
        GameObject life = new GameObject(Asset.instance().getSprite("heart"));
        int i = getLifeCount();
        life.setSize(25, 25);
        System.out.println("life count: " + i);
        if (getLifeCount() < 5){
            life.setPosition(615 - life.getWidth() * (i * 1.2f) , 445);
            System.out.println(life.getX() + " " + life.getY());
            lives.add(life);}
    }

    private void init() {
        snakeBody.clear();
        for (int i = INITIAL_BODY_COUNT; i > 0; i--) {
            Cell body = new Cell(Asset.instance().getSprite(getBodyType(i)), SCALE * i, 0);
            snakeBody.add(body);
        }
        dir = Direction.RIGHT;
        head = snakeBody.getFirst().originCenter();
        tail = snakeBody.getLast().originCenter();
    }

    private String getBodyType(int index) {
        if (index == INITIAL_BODY_COUNT) return "Snake Head";
        if (index == 0) return "Snake Tail";
        else return "Snake Body";
    }

    public void moveBody() {
        for (int i = snakeBody.size() - 1; i > 0; i--) {
            Cell nextBody = snakeBody.get(i - 1);
            Cell body = snakeBody.get(i);
            body.setPosition(nextBody.getX(), nextBody.getY());
        }
        head.setDirection(dir);
        checkWallCollision();
    }

    public void handleEvents() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && dir != Direction.DOWN) dir = Direction.UP;
        else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && dir != Direction.UP)
            dir = Direction.DOWN;
        else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && dir != Direction.RIGHT)
            dir = Direction.LEFT;
        else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && dir != Direction.LEFT)
            dir = Direction.RIGHT;
    }
    public void Pause(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            Pause = !Pause;
        }
    }
    public void Mute(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)){
            Mute = !Mute;
        }
    }


    public void render(SpriteBatch batch) {
        for (Cell body : snakeBody) {
            body.draw(batch);
        }
        for (GameObject life : lives) {
            life.draw(batch);
        }
    }

    public boolean isCrash() {
        for (int i = 1; i < snakeBody.size(); i++) {
            if (head.isCollide(snakeBody.get(i))) return true;
        }
        return false;
    }

    private void checkWallCollision() {
        if (head.getY() > InfoGame.SCREEN_HEIGHT) head.setY(0);
        if (head.getY() < 0) head.setY(InfoGame.SCREEN_HEIGHT);
        if (head.getX() > InfoGame.SCREEN_WIDTH) head.setX(0);
        if (head.getX() < 0) head.setX(InfoGame.SCREEN_WIDTH);
    }


    public boolean isFoodTouch(GameObject food) {
        return this.snakeBody.getFirst().isCollide(food);
    }


    public void grow() {
        snakeBody.getLast().sprite.setRegion(atlas.getRegions().get(3));
        Cell body = new Cell(atlas.createSprite("Snake Tail"), tail.getX(), tail.getY());
        snakeBody.add(body);
        tail = snakeBody.getLast().originCenter();
        System.out.println(snakeBody.size());
    }

    public boolean isPause() {
        return Pause;
    }

    public boolean isMute() {
        return Mute;
    }

    public boolean hasLive() {
        return !lives.isEmpty();
    }

    public LinkedList<Cell> getBody() {
        return snakeBody;
    }

    public void popLife() {
        lives.pop();
    }


    public void reset() {
        init();
    }

    public int getLifeCount() {
        return LifeCount;
    }

    public void setLifeCount(int lifeCount) {
        LifeCount = lifeCount;
    }
}
