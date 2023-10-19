package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import javax.sound.sampled.Clip;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros

	private Entorno entorno;

	// Generales del juego

	private int anchoPantalla;
	private int altoPantalla;
	private int tickFrecuencia;
	private Sakura sakura;
	private Rasengan rasengan;
	private Ninja[] ninjas;
	private Ninja ninjaMuerto;
	private int numeroNinjaMuerto;
	private Camino camino;
	private Aldea aldea;
	private Flecha flecha;
	private int ninjasMuertos;
	private Image imagenFondo;
	private Image imagenInterfazDatos;
	private Image imagenFlorDeLosPedidos;
	private Image imagenCargaRasengan;
	private Image imagenPantallaFinalJuego;
	private Image imagenPantallaFinalJuegoGanaste;
	private Image imagenPantallaFinalJuegoPerdiste;
	private int totalDeEntregasRealizadas;
	private int stockDePedidos;
	private int[] siguienteUbicacion;
	private int[] ubicacionFloreria;
	private int segundosMuerteNinja;
	private int segundosCargaRasengan;
	private int tiempoDeRegeneracionDelNinja;
	private int milisegundos;
	private int segundos;
	private int tiempoTotalDePartida;
	private int sumaTotal;
	private Clip audioFondo;
	private Clip audioPantallaPuntosGano;
	private Clip audioPantallaPuntosGanoFondo;
	private Clip audioFinalPerdio;
	private Clip audioFlechaFloreria;
	private String textoTiempoRestante;
	private String textoEntregasRealizadas;
	private String textoNinjasMuertos;
	private int puntajeMaximo;

	public Juego() {

		// Inicializa el objeto entorno
		
		anchoPantalla = 800;
		altoPantalla = 600;

		this.entorno = new Entorno(this, "Plantas Invasoras V1", anchoPantalla, altoPantalla);

		// Inicializar lo que haga falta para el juego

		// Definiendo Imagenes

		imagenPantallaFinalJuego = Herramientas.cargarImagen("pantallaFinalJuego.png");
		imagenPantallaFinalJuegoGanaste = Herramientas.cargarImagen("ganaste.png");
		imagenPantallaFinalJuegoPerdiste = Herramientas.cargarImagen("perdiste.png");
		imagenFlorDeLosPedidos = Herramientas.cargarImagen("florPedido.png");
		imagenCargaRasengan = Herramientas.cargarImagen("barraRasengan.gif");

		// Definiendo Audios que se pisan

		audioFondo = Herramientas.cargarSonido("audioFondo.wav");
		audioFlechaFloreria = Herramientas.cargarSonido("flechaFloreria.wav");
		audioPantallaPuntosGano = Herramientas.cargarSonido("pantallaPuntosGano.wav");
		audioPantallaPuntosGanoFondo = Herramientas.cargarSonido("pantallaPuntosGano2.wav");
		audioFinalPerdio = Herramientas.cargarSonido("audioFinalPerdio.wav");

		// Definiendo variables generales y objetos

		setteoDeVariables();

		// Inicia el juego!

		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */

	public void tick() {

		/// Tiempo en sistema

		if (sakura != null) {
			tickFrecuencia++;
			int contador = 1;
			if (tickFrecuencia >= 81 * contador) {
				milisegundos++;
				contador++;
			}
			if (milisegundos >= 81) {
				milisegundos = 0;
				segundos++;
			}
		}

		//////// Dibuja el escenario del juego ////////

		// Dibuja Escenario o superficie

		imagenFondo = Herramientas.cargarImagen("fondolayka.jpg");
		entorno.dibujarImagen(imagenFondo, entorno.ancho() / 2, entorno.alto() / 2, 0);

		// Dibuja Sakura

		if (sakura != null) {
			sakura.dibujar(entorno);
		}
		
		if (sakura != null) {
			if (!entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !entorno.estaPresionada(entorno.TECLA_DERECHA)
					&& !entorno.estaPresionada(entorno.TECLA_ARRIBA) && !entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				sakura.settearModoDetenido();
			}
		}

		// Dibuja Ninjas

		for (Ninja ninja : ninjas) {
			if (ninja != null) {
				ninja.dibujar(entorno);
			}
		}

		// Dibuja Rasengan

		if (rasengan != null) {
			rasengan.dibujar(entorno);
		}

		// Dibuja Flecha/Kunai de entrega

		flecha.dibujar(entorno);

		// Interfaz de usuario

		imagenInterfazDatos = Herramientas.cargarImagen("interfazDatos.png");
		entorno.dibujarImagen(imagenInterfazDatos, entorno.ancho() / 2, entorno.alto() / 2, 0);

		//// ESCRITURA SOBRE INTERFAZ USUARIO /////

		// Dibuja cantidad de ninjas muertos

		textoNinjasMuertos = Integer.toString(ninjasMuertos);
		entorno.cambiarFont("sans", 20, Color.orange);
		entorno.escribirTexto(textoNinjasMuertos, entorno.ancho() - 744, entorno.alto() - 547);

		// Dibuja cantidad entrega de ikebana

		textoEntregasRealizadas = Integer.toString(totalDeEntregasRealizadas * 5);
		entorno.cambiarFont("sans", 20, Color.orange);
		entorno.escribirTexto(textoEntregasRealizadas, entorno.ancho() - 46, entorno.alto() - 547);

		// Dibuja cantidad de entregas a entregar

		if (stockDePedidos == 3) {
			entorno.dibujarImagen(imagenFlorDeLosPedidos, entorno.ancho() - 498, entorno.alto() - 33, 0);
		}
		if (stockDePedidos >= 2) {
			entorno.dibujarImagen(imagenFlorDeLosPedidos, entorno.ancho() - 518, entorno.alto() - 33, 0);
		}
		if (stockDePedidos >= 1) {
			entorno.dibujarImagen(imagenFlorDeLosPedidos, entorno.ancho() - 538, entorno.alto() - 33, 0);
		}

		// Dibuja Carga del Rasengan

		if (rasengan == null && segundosCargaRasengan <= segundos) {
			entorno.dibujarImagen(imagenCargaRasengan, entorno.ancho() - 390, entorno.alto() - 25, 0);
		}

		// Dibuja Tiempo Restante y ejecuta la Finalizacion del juego

		if (tiempoTotalDePartida - segundos > 0) {
			textoTiempoRestante = Integer.toString(tiempoTotalDePartida - segundos);
			entorno.cambiarFont("sans", 30, Color.orange);
			entorno.escribirTexto(textoTiempoRestante, entorno.ancho() - 223, entorno.alto() - 43);
			if ((totalDeEntregasRealizadas * 5) * (ninjasMuertos + 1) >= puntajeMaximo) {
				entorno.dibujarImagen(imagenPantallaFinalJuego, entorno.ancho() / 2, entorno.alto() / 2, 0);
				entorno.dibujarImagen(imagenPantallaFinalJuegoGanaste, entorno.ancho() / 2, entorno.alto() - 550, 0);
				sumaTotal = (totalDeEntregasRealizadas * 5) * (ninjasMuertos + 1);
				audioPantallaPuntosGanoFondo.start();
				audioPantallaPuntosGano.start();
				audioFondo.stop();
				audioFondo.setFramePosition(0);
				finalizarJuego();
			}
		} else {
			entorno.dibujarImagen(imagenPantallaFinalJuego, entorno.ancho() / 2, entorno.alto() / 2, 0);
			entorno.dibujarImagen(imagenPantallaFinalJuegoPerdiste, entorno.ancho() / 2, entorno.alto() - 540, 0);
			audioFinalPerdio.start();
			audioFondo.stop();
			audioFondo.setFramePosition(0);
			finalizarJuego();
		}

		////// CORE DEL JUEGO ///////

		// Sistema de Entrega

		if (stockDePedidos == 0 && sakura != null) {
			ubicacionFloreria = flecha.marcaFloreria(aldea); // actual lugarRetiro
			audioFlechaFloreria.start();
			if (sakura.llegoALaFlecha(ubicacionFloreria)) {
				audioFlechaFloreria.stop();
				audioFlechaFloreria.setFramePosition(0);
				Herramientas.cargarSonido("floreriaCargaPedido.wav").start();
				stockDePedidos = numeroRandom(1, 4);
				siguienteUbicacion = flecha.elegirCasa(aldea);
				Herramientas.cargarSonido("flechaEntrega.wav").start();
			}
		}

		if (sakura != null && stockDePedidos != 0 && sakura.llegoALaFlecha(siguienteUbicacion)) {
			Herramientas.cargarSonido("sakuraEntrega.wav").start();
			stockDePedidos--;
			totalDeEntregasRealizadas++;
			siguienteUbicacion = flecha.elegirCasa(aldea);
			Herramientas.cargarSonido("flechaEntrega.wav").start();
		}

		// Movimientos Ninja

		for (Ninja ninja : ninjas) {
			if (ninja != null) {
				ninja.mover(entorno);
			}
		}

		// Muerte sakura y Finalizacion del juego

		if (sakura != null && sakura.laImpactaron(ninjas)) {
			Herramientas.cargarSonido("ninjaEstasMuerta.wav").start();
			Herramientas.cargarSonido("sakuraImpacto.wav").start();
			Herramientas.cargarSonido("sakuraMuerte.wav").start();
			Herramientas.cargarSonido("sakuraMuerteFondo.wav").start();
			tiempoTotalDePartida = segundos;
			sakura = null;
		}

		// Muerte Ninja Rasengan

		if (rasengan != null && sakura != null) {
			numeroNinjaMuerto = rasengan.impactoConNinja(ninjas);
			if (numeroNinjaMuerto != -1) {
				Herramientas.cargarSonido("ninjaMuerto.wav").start();
				segundosMuerteNinja = segundos;
				ninjasMuertos++;
				rasengan = null;
				ninjaMuerto = ninjas[numeroNinjaMuerto];
				ninjas[numeroNinjaMuerto] = null;
			}
		}

		// Regeneracion Ninja

		if (tiempoDeRegeneracionDelNinja + segundosMuerteNinja <= segundos && numeroNinjaMuerto != -1) {
			Herramientas.cargarSonido("ninjaOkeyVamos.wav").start();
			ninjas[numeroNinjaMuerto] = new Ninja(ninjaMuerto.x(), ninjaMuerto.y(), ninjaMuerto.direccion());
			ninjaMuerto = null;
			numeroNinjaMuerto = -1;
		}

		// Rasengan esta fuera del Escenario

		if (rasengan != null && rasengan.salioDePantalla(entorno)) {
			rasengan = null;
		}

		////// TECLAS DEL JUEGO //////

		if (entorno.estaPresionada('h') || entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && sakura != null) {
			sakura.moverIzquierda(camino);
		}

		if (entorno.estaPresionada('l') || entorno.estaPresionada(entorno.TECLA_DERECHA) && sakura != null) {
			sakura.moverDerecha(camino);
		}

		if (entorno.estaPresionada('k') || entorno.estaPresionada(entorno.TECLA_ARRIBA) && sakura != null) {
			sakura.moverArriba(camino);
		}

		if (entorno.estaPresionada('j') || entorno.estaPresionada(entorno.TECLA_ABAJO) && sakura != null) {
			sakura.moverAbajo(camino);
		}

		if (entorno.estaPresionada(entorno.TECLA_ESPACIO) && segundosCargaRasengan <= segundos && sakura != null) {
			Herramientas.cargarSonido("sakuraRasengan.wav").start();
			Herramientas.cargarSonido("sakuraRasenganEfecto.wav").start();
			segundosCargaRasengan = segundos + 6;
			rasengan = sakura.rasengan();
		}
	}

	////// ADICIONALES ////////

	private void setteoDeVariables() {

		// Camino

		camino = new Camino();

		// Sakura

		sakura = new Sakura();

		// Flecha

		flecha = new Flecha();

		// Aldea

		aldea = new Aldea();

		// Definiendo Variables de juego nuevo

		tickFrecuencia = 0;
		milisegundos = 0;
		segundos = 0;
		tiempoTotalDePartida = 60;
		stockDePedidos = 0;
		ninjasMuertos = 0;
		siguienteUbicacion = aldea.floreria();
		segundosMuerteNinja = 0;
		segundosCargaRasengan = 0;
		tiempoDeRegeneracionDelNinja = 5;
		ninjaMuerto = null;
		numeroNinjaMuerto = -1;
		sumaTotal = 0;
		totalDeEntregasRealizadas = 0;
		puntajeMaximo = 160;

		// Creacion total del ninja,

		ninjas = new Ninja[7];// cantidad maxima de ninjas en pantalla
		double posicionX = 0;
		double posicionY = 0;
		int ninja = 0;
		int direccion = 0;
		String direccionNinja = "";
		ninja = numeroRandom(0, 4); // cantidad variable de ninjas totales en partida
		for (int i = ninja; i < ninjas.length; i++) {
			direccion = numeroRandom(0, 2);
			if (i % 2 != 0) {
				if (direccion == 0) {
					direccionNinja = "L";
				} else {
					direccionNinja = "R";
				}
				posicionX = numeroRandom(0, entorno.alto() - 93);
				posicionY = camino.caminoDePatrullaNinjaSub(i);

				if (i == 3 && direccionNinja == "R") { // calle de sakura
					posicionX = numeroRandom(486, entorno.ancho());
				} else {
					posicionX = numeroRandom(0, 314);
				}
				ninjas[i] = new Ninja(posicionX, posicionY, direccionNinja);
			} else {
				if (direccion == 0) {
					direccionNinja = "U";
				} else {
					direccionNinja = "D";
				}
				posicionX = camino.caminoDePatrullaNinjaSub(i);
				posicionY = numeroRandom(0, entorno.ancho());
				ninjas[i] = new Ninja(posicionX, posicionY, direccionNinja);
			}
		}
		audioFondo.start();
	}

	private void finalizarJuego() {
		sakura = null;
		entorno.cambiarFont("sans", 38, Color.orange);
		entorno.escribirTexto(textoEntregasRealizadas, entorno.ancho() - 392, entorno.alto() - 417);
		entorno.cambiarFont("sans", 38, Color.orange);
		entorno.escribirTexto(textoNinjasMuertos, entorno.ancho() - 392, entorno.alto() - 332);
		String textoSumaTotal = Integer.toString(sumaTotal);
		entorno.cambiarFont("sans", 38, Color.orange);
		entorno.escribirTexto(textoSumaTotal, entorno.ancho() - 392, entorno.alto() - 218);
		if (entorno.estaPresionada(entorno.TECLA_ENTER)) {
			Herramientas.cargarSonido("enterParaVolverAJugar.wav").start();
			audioFinalPerdio.stop();
			audioFinalPerdio.setFramePosition(0);
			audioPantallaPuntosGano.stop();
			audioPantallaPuntosGano.setFramePosition(0);
			audioPantallaPuntosGanoFondo.stop();
			audioPantallaPuntosGanoFondo.setFramePosition(0);
			setteoDeVariables(); // simula Juego juego = new Juego();
		}
	}

	private int numeroRandom(int min, int max) { // genera numero random el int max va como >>>> max + 1 <<<<
		return (int) ((Math.random() * (max - min)) + min);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
