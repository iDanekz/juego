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
	private Layka layka;
	private Rayo rayo;
	private Auto[] autos;
	private Camino camino;
	private Image imagenFondo;
	private Image imagenInterfazDatos;
	private Image imagenCargaRayo;
	private Image imagenPantallaFinalJuego;
	private Image imagenPantallaFinalJuegoGanaste;
	private Image imagenPantallaFinalJuegoPerdiste;
	private int segundosMuerteAuto;
	private int segundosCargaRayo;
	private int tiempoDeRegeneracionDeAutos;
	private int milisegundos;
	private int segundos;
	private int tiempoTotalDePartida;			
	private int sumaTotal;
	private Clip audioFondo;
	private Clip audioPantallaPuntosGano;
	private Clip audioPantallaPuntosGanoFondo;
	private Clip audioFinalPerdio;
	private String textoTiempoRestante;
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
		imagenCargaRasengan = Herramientas.cargarImagen("barraRayo.gif");

		// Definiendo Audios que se pisan

		audioFondo = Herramientas.cargarSonido("audioFondo.wav");
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

		if (layka != null) {
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

		imagenFondo = Herramientas.cargarImagen("fondo.png");
		entorno.dibujarImagen(imagenFondo, entorno.ancho() / 2, entorno.alto() / 2, 0);

		// Dibuja Layka

		if (layka != null) {
			layka.dibujar(entorno);
		}
		
		if (layka != null) {
			if (!entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !entorno.estaPresionada(entorno.TECLA_DERECHA)
					&& !entorno.estaPresionada(entorno.TECLA_ARRIBA) && !entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				layka.settearModoDetenido();
			}
		}

		// Dibuja Autos

		for (Auto auto : autos) {
			if (auto != null) {
				auto.dibujar(entorno);
			}
		}

		// Dibuja Rayo

		if (rayo != null) {
			rayo.dibujar(entorno);
		}

		// Interfaz de usuario

		imagenInterfazDatos = Herramientas.cargarImagen("interfazDatos.png");
		entorno.dibujarImagen(imagenInterfazDatos, entorno.ancho() / 2, entorno.alto() / 2, 0);

		//// ESCRITURA SOBRE INTERFAZ USUARIO /////

		// Dibuja cantidad de autos muertos

//		textoAutosMuertos = Integer.toString(AutosMuertos);
//		entorno.cambiarFont("sans", 20, Color.orange);
//		entorno.escribirTexto(textoAutosMuertos, entorno.ancho() - 744, entorno.alto() - 547);


		// Dibuja Carga del Rasengan

		if (rayo == null && segundosCargaRayo <= segundos) {
			entorno.dibujarImagen(imagenCargaRayo, entorno.ancho() - 390, entorno.alto() - 25, 0);
		}

		// Dibuja Tiempo Restante y ejecuta la Finalizacion del juego

		if (tiempoTotalDePartida - segundos > 0) {
			textoTiempoRestante = Integer.toString(tiempoTotalDePartida - segundos);
			entorno.cambiarFont("sans", 30, Color.orange);
			entorno.escribirTexto(textoTiempoRestante, entorno.ancho() - 223, entorno.alto() - 43);
//			if ((totalDeEntregasRealizadas * 5) * (autosMuertos + 1) >= puntajeMaximo) {
//				entorno.dibujarImagen(imagenPantallaFinalJuego, entorno.ancho() / 2, entorno.alto() / 2, 0);
//				entorno.dibujarImagen(imagenPantallaFinalJuegoGanaste, entorno.ancho() / 2, entorno.alto() - 550, 0);
//				sumaTotal = (totalDeEntregasRealizadas * 5) * (autosMuertos + 1);
//				audioPantallaPuntosGanoFondo.start();
//				audioPantallaPuntosGano.start();
//				audioFondo.stop();
//				audioFondo.setFramePosition(0);
//				finalizarJuego();
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

//		if (stockDePedidos == 0 && layka != null) {
//			ubicacionFloreria = flecha.marcaFloreria(aldea); // actual lugarRetiro
//			audioFlechaFloreria.start();
//			if (layka.llegoALaFlecha(ubicacionFloreria)) {
//				audioFlechaFloreria.stop();
//				audioFlechaFloreria.setFramePosition(0);
//				Herramientas.cargarSonido("floreriaCargaPedido.wav").start();
//				stockDePedidos = numeroRandom(1, 4);
//				siguienteUbicacion = flecha.elegirCasa(aldea);
//				Herramientas.cargarSonido("flechaEntrega.wav").start();
			}
		}

//		if (layka != null && stockDePedidos != 0 && layka.llegoALaFlecha(siguienteUbicacion)) {
//			Herramientas.cargarSonido("laykaEntrega.wav").start();
//			stockDePedidos--;
//			totalDeEntregasRealizadas++;
//			siguienteUbicacion = flecha.elegirCasa(aldea);
//			Herramientas.cargarSonido("flechaEntrega.wav").start();
//		}

		// Movimientos Auto

		for (Auto auto : autos) {
			if (auto != null) {
				auto.mover(entorno);
			}
		}

		// Muerte Layka y Finalizacion del juego

		if (layka != null && layka.laImpactaron(autos)) {
			Herramientas.cargarSonido("ninjaEstasMuerta.wav").start();
			Herramientas.cargarSonido("sakuraImpacto.wav").start();
			Herramientas.cargarSonido("sakuraMuerte.wav").start();
			Herramientas.cargarSonido("sakuraMuerteFondo.wav").start();
			tiempoTotalDePartida = segundos;
			layka = null;
		}

		// Muerte Auto Rayo

		if (rayo != null && layka != null) {
			numeroAutoMuerto = rayo.impactoConAuto(autos);
			if (numeroAutoMuerto != -1) {
				Herramientas.cargarSonido("ninjaMuerto.wav").start();
				segundosMuerteAuto = segundos;
				autosMuertos++;
				rayo = null;
				autoMuerto = autos[numeroAutoMuerto];
				autos[numeroAutoMuerto] = null;
			}
		}

		// Regeneracion Auto

		if (tiempoDeRegeneracionDelAuto + segundosMuerteAuto <= segundos && numeroAutoMuerto != -1) {
			Herramientas.cargarSonido("ninjaOkeyVamos.wav").start();
			autos[numeroAutoMuerto] = new Auto(autoMuerto.x(), autoMuerto.y(), autoMuerto.direccion());
			autoMuerto = null;
			numeroAutoMuerto = -1;
		}

		// Rayo esta fuera del Escenario

		if (rayo != null && rayo.salioDePantalla(entorno)) {
			rayo = null;
		}

		////// TECLAS DEL JUEGO //////

		if (entorno.estaPresionada('h') || entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && layka != null) {
			layka.moverIzquierda(camino);
		}

		if (entorno.estaPresionada('l') || entorno.estaPresionada(entorno.TECLA_DERECHA) && layka != null) {
			layka.moverDerecha(camino);
		}

		if (entorno.estaPresionada('k') || entorno.estaPresionada(entorno.TECLA_ARRIBA) && layka != null) {
			layka.moverArriba(camino);
		}

		if (entorno.estaPresionada('j') || entorno.estaPresionada(entorno.TECLA_ABAJO) && layka != null) {
			layka.moverAbajo(camino);
		}

		if (entorno.estaPresionada(entorno.TECLA_ESPACIO) && segundosCargaRayo <= segundos && layka != null) {
			Herramientas.cargarSonido("sakuraRasengan.wav").start();
			Herramientas.cargarSonido("sakuraRasenganEfecto.wav").start();
			segundosCargaRayo = segundos + 6;
			rayo = layka.rayo();
		}
	}

	////// ADICIONALES ////////

	private void setteoDeVariables() {

		// Camino

		camino = new Camino();

		// Layka

		layka = new Layka();

		// Definiendo Variables de juego nuevo

		tickFrecuencia = 0;
		milisegundos = 0;
		segundos = 0;
		tiempoTotalDePartida = 60;
		autosMuertos = 0;
		segundosMuerteAuto = 0;
		segundosCargaRayo = 0;
		tiempoDeRegeneracionDelAuto = 5;
		autoMuerto = null;
		numeroAutoMuerto = -1;
		sumaTotal = 0;
		puntajeMaximo = 160;

		// Creacion total del auto,

		autos = new Autos[7];// cantidad maxima de autos en pantalla
		double posicionX = 0;
		double posicionY = 0;
		int auto = 0;
		int direccion = 0;
		String direccionAuto = "";
		auto = numeroRandom(0, 4); // cantidad variable de autos totales en partida
		for (int i = auto; i < auto.length; i++) {
			direccion = numeroRandom(0, 2);
			if (i % 2 != 0) {
				if (direccion == 0) {
					direccionAuto = "L";
				} else {
					direccionAuto = "R";
				}
				posicionX = numeroRandom(0, entorno.alto() - 93);
				posicionY = camino.caminoDePatrullaAutoSub(i);

				if (i == 3 && direccionAuto == "R") { // calle de layka
					posicionX = numeroRandom(486, entorno.ancho());
				} else {
					posicionX = numeroRandom(0, 314);
				}
				autos[i] = new Auto(posicionX, posicionY, direccionAuto);
			} else {
				if (direccion == 0) {
					direccionAuto = "U";
				} else {
					direccionAuto = "D";
				}
				posicionX = camino.caminoDePatrullaAutoSub(i);
				posicionY = numeroRandom(0, entorno.ancho());
				autos[i] = new Autos(posicionX, posicionY, direccionAuo);
			}
		}
		audioFondo.start();
	}

	private void finalizarJuego() {
		layka = null;
		entorno.cambiarFont("sans", 38, Color.orange);
		entorno.cambiarFont("sans", 38, Color.orange);
		entorno.escribirTexto(textoAutosMuertos, entorno.ancho() - 392, entorno.alto() - 332);
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
