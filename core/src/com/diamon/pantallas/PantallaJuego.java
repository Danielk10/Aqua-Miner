package com.diamon.pantallas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.diamon.escenarios.Oceano;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;
import com.diamon.personajes.Jugador;

public class PantallaJuego extends Pantalla {

	private Jugador jugador;

	private TiledMap mapaTile;

	private Oceano mundo;

	public PantallaJuego(Juego juego) {
		super(juego);

	}

	@Override
	public void mostrar() {

		mapaTile = recurso.get("mapas/mapa.tmx");

		jugador = new Jugador(recurso.get("texturas/badlogic.jpg", Texture.class), this, 32, 32, Personaje.ESTATICO);

		jugador.setPosition(100, 100);

		personajes.add(jugador);

		mundo = new Oceano(this, jugador, mapaTile);

	}

	@Override
	public void eventos() {

	}

	@Override
	public void colisiones() {

		for (Personaje personaje : personajes) {

			if (personaje.getBoundingRectangle().overlaps(jugador.getBoundingRectangle())) {

				jugador.colision(personaje);

				personaje.colision(jugador);

			}

			if (personaje.isRemover()) {

				personajes.removeValue(personaje, true);
			}

		}

	}

	@Override
	public void actualizar(float delta) {

		mundo.actualizar(delta);

	}

	@Override
	public void dibujar(Batch pincel, float delta) {

		mundo.dibujar(pincel, delta);

	}

	@Override
	public void guardarDatos() {

		mundo.guardarDatos();

	}

	@Override
	public void liberarRecursos() {

		mundo.liberarRecursos();

	}

}
