package com.diamon.miner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.diamon.nucleo.Juego;
import com.diamon.pantallas.PantallaJuego;

public class AquaMiner extends Juego {

	@Override
	public void create() {

		super.create();

		recurso.load("texturas/badlogic.jpg", Texture.class);

		recurso.load("texturas/invisible.png", Texture.class);

		recurso.load("texturas/tiles.atlas", TextureAtlas.class);

		recurso.finishLoading();

		setScreen(new PantallaJuego(this));

	}

}
