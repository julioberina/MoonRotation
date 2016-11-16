package com.mygdx.moonrotation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Array;

public class MoonRotation extends ApplicationAdapter {
        // Setting the stage
        Stage stage;
        Array<Actor> arr;
        
        // Set up button
        TextButton button;
        TextButtonStyle textButtonStyle;
        
        // Set up text field
        TextField field;
        TextFieldStyle textFieldStyle;
        
        // Setting up background
        BitmapFont font;
        TextureRegion backgroundTexture;
        SpriteBatch batch;
        
        // Adding the Earth image
        Texture imgTexture;
        Image earth;
        
        
	@Override
	public void create () {
                // Setting background image
                backgroundTexture = new TextureRegion(new Texture("space.jpg"), 0, 0, 1400, 800);
                batch = new SpriteBatch();
                
                // Setting up the stage
                stage = new Stage(new StretchViewport(1400, 800));
                Gdx.input.setInputProcessor(stage);
                font = new BitmapFont();
                
                // Button code
                textButtonStyle = new TextButtonStyle();
                textButtonStyle.font = font;
                button = new TextButton("Set Month", textButtonStyle);
                
                // Text Field code
                textFieldStyle = new TextFieldStyle();
                textFieldStyle.font = font;
                textFieldStyle.fontColor = Color.WHITE;
                field = new TextField("Enter month:", textFieldStyle);
                
                // Moon code
                imgTexture = new Texture("earth.png");
                earth = new Image(imgTexture);
                
                // Adding "actors" to the "stage"
                stage.addActor(button);
                stage.addActor(field);
                
                // Add a Click Listener to button
                button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                        
                    };
                });
                
                // Add a Click Listener to field
                field.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                        field.setText("");
                    };
                });

                /*
                Actor guide:
                0 - button
                1 - field
                */

                // Setting default positions of actors on the stage
                arr = stage.getActors();               
                arr.get(0).setX((float)1280);
                arr.get(0).setY((float)30);
                arr.get(1).setX((float)1280);
                arr.get(1).setY((float)55);
	}

	@Override
	public void render () {
                batch.begin();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.draw(backgroundTexture, 0, 0);
                batch.draw(imgTexture, 550, 275);
                batch.end();
                stage.draw();
	}
}
