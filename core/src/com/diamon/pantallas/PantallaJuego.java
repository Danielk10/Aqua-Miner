package com.diamon.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;
import com.diamon.personajes.Jugador;
import com.diamon.utilidades.GeneradorMapaTile;
import com.diamon.utilidades.Utilidad;

public class PantallaJuego extends Pantalla {

	private Jugador jugador;

	private int xTiles, yTiles;

	private GeneradorMapaTile generadorMapa = new GeneradorMapaTile(64, 1780, 17, 6);

	private int mapa[];

	private Jugador[] tiles;

	public PantallaJuego(Juego juego) {
		super(juego);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mostrar() {

		xTiles = generadorMapa.getFilas();

		yTiles = generadorMapa.getColumnas();

		generadorMapa.setProbabilidadMastrarTile(7, 1, 100);

		generadorMapa.setProbabilidadMastrarTile(5, 1, 100);

		generadorMapa.setProbabilidadMastrarTile(6, 1, 100);

		generadorMapa.setProbabilidadMastrarTile(7, 1, 100);
		
		generadorMapa.setProbabilidadMastrarTile(8, 1, 100);

		mapa = generadorMapa.getMapaTile();

		tiles = new Jugador[mapa.length];

		for (int i = 1; i < mapa.length; i++) {

			tiles[i] = new Jugador(recurso.get("texturas/invisible.png", Texture.class), this, 32, 32,
					Personaje.ESTATICO);

			personajes.add(tiles[i]);

		}

		int nu = 1;

		int x = 0, y = 0, t = 0;

		int i, j;

		for (i = 0; i < yTiles; i++) {

			for (j = 0; j < xTiles; j++) {

				t = mapa[(i * xTiles + j)];

				tiles[nu].setRegion(recurso.get("texturas/tiles.atlas", TextureAtlas.class).getRegions().get(t));

				x = j * 32;

				y = -i * 32;

				tiles[nu].setPosition(x, y);

				nu++;
			}
		}

		jugador = new Jugador(recurso.get("texturas/badlogic.jpg", Texture.class), this, 32, 32, Personaje.DIANAMICO);

		jugador.setPosition(100, 640);

	}

	@Override
	public void eventos() {

	}

	@Override
	public void colisiones() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(float delta) {

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

			camara.position.x -= 0.07f;

			jugador.setPosition(camara.position.x * 100 + 50, 600);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

			camara.position.x += 0.07f;

			jugador.setPosition(camara.position.x * 100 + 50, 600);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {

			camara.position.y += 0.07f;

		}

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

			camara.position.y -= 0.07f;

		}

		for (Personaje personaje : personajes) {

			personaje.actualizar(delta);

		}

		jugador.actualizar(delta);

		Gdx.input.vibrate(100);

	}

	@Override
	public void dibujar(Batch pincel, float delta) {

		pincel.begin();

		/*
		 * int x = 0, y = 0, t = 0;
		 * 
		 * int i, j;
		 * 
		 * for (i = 0; i < yTiles; i++) {
		 * 
		 * for (j = 0; j < xTiles; j++) {
		 * 
		 * t = mapa[(i * xTiles + j)];
		 * 
		 * System.out.println(t);
		 * 
		 * x = j * 32;
		 * 
		 * y = i * 32;
		 * 
		 * tiles[t].setPosition(x, y);
		 * 
		 * tiles[t].dibujar(pincel, delta); } }
		 */

		for (Personaje personaje : personajes) {

			personaje.dibujar(pincel, delta);

		}

		jugador.dibujar(pincel, delta);

		pincel.end();

	}

	@Override
	public void guardarDatos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void liberarRecursos() {
		// TODO Auto-generated method stub

	}

}
