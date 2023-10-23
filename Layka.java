package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Layka {

	private double x;
	private double y;
	private double tamaño;
	private String direccion;
	private double velocidad;
	private Image img;
	private boolean noSeMueve;

	public Layka() {
		this.x = 396; // por defecto aparece en el medio en ambas coordenadas
		this.y = 271;
		this.tamaño = 50;
		this.velocidad = 2;
		this.img = Herramientas.cargarImagen("sakuraSD.gif");
		this.direccion = "D";
		this.noSeMueve = true;
	}
	
	public void dibujar(Entorno e) {
		if (!noSeMueve) {
			if (direccion == "U") {
				img = Herramientas.cargarImagen("sakuraU.gif");
			}
			if (direccion == "D") {
				img = Herramientas.cargarImagen("sakuraD.gif");
			}
			if (direccion == "R") {
				img = Herramientas.cargarImagen("sakuraR.gif");
			}
			if (direccion == "L") {
				img = Herramientas.cargarImagen("sakuraL.gif");
			}
		} else {
			if (direccion == "U") {
				img = Herramientas.cargarImagen("sakuraSU.gif");
			}
			if (direccion == "D") {
				img = Herramientas.cargarImagen("sakuraSD.gif");
			}
			if (direccion == "R") {
				img = Herramientas.cargarImagen("sakuraSR.gif");
			}
			if (direccion == "L") {
				img = Herramientas.cargarImagen("sakuraSL.gif");
			}
		}
		e.dibujarImagen(img, x, y - tamaño / 2, 0, 0.7);
	}

	public void moverIzquierda(Camino camino) {
		if (camino.posicionValida(x - velocidad, y, tamaño)) {
			direccion = "L";
			x -= velocidad;
			noSeMueve = false;
		}
	}

	public void moverDerecha(Camino camino) {
		if (camino.posicionValida(x + velocidad, y, tamaño)) {
			direccion = "R";
			x += velocidad;
			noSeMueve = false;
		}
	}

	public void moverArriba(Camino camino) {
		if (camino.posicionValida(x, y - velocidad, tamaño)) {
			direccion = "U";
			y -= velocidad;
			noSeMueve = false;
		}
	}

	public void moverAbajo(Camino camino) {
		if (camino.posicionValida(x, y + velocidad, tamaño)) {
			direccion = "D";
			y += velocidad;
			noSeMueve = false;
		}
	}

	public boolean laImpactaron(Auto[] autos) {
		int areaAuto = 30;
		int areaLayka = 30;
		for (int i = 0; i < autos.length; i++) {
			if (autos[i] != null) {
				if (autos[i].x() - areaAuto / 2 < x + areaLayka / 2
						&& x - areaLayka / 2 < autos[i].x() + areaAutos / 2
						&& y + areaLayka / 2 > autos[i].y() - areaAutos / 2
						&& y - areaLayka / 2 < autos[i].y() + areaAutos / 2) {
					return true;
				}
			}
		}
		return false;
	}
	}

	public Rayo rayo() {
		if (direccion == "U") {
			return new Rayo(x, y - tamaño / 2, direccion);
		}
		if (direccion == "D") {
			return new Rayo(x, y + tamaño / 2, direccion);
		}
		if (direccion == "L") {
			return new Rayo(x - tamaño / 2, y, direccion);
		}
		return new Rayo(x + tamaño / 2, y, direccion);
	}

	public void settearModoDetenido() {
		noSeMueve = true;
	}

}
