package com.diamon.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;
import com.diamon.personajes.Jugador;

public class PantallaJuego extends Pantalla {

	private Jugador jugador;

	public PantallaJuego(Juego juego) {
		super(juego);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mostrar() {

		jugador = new Jugador(new Texture(Gdx.files.internal("badlogic.jpg")), this, 128, 128, Personaje.ESTATICO);

		jugador.setPosition(320, 200);

	}

	@Override
	public void eventos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void colisiones() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dibujar(Batch pincel, float delta) {

		pincel.begin();

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
