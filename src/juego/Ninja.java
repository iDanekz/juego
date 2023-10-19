package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Ninja {

	private double x;
	private double y;
	private double tamaño;
	private double velocidad;
	private String direccion;
	private Image img;

	public Ninja(double x, double y, String direccion) {
		this.x = x;
		this.y = y;
		this.tamaño = 67;
		this.direccion = direccion;
		this.velocidad = 2;
		this.img = Herramientas.cargarImagen("ninjaL.gif");
	}

	public void dibujar(Entorno e) {
		if (direccion == "U") {
			img = Herramientas.cargarImagen("ninjaU.gif");
		}
		if (direccion == "D") {
			img = Herramientas.cargarImagen("ninjaD.gif");
		}
		if (direccion == "R") {
			img = Herramientas.cargarImagen("ninjaR.gif");
		}
		e.dibujarImagen(img, x, y - tamaño / 2, 0, 0.55);
	}

	public void mover(Entorno e) {
		if (direccion == "U") {
			y -= velocidad;
			if (0 >= y + tamaño / 2) {
				y = e.alto() - 93 + tamaño;
			}
		}
		if (direccion == "D") {
			y += velocidad;
			if (e.alto() - 93 <= y - tamaño / 2) {
				y = 0 - tamaño;
			}
		}
		if (direccion == "R") {
			x += velocidad;
			if (e.ancho() <= x - tamaño / 2) {
				x = 0 - tamaño;
			}
		}
		if (direccion == "L") {
			x -= velocidad;
			if (0 >= x + tamaño / 2) {
				x = e.ancho() + tamaño;
			}
		}
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

	public double tamaño() {
		return tamaño;
	}

	public String direccion() {
		return direccion;
	}
}
