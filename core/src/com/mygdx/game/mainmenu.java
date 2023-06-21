//package com.mygdx.game;
//
//import com.badlogic.gdx.*;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//
//
//public class mainmenu extends ApplicationAdapter implements Screen {
//    OrthographicCamera camera;
//    SpriteBatch batch;
//    Sprite MenuScreen;
//    Stage stage;
//    StartButton startButton;
//
//    Game game;
//
//
//    @Override
//    public void create() {
//        float screenWidth = 1280;
//        float screenHeight = 720;
//
//        camera = new OrthographicCamera(screenWidth, screenHeight);
//        batch = new SpriteBatch();
//
//        MenuScreen = new Sprite(new Texture("white screen.jpg"));
//        MenuScreen.setPosition(-640, -360);
//
//        stage = new Stage();
//
//        Gdx.input.setInputProcessor(stage);
//
//        Texture defaultTexture = new Texture("button_1.png");
//        Texture hoverTexture = new Texture("button_2.png");
//        startButton = new StartButton(defaultTexture, hoverTexture);
//
//        stage.addActor(startButton);
//
//    }
//
//    @Override
//    public void render() {
//        Gdx.gl.glClearColor(1, 1, 1, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        batch.setProjectionMatrix(camera.combined);
//
//        batch.begin();
//        MenuScreen.draw(batch);
//        startButton.draw(batch, 1f);
//        batch.end();
//
//        stage.act(Gdx.graphics.getDeltaTime());
//
//        startButton.act(Gdx.graphics.getDeltaTime());
//        stage.act(Gdx.graphics.getDeltaTime());
//    }
//
//    @Override
//    public void dispose() {
//        super.dispose();
//        MenuScreen.getTexture().dispose();
//        stage.dispose();
//
//    }
//
//    @Override
//    public void show() {
//
//    }
//
//    @Override
//    public void render(float delta) {
//
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        super.resize(width, height);
//    }
//
//    @Override
//    public void hide() {
//
//    }
//}
