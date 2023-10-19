package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Flecha {

	private double x;
	private double y;
	private double tamañoFlecha;
	private Image imagenDelPedidoAEntregar;
	private Image imagenDeBuscarPedido;

	public Flecha() {
		this.x = 397;
		this.y = 306;
		this.tamañoFlecha = 154;
		this.imagenDelPedidoAEntregar = Herramientas.cargarImagen("kunaiR.gif");
		this.imagenDeBuscarPedido = Herramientas.cargarImagen("kunaiG.gif");
	}

	public void dibujar(Entorno e) {
		if (x == 397 && y == 306) {
			e.dibujarImagen(imagenDeBuscarPedido, x, y - tamañoFlecha / 2, 0);
		} else if (y <= 75 && x <= 400) {
			e.dibujarImagen(imagenDelPedidoAEntregar, x + tamañoFlecha / 2, y, Math.PI / 2);
		} else if (y <= 75 && x >= 400) {
			e.dibujarImagen(imagenDelPedidoAEntregar, x - tamañoFlecha / 2, y, -Math.PI / 2);
		} else if (y > 75) {
			e.dibujarImagen(imagenDelPedidoAEntregar, x, y - tamañoFlecha / 2, 0);
		}
	}

	public int[] elegirCasa(Aldea aldea) {
		int posicionDelAreaDeEntregaX = 0;
		int posicionDelAreaDeEntregaY = 0;
		int carga = 0;
		int[] arrayAldea = new int[2];
		carga = numeroRandom(0, 32);
		x = aldea.posicionesDeEntregaSub(carga, 0);
		y = aldea.posicionesDeEntregaSub(carga, 1);
		posicionDelAreaDeEntregaX = aldea.posicionesDeEntregaSub(carga, 2);
		posicionDelAreaDeEntregaY = aldea.posicionesDeEntregaSub(carga, 3);
		arrayAldea[0] = posicionDelAreaDeEntregaX;
		arrayAldea[1] = posicionDelAreaDeEntregaY;
		return arrayAldea;
	}

	public int[] marcaFloreria(Aldea aldea) {
		int[] arrayFloreria = new int[2];
		x = aldea.posicionesDeEntregaSub(31, 0);
		y = aldea.posicionesDeEntregaSub(31, 1);
		arrayFloreria[0] = aldea.posicionesDeEntregaSub(31, 2);
		arrayFloreria[1] = aldea.posicionesDeEntregaSub(31, 3);
		return arrayFloreria;
	}

	private int numeroRandom(int min, int max) { // funcion random general, hay que poner en maximo [maximo +1]
		return (int) ((Math.random() * (max - min)) + min);
	}

}
