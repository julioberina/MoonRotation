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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

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
        Array<TextureRegion> backgroundTexture;
        SpriteBatch batch;
        
        // Adding the Earth image
        Array<Texture> imgTexture;
        
        // Variables for moon rotation
        int phase = 0;
        int cycle = 0; // Earth
        double angle = phase * Math.PI / 180.0;
        String moonimg = ".png";
        boolean started = false; // earth variable as well as a moon variable
        boolean stopped = false;
        Array<Texture> moonTexture;
        
        // Variable for background animation
        int space_fr = 0;
        
        // Setting the timer and its variables
	@Override
	public void create () {
                // Setting background image
                backgroundTexture = new Array<TextureRegion>();
                backgroundTexture.setSize(23);
                for (int i = 0; i < 23; i++)
                    backgroundTexture.set(i, new TextureRegion(new Texture("spacepngs/frame_" + i + "_delay-0.1s.jpg"), 0, 0, 1280, 630));
                batch = new SpriteBatch();
                
                // Setting up the stage
                stage = new Stage(new StretchViewport(1280, 630));
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
                moonTexture = new Array<Texture>();
                moonTexture.setSize(30);
                for (int i = 0; i < 30; i++)
                    moonTexture.set(i, new Texture(i + moonimg));
                
                // Earth code
                imgTexture = new Array<Texture>();
                imgTexture.setSize(24);
                for (int i = 0; i < 24; i++)
                    imgTexture.set(i, new Texture("earthpngs/frame_" + i + "_delay-0.1s.gif"));
                
                // Adding "actors" to the "stage"
                stage.addActor(startButton);
                stage.addActor(stopButton);
                
                // Add a Click Listener to start button
                startButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                        if (started == false)
                        {
                            started = true;
                            stopped = false;
                            Timer.instance().schedule(
                                    new Task()
                                    {
                                        @Override
                                        public void run() {
                                            // Earth
                                            if (cycle < 22)
                                                cycle++;
                                            else
                                                cycle = 0;
                                            
                                            // Moon
                                            if (phase < 29)
                                                phase++;
                                            else
                                                phase = 0;
                                            
                                            angle = phase * 12.0 * Math.PI / 180.0;
                                        }
                                    }
                                    , 0, 0.1f);
                        }
                        else
                        {
                            if (stopped)
                            {
                                stopped = false;
                                Timer.instance().start();
                            }
                        }
                    };
                });
                
                // Add a Click Listener to stop button
                stopButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                        if (started && stopped == false)
                        {
                            stopped = true;
                            Timer.instance().stop();
                        }
                    };
                });

                /*
                Actor guide:
                0 - startButton
                1 - stopButton
                */

                // Setting default positions of actors on the stage
                arr = stage.getActors();               
                arr.get(0).setX((float)1160);
                arr.get(0).setY((float)55);
                arr.get(1).setX((float)1160);
                arr.get(1).setY((float)30);
	}

        @Override
        public void dispose() {
            if (stopped == false)
                Timer.instance().stop();
            Timer.instance().clear();
            
            batch.dispose();
            font.dispose();
            stage.dispose();
        }
        
	@Override
	public void render () {
                // Changing moon image based on current phase
                float moon_x = (float)Math.cos(angle);
                float moon_y = (float)Math.sin(angle);
                
                // Change background frame
                if (space_fr >= 69)
                    space_fr = 0;
                
                batch.begin();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
                batch.draw(backgroundTexture.get(space_fr++ / 3), 0, 0);
                batch.draw(imgTexture.get(cycle), 600, 250);
                batch.draw(moonTexture.get(phase), 650 + (250*moon_x), 285 + (250*moon_y));
                batch.end();
                stage.draw();
	}
}
