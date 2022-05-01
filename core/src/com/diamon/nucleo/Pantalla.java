package com.diamon.nucleo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import box2dLight.Light;
import box2dLight.RayHandler;

public abstract class Pantalla implements Screen {

	protected final Juego juego;

	protected Array<Personaje> personajes;

	protected AssetManager recurso;

	protected Stage nivel;

	protected OrthographicCamera camara;

	protected SpriteBatch pincel;

	protected World mundoVirtual;

	protected static final float STEP_TIME = 1f / 60f;

	protected static final int VELOCITY_ITERATIONS = 6;

	protected static final int POSITION_ITERATIONS = 2;

	private float accumulator = 0;

	protected Array<Body> cuerpos = new Array<Body>();

	protected Box2DDebugRenderer debugRenderer;

	protected RayHandler luz;

	protected Array<Light> luces;

	protected Pantalla(Juego juego) {

		mundoVirtual = new World(new Vector2(0, Juego.GRAVEDAD), true);

		luz = new RayHandler(mundoVirtual);

		luces = new Array<Light>();

		this.juego = juego;

		nivel = new Stage(new StretchViewport(Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA));

		((OrthographicCamera) nivel.getCamera()).setToOrtho(false, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA);

		camara = new OrthographicCamera();

		camara.setToOrtho(false, Juego.ANCHO_PANTALLA / Juego.UNIDAD_DEL_MUNDO,
				Juego.ALTO_PANTALLA / Juego.UNIDAD_DEL_MUNDO);

		camara.update();

		pincel = new SpriteBatch();

		debugRenderer = new Box2DDebugRenderer();

		personajes = new Array<Personaje>();

		recurso = juego.recurso;

	}

	public abstract void mostrar();

	public abstract void eventos();

	public abstract void colisiones();

	public abstract void actualizar(float delta);

	public abstract void dibujar(Batch pincel, float delta);

	public abstract void guardarDatos();

	public abstract void liberarRecursos();

	@Override
	public void show() {

		Gdx.input.setInputProcessor(nivel);

		mostrar();

		eventos();
	}

	@Override
	public void render(float delta) {

		ScreenUtils.clear(0.0F, 0.0F, 1.0F, 1.0F, true);

		pincel.setProjectionMatrix(camara.combined);

		accumulator += Math.min(delta, 0.25f);

		if (accumulator >= STEP_TIME) {

			accumulator -= STEP_TIME;

			mundoVirtual.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);

		}

		colisiones();

		actualizar(delta);

		camara.update();

		dibujar(pincel, delta);

		nivel.draw();

		nivel.act();

		debugRenderer.render(mundoVirtual, camara.combined);

	}

	@Override
	public void resize(int ancho, int alto) {

		nivel.getViewport().update(ancho, alto);

	}

	@Override
	public void pause() {

		guardarDatos();

		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void resume() {

		Gdx.input.setInputProcessor(nivel);

	}

	@Override
	public void hide() {

		guardarDatos();

		personajes.clear();

		liberarRecursos();

		pincel.dispose();

		Gdx.input.setInputProcessor(null);

		nivel.dispose();

		luz.dispose();

		mundoVirtual.getBodies(cuerpos);

		if (cuerpos.size > 0) {

			for (Body cuerpo : cuerpos) {

				mundoVirtual.destroyBody(cuerpo);

			}

		}

		cuerpos.clear();

		luces.clear();

		mundoVirtual.dispose();

	}

	@Override
	public void dispose() {

		guardarDatos();

		personajes.clear();

		liberarRecursos();

		pincel.dispose();

		Gdx.input.setInputProcessor(null);

		nivel.dispose();

		mundoVirtual.getBodies(cuerpos);

		if (cuerpos.size > 0) {

			for (Body cuerpo : cuerpos) {

				mundoVirtual.destroyBody(cuerpo);

			}

		}
		cuerpos.clear();

		luces.clear();

		luz.dispose();

		debugRenderer.dispose();

		mundoVirtual.dispose();

	}

}
