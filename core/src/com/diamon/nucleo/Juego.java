package com.diamon.nucleo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.Box2D;

public abstract class Juego extends Game {

	protected AssetManager recurso;

	public static final float ANCHO_PANTALLA = 480;

	public static final float ALTO_PANTALLA = 640;

	public static final float UNIDAD_DEL_MUNDO = 100f;

	public static final float ANCHO_NIVEL = 480f;

	public static final float ALTO_NIVEL = 640f;

	public static final float GRAVEDAD = -10.0f;

	public static final float VELOCIDAD_CAMARA = 1f;

	public static final float DELTA_A_PIXEL = 0.0166666666666667f;

	public static final float FPS = 60f;

	@Override
	public void create() {

		Box2D.init();

		recurso = new AssetManager();

	}

	@Override
	public void dispose() {

		 //recurso.dispose();

	}

}
