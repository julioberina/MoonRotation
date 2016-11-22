package com.mygdx.moonrotation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Array;
import java.util.Timer;
import java.util.TimerTask;

public class MoonRotation extends ApplicationAdapter {
        // Setting the stage
        Stage stage;
        Array<Actor> arr;
        
        // Set up start button
        TextButton startButton;
        TextButtonStyle startButtonStyle;
        
        // Set up stop button
        TextButton stopButton;
        TextButtonStyle stopButtonStyle;
        
        // Setting up background
        BitmapFont font;
        TextureRegion backgroundTexture;
        SpriteBatch batch;
        
        // Adding the Earth image
        Texture imgTexture;
        Image earth;
        
        // Variables for moon rotation
        int phase = 0;
        long stall = 0; // if time is stopped
        int timesStarted = 0;
        double angle = phase * Math.PI / 180.0;
        String moonimg = ".png";
        boolean rotating = false;
        Texture moonTexture;
        Image moon;
        
        // Setting the timer and its variables
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {   
                if (rotating)
                {
                    stall = 0;
                    if (phase < 29)
                        phase++;
                    else
                        phase = 0;
                    
                    angle = phase * 12.0 * Math.PI / 180.0;
                }
                else
                    stall++;
            }
        };
        
	@Override
	public void create () {
                // Setting background image
                backgroundTexture = new TextureRegion(new Texture("space.jpg"), 0, 0, 1400, 800);
                batch = new SpriteBatch();
                
                // Setting up the stage
                stage = new Stage(new StretchViewport(1400, 800));
                Gdx.input.setInputProcessor(stage);
                font = new BitmapFont();
                
                // Start button code
                startButtonStyle = new TextButtonStyle();
                startButtonStyle.font = font;
                startButton = new TextButton("Start Rotation", startButtonStyle);
                
                // Stop button code
                stopButtonStyle = new TextButtonStyle();
                stopButtonStyle.font = font;
                stopButton = new TextButton("Stop Rotation", stopButtonStyle);
                
                // Moon code
                imgTexture = new Texture("earth.png");
                earth = new Image(imgTexture);
                
                // Adding "actors" to the "stage"
                stage.addActor(startButton);
                stage.addActor(stopButton);
                
                // Add a Click Listener to start button
                startButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                        rotating = true;
                        ++timesStarted;
                        if (timesStarted < 2)
                        {
                            timer.scheduleAtFixedRate(task, 0, 100);
                            task.run();
                        }
                    };
                });
                
                // Add a Click Listener to stop button
                stopButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                        rotating = false;
                    };
                });

                /*
                Actor guide:
                0 - startButton
                1 - stopButton
                */

                // Setting default positions of actors on the stage
                arr = stage.getActors();               
                arr.get(0).setX((float)1280);
                arr.get(0).setY((float)30);
                arr.get(1).setX((float)1280);
                arr.get(1).setY((float)55);
	}

        @Override
        public void dispose() {
            task.cancel();
            timer.cancel();
            timer.purge();
        }
        
	@Override
	public void render () {
                // Changing moon image based on current phase
                moonTexture = new Texture(phase + moonimg);
                moon = new Image(moonTexture);
                float moon_x = (float)Math.cos(angle);
                float moon_y = (float)Math.sin(angle);
                
                batch.begin();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.draw(backgroundTexture, 0, 0);
                batch.draw(imgTexture, 550, 275);
                batch.draw(moonTexture, 650 + (350*moon_x), 365 + (350*moon_y));
                batch.end();
                stage.draw();
	}
}
