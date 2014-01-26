package com.jotase.model;

import java.util.Random;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class TestWorld {
    private World world;


    private Body[] ballModels;

    // Render

    private Texture ballTexture;
    private Sprite[] ballSprites;
    private Texture whiteTexture;
    private Sprite groundSprite;

    // Render general
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    // Misc
    private final Random rand = new Random();
    
}
