package com.diamon.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class Jugador extends Personaje {

	private float velocidadX;

	private float velocidadY;

	private float velocidad;

	private static final float VELOCIDAD_JUGADOR = 5 / Juego.UNIDAD_DEL_MUNDO;

	public Jugador(Texture textura, Pantalla pantalla, float ancho, float alto, int tipoDeCuerpo) {
		super(textura, pantalla, ancho, alto, tipoDeCuerpo);

		velocidad = VELOCIDAD_JUGADOR;
	}

	public Jugador(TextureRegion texturaRegion, Pantalla pantalla, float ancho, float alto, int tipoDeCuerpo) {
		super(texturaRegion, pantalla, ancho, alto, tipoDeCuerpo);

		velocidad = VELOCIDAD_JUGADOR;
	}

	public Jugador(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, PlayMode modo, Pantalla pantalla,
			float ancho, float alto, int tipoDeCuerpo) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla, ancho, alto, tipoDeCuerpo);

		velocidad = VELOCIDAD_JUGADOR;
	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

			velocidadX = -velocidad;

		} else

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

			velocidadX = velocidad;

		} else {

			velocidadX = 0;

		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {

			velocidadY = velocidad;

		} else

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

			velocidadY = -velocidad;

		} else {

			velocidadY = 0;
		}

		x += velocidadX / Juego.DELTA_A_PIXEL * delta;

		y += velocidadY / Juego.DELTA_A_PIXEL * delta;

		camara.position.x = x;

		camara.position.y = y;

	}

	@Override
	public void colision(Personaje personaje) {

	}

}
