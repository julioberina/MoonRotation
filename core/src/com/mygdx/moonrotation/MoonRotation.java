package com.mygdx.moonrotation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Array;

public class MoonRotation extends ApplicationAdapter {
        // Button attributes
        Stage stage;
        Array<Actor> arr;
        TextButton button;
        TextButtonStyle textButtonStyle;
        BitmapFont font;
        TextureRegion backgroundTexture;
        SpriteBatch batch;
        
	@Override
	public void create () {
                // Setting background image
                backgroundTexture = new TextureRegion(new Texture("space.jpg"), 0, 0, 1366, 768);
                batch = new SpriteBatch();
                
                // Button code
                stage = new Stage(new StretchViewport(1366, 768));
                Gdx.input.setInputProcessor(stage);
                font = new BitmapFont();
                textButtonStyle = new TextButtonStyle();
                textButtonStyle.font = font;
                
                // Add a Click Listener to textButton
                
                button = new TextButton("Click Me", textButtonStyle);
                stage.addActor(button);
                
                /*
                Actor guide:
                0 - button
                */
                
                button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                        
                    };
                });

                arr = stage.getActors();               
                arr.get(0).setX((float)400);
                arr.get(0).setY((float)300);
	}

	@Override
	public void render () {
                batch.begin();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.draw(backgroundTexture, 0, 0);
                batch.end();
                stage.draw();
	}
}
