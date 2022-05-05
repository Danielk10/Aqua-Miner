package com.diamon.utilidades;

import com.badlogic.gdx.math.MathUtils;

public class GeneradorMapaTile {

	private int filas;

	private int columnas;

	private int tiposTiles;

	private int seccionesMapa;

	private int[] mapaTile;

	public GeneradorMapaTile(int filas, int columnas, int tiposTiles, int seccionesMapa) {

		if (filas == 0 || columnas == 0) {

			this.mapaTile = new int[filas + columnas + 1];

		} else {

			this.mapaTile = new int[filas * columnas + 1];
		}

		for (int i = 0; i < this.mapaTile.length; i++) {

			this.mapaTile[i] = 0;

		}

		this.filas = filas;

		this.columnas = columnas;

		this.tiposTiles = tiposTiles;

		this.seccionesMapa = seccionesMapa;

	}

	public int[] getFondoTile(int tipoTile1, int tipoTile2, int seccionMapa, int porsentajeTile) {

		int[] fondo = new int[mapaTile.length];

		int[] tile = new int[2];

		tile[0] = tipoTile1;

		tile[1] = tipoTile2;

		for (int i = (this.mapaTile.length
				- (this.mapaTile.length / this.seccionesMapa) * (this.seccionesMapa - seccionMapa))
				- (this.mapaTile.length / this.seccionesMapa); i < (this.mapaTile.length
						- (this.mapaTile.length / this.seccionesMapa) * (this.seccionesMapa - seccionMapa)); i++) {

			if (fondo[i] == 0) {

				if (i <= seccionMapa * (((this.mapaTile.length
						- (this.mapaTile.length / this.seccionesMapa) * (this.seccionesMapa - seccionMapa))
						* porsentajeTile) / 100)) {

					fondo[i] = tile[0];

				} else {

					// fondo[i] = ((MathUtils.random(2) % 2) + 1);

					fondo[i] = tile[((MathUtils.random(2) % 2))];

				}

			}

			if (mapaTile[i] != 0) {

				if (i <= seccionMapa * (((this.mapaTile.length
						- (this.mapaTile.length / this.seccionesMapa) * (this.seccionesMapa - seccionMapa))
						* porsentajeTile) / 100)) {

					fondo[i] = tile[0];

				} else {

					fondo[i] = tile[((MathUtils.random(2) % 2))];

				}

			}

		}

		return fondo;
	}

	public void setProbabilidadMastrarTile(int tipoTile, int seccionMapa, float probabilidad) {

		for (int i = (this.mapaTile.length
				- (this.mapaTile.length / this.seccionesMapa) * (this.seccionesMapa - seccionMapa))
				- (this.mapaTile.length / this.seccionesMapa); i < (this.mapaTile.length
						- (this.mapaTile.length / this.seccionesMapa) * (this.seccionesMapa - seccionMapa)); i++) {

			if (MathUtils.random() * probabilidad > probabilidad / 2) {

				int tileProbable = MathUtils.random(this.tiposTiles);

				if (tileProbable == tipoTile) {

					if (mapaTile[i] != 0) {

						continue;

					} else {

						mapaTile[i] = tileProbable;

					}

				}

			}

		}

	}

	public int[] getMapaTile() {

		return mapaTile;
	}

	public int[] getTiposTiles() {

		final int[] tipoDeTiles = new int[tiposTiles];

		for (int i = 0; i < tiposTiles; i++) {

			tipoDeTiles[i] = i + 1;

		}

		return tipoDeTiles;
	}

	public int[] getSeccionesMapa() {

		final int[] secciones = new int[seccionesMapa];

		for (int i = 0; i < seccionesMapa; i++) {

			secciones[i] = i + 1;

		}

		return secciones;
	}

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

}
