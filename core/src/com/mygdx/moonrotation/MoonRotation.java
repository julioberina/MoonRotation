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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Array;

public class MoonRotation extends ApplicationAdapter {
        // Button attributes
        Stage stage;
        Array<Actor> arr;
        TextButton button;
        TextButtonStyle textButtonStyle;
        BitmapFont font;
        
	@Override
	public void create () {
                // Button code
                stage = new Stage(new StretchViewport(800, 600));
                Gdx.input.setInputProcessor(stage);
                font = new BitmapFont();
                textButtonStyle = new TextButtonStyle();
                textButtonStyle.font = font;
                
                // Add a Click Listener to textButton
                
                button = new TextButton("Button1", textButtonStyle);
                stage.addActor(button);
                
                /*
                Actor guide:
                0 - button
                */
                
                button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    arr.get(0).setX((float)400);
                    arr.get(0).setY((float)500);
                    };
                });

                arr = stage.getActors();               
                arr.get(0).setX((float)400);
                arr.get(0).setY((float)300);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                stage.draw();
	}
}
