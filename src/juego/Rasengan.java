package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Rasengan {

	private double x;
	private double y;
	private double tamañoX;
	private double tamañoY;
	private String direccion;
	private double velocidad;
	private Image img;

	public Rasengan(double x, double y, String direccion) {
		this.x = x;
		this.y = y;
		this.tamañoX = 40; // 197
		this.tamañoY = 40; // 128
		this.velocidad = 4;
		this.img = Herramientas.cargarImagen("rasenganD.gif");
		this.direccion = direccion;
	}

	public void dibujar(Entorno entorno) {
		int sakuraTamaño = 40;
		if (direccion == "U") {
			img = Herramientas.cargarImagen("rasenganU.gif");
			entorno.dibujarImagen(img, x, y - tamañoY / 2 - sakuraTamaño, 0, 0.5);
			y -= velocidad;
		}
		if (direccion == "D") {
			img = Herramientas.cargarImagen("rasenganD.gif");
			entorno.dibujarImagen(img, x, y + tamañoY / 2, 0, 0.5);
			y += velocidad;
		}
		if (direccion == "L") {
			img = Herramientas.cargarImagen("rasenganL.gif");
			entorno.dibujarImagen(img, x - tamañoX / 2, y - tamañoY / 2, 0, 0.5);
			x -= velocidad;
		}
		if (direccion == "R") {
			img = Herramientas.cargarImagen("rasenganR.gif");
			entorno.dibujarImagen(img, x + tamañoX / 2, y - tamañoY / 2, 0, 0.5);
			x += velocidad;
		}
	}

	public int impactoConNinja(Ninja[] ninjas) {
		for (int i = 0; i < ninjas.length; i++) {
			if (ninjas[i] != null) {
				if (ninjas[i].x() - ninjas[i].tamaño() / 2 < x + tamañoX / 2
						&& x - tamañoX / 2 < ninjas[i].x() + ninjas[i].tamaño() / 2
						&& y + tamañoY / 2 > ninjas[i].y() - ninjas[i].tamaño() / 2
						&& y - tamañoY / 2 < ninjas[i].y() + ninjas[i].tamaño() / 2) {
					return i;
				}
			}
		}
		return -1;
	}

	public boolean salioDePantalla(Entorno e) {
		if (x < 0 - tamañoX || x > e.ancho() + tamañoX
				|| y > e.alto() + tamañoY || y < 0 - tamañoY) {
			return true;
		}
		return false;
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}
}
//Rayo de layka destruye plantas, pero bola de fuego destruye a layka, autos y otras plantas.
