package com.diamon.personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class Tile extends Personaje {

	private float corte;

	private TextureRegion trozo;

	private boolean choque;

	public Tile(Texture textura, Pantalla pantalla, float ancho, float alto, int tipoDeCuerpo) {
		super(textura, pantalla, ancho, alto, tipoDeCuerpo);

		corte = this.getWidth() * Juego.UNIDAD_DEL_MUNDO;

	}

	public Tile(TextureRegion texturaRegion, Pantalla pantalla, float ancho, float alto, int tipoDeCuerpo) {
		super(texturaRegion, pantalla, ancho, alto, tipoDeCuerpo);

		corte = this.getWidth() * Juego.UNIDAD_DEL_MUNDO;

	}

	public Tile(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, PlayMode modo, Pantalla pantalla, float ancho,
			float alto, int tipoDeCuerpo) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla, ancho, alto, tipoDeCuerpo);

		corte = this.getWidth() * Juego.UNIDAD_DEL_MUNDO;

	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);
		if (choque) {

			corte -= 1f;

			if (corte <= 0) {

				corte = 0;

				remover = true;
			}

			this.setSize((int) corte, this.getHeight() * Juego.UNIDAD_DEL_MUNDO);

			trozo.setRegionWidth((int) corte);

			this.setRegion(trozo);

		}

	}

	@Override
	public void setRegion(TextureRegion region) {

		super.setRegion(region);

		trozo = new TextureRegion(region);

	}

	@Override
	public void colision(Personaje personaje) {

		if (personaje instanceof Jugador) {

			choque = true;

		}

	}

}
