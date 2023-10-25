package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Rayo {

	private double x;
	private double y;
	private double tamañoX;
	private double tamañoY;
	private String direccion;
	private double velocidad;
	private Image img;

	public Rayo(double x, double y, String direccion) {
		this.x = x;
		this.y = y;
		this.tamañoX = 40; // 197
		this.tamañoY = 40; // 128
		this.velocidad = 4;
		this.img = Herramientas.cargarImagen("rayoD.gif");
		this.direccion = direccion;
	}

	public void dibujar(Entorno entorno) {
		int laykaTamaño = 40;
		if (direccion == "U") {
			img = Herramientas.cargarImagen("rayoU.gif");
			entorno.dibujarImagen(img, x, y - tamañoY / 2 - laykaTamaño, 0, 0.5);
			y -= velocidad;
		}
		if (direccion == "D") {
			img = Herramientas.cargarImagen("rayoD.gif");
			entorno.dibujarImagen(img, x, y + tamañoY / 2, 0, 0.5);
			y += velocidad;
		}
		if (direccion == "L") {
			img = Herramientas.cargarImagen("rayoL.gif");
			entorno.dibujarImagen(img, x - tamañoX / 2, y - tamañoY / 2, 0, 0.5);
			x -= velocidad;
		}
		if (direccion == "R") {
			img = Herramientas.cargarImagen("rayoR.gif");
			entorno.dibujarImagen(img, x + tamañoX / 2, y - tamañoY / 2, 0, 0.5);
			x += velocidad;
		}
	}

	public int impactoConAuto(Auto[] autos) {
		for (int i = 0; i < autos.length; i++) {
			if (autos[i] != null) {
				if (autos[i].x() - autos[i].tamaño() / 2 < x + tamañoX / 2
						&& x - tamañoX / 2 < autos[i].x() + autos[i].tamaño() / 2
						&& y + tamañoY / 2 > autos[i].y() - autos[i].tamaño() / 2
						&& y - tamañoY / 2 < autos[i].y() + autos[i].tamaño() / 2) {
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