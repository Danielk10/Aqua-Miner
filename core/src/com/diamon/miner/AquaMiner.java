package com.diamon.miner;

import com.diamon.nucleo.Juego;
import com.diamon.pantallas.PantallaJuego;

public class AquaMiner extends Juego {

	@Override
	public void create() {

		setScreen(new PantallaJuego(this));

	}

}
