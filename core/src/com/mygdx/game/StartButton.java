//package com.mygdx.game;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//
//public class StartButton extends Actor {
//
//    private final Texture defaultTexture;
//    private final Texture hoverTexture;
//    private boolean isHovered;
//    private Game game;
//
//    public StartButton(Texture defaultTexture, Texture hoverTexture) {
//        this.defaultTexture = defaultTexture;
//        this.hoverTexture = hoverTexture;
//
//        setSize(300, 110);
//        setPosition(-getWidth()/2, -getHeight()*2);
//
//    }
//
//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        if (isHovered) {
//            batch.draw(hoverTexture, getX(), getY(), getWidth(), getHeight());
//        } else {
//            batch.draw(defaultTexture, getX(), getY(), getWidth(), getHeight());
//        }
//    }
//
//    @Override
//    public void act(float delta) {
//        updateHoverState();
//        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && isHovered) {
//            performAction();
//        }
//    }
//
//    private void updateHoverState() {
//        float mouseX = Gdx.input.getX() - getWidth()*2-20;
//        float mouseY = Gdx.input.getY() - getHeight()*2-240;
//
//        float buttonX = getX()*-1;
//        float buttonY = getY()*-1;
//        float buttonWidth = getWidth();
//        float buttonHeight = getHeight();
//
//        isHovered = mouseX >= buttonX && mouseX <= buttonX + buttonWidth + 80 &&
//                mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
//    }
//
//    private void performAction() {
//        game.setScreen(new SSSnake());
//    }
//}