package com.diamon.escenarios;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Nivel;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;
import com.diamon.personajes.Jugador;
import com.diamon.personajes.Tile;
import com.diamon.utilidades.GeneradorMapaTile;

public class Oceano extends Nivel {

	private TiledMapRenderer render;

	private int numeroFilasMapa, numeroColumnasMapa;

	private GeneradorMapaTile generadorMapa;

	private int numeroTiles[];

	private Tile[] tilesMinerales;

	private Tile[] tilesFondo;

	public Oceano(Pantalla pantalla, Jugador jugador, TiledMap mapa) {
		super(pantalla, jugador);

		generadorMapa = new GeneradorMapaTile(64, 960, 17, 20);

		this.mapa = mapa;

		render = new OrthogonalTiledMapRenderer(mapa, (float) (1 / Juego.UNIDAD_DEL_MUNDO));

		numeroFilasMapa = generadorMapa.getFilas();

		numeroColumnasMapa = generadorMapa.getColumnas();

		generadorMapa.setProbabilidadMastrarTile(6, 1, 100);

		generadorMapa.setProbabilidadMastrarTile(7, 1, 100);

		generadorMapa.setProbabilidadMastrarTile(8, 2, 100);

		generadorMapa.setProbabilidadMastrarTile(9, 2, 100);

		generadorMapa.setProbabilidadMastrarTile(10, 3, 100);

		generadorMapa.setProbabilidadMastrarTile(11, 3, 100);

		generadorMapa.setProbabilidadMastrarTile(12, 4, 100);

		generadorMapa.setProbabilidadMastrarTile(17, 5, 100);

		numeroTiles = generadorMapa.getMapaTile();

		tilesMinerales = new Tile[numeroTiles.length];

		tilesFondo = new Tile[numeroTiles.length];

		for (int i = 0; i < numeroTiles.length; i++) {

			tilesMinerales[i] = new Tile(recurso.get("texturas/invisible.png", Texture.class), pantalla, 32, 32,
					Personaje.ESTATICO);

			tilesFondo[i] = new Tile(recurso.get("texturas/invisible.png", Texture.class), pantalla, 32, 32,
					Personaje.ESTATICO);

		}

		TextureRegion[] texturas = new TextureRegion[18];

		for (int i = 1; i < texturas.length; i++) {

			texturas[i] = recurso.get("texturas/tiles.atlas", TextureAtlas.class).getRegions().get(i - 1);

		}

		int[] fondo = generadorMapa.getFondoTile(2, 2, 40);

		
		TiledMapTileLayer capaTilesFondo = (TiledMapTileLayer) mapa.getLayers().get(1);

		TiledMapTileLayer capaTilesMinerales = (TiledMapTileLayer) mapa.getLayers().get(0);

		int tipoTextura = 0;

		int tipoTexturaFondo = 0;

		for (int i = 0; i < numeroColumnasMapa; i++) {

			for (int j = 0; j < numeroFilasMapa; j++) {

				tipoTextura = numeroTiles[(i * numeroFilasMapa + j)];

				tipoTexturaFondo = fondo[(i * numeroFilasMapa + j)];

				if (tipoTextura != 0) {

					Cell celda = new Cell();

					TiledMapTile tile = new StaticTiledMapTile(texturas[tipoTextura]);

					celda.setTile(tile);

					capaTilesFondo.setCell(j, i, celda);

				}

				if (tipoTexturaFondo != 0) {

					Cell celda = new Cell();

					TiledMapTile tile = new StaticTiledMapTile(texturas[tipoTexturaFondo]);

					celda.setTile(tile);

					capaTilesMinerales.setCell(j, i, celda);

				} else {

					Cell celda = new Cell();

					TiledMapTile tile = new StaticTiledMapTile(texturas[1]);

					celda.setTile(tile);

					capaTilesMinerales.setCell(j, i, celda);

				}

			}

		}

		int numeroTile = 1;

		int x = 0, y = 0, tileMineral = 0, tileFondo = 0;

		for (int i = 0; i < numeroColumnasMapa; i++) {

			for (int j = 0; j < numeroFilasMapa; j++) {

				tileMineral = numeroTiles[(i * numeroFilasMapa + j)];

				tileFondo = fondo[(i * numeroFilasMapa + j)];

				if (tileFondo != 0) {

					tilesFondo[numeroTile].setRegion(texturas[tileFondo]);

					personajes.add(tilesFondo[numeroTile]);
				} else

				{
					tilesFondo[numeroTile].setRegion(texturas[1]);

					//personajes.add(tilesFondo[numeroTile]);

				}

				if (tileMineral != 0) {

					tilesMinerales[numeroTile].setRegion(texturas[tileMineral]);

					personajes.add(tilesMinerales[numeroTile]);

				}

				x = j * 32;

				y = -32 - i * 32;

				tilesMinerales[numeroTile].setPosition(x, y);

				tilesFondo[numeroTile].setPosition(x, y);

				numeroTile++;

			}
		}

	}

	@Override
	protected void iniciar() {

	}

	@Override
	public void actualizar(float delta) {

		for (Personaje personaje : personajes) {

			personaje.actualizar(delta);

		}

		jugador.actualizar(delta);

		// luz.update();

	}

	@Override
	public void dibujar(Batch pincel, float delta) {

		render.setView(camara);

		render.render();

		pincel.begin();

		for (Personaje personaje : personajes) {

			personaje.dibujar(pincel, delta);

		}

		jugador.dibujar(pincel, delta);

		pincel.end();

		// luz.setCombinedMatrix(camara);

		// luz.render();

	}

	@Override
	public void guardarDatos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void liberarRecursos() {

		mundoVirtual.getBodies(cuerpos);

		luces.clear();

		luz.removeAll();

		cuerpos.clear();

		personajes.clear();

		mapa.dispose();

	}

}
