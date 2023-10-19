package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Sakura {

	private double x;
	private double y;
	private double tamaño;
	private String direccion;
	private double velocidad;
	private Image img;
	private boolean noSeMueve;

	public Sakura() {
		this.x = 396; // por defecto aparece en el medio en ambas coordenadas
		this.y = 271;
		this.tamaño = 0.05;
		this.velocidad = 2;
		this.img = Herramientas.cargarImagen("laykaQuietaD.png");
		this.direccion = "D";
		this.noSeMueve = true;
	}
	
	public void dibujar(Entorno e) {
		if (!noSeMueve) {
			if (direccion == "U") {
				img = Herramientas.cargarImagen("laykaarriba.gif");
			}
			if (direccion == "D") {
				img = Herramientas.cargarImagen("laykabajo.gif");
			}
			if (direccion == "R") {
				img = Herramientas.cargarImagen("Sin-título-6.gif");
			}
			if (direccion == "L") {
				img = Herramientas.cargarImagen("laykaLl.gif");
			}
		} else {
			if (direccion == "U") {
				img = Herramientas.cargarImagen("laykaQuietaD.png");
			}
			if (direccion == "D") {
				img = Herramientas.cargarImagen("laykaQuietaI.png");
			}
			if (direccion == "R") {
				img = Herramientas.cargarImagen("laykaQuietaD.png");
			}
			if (direccion == "L") {
				img = Herramientas.cargarImagen("laykaQuietaI.png");
			}
		}
		e.dibujarImagen(img, x, y - tamaño / 2, 0, 0.4);
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

	public boolean laImpactaron(Ninja[] ninjas) {
		int areaNinja = 30;
		int areaSakura = 30;
		for (int i = 0; i < ninjas.length; i++) {
			if (ninjas[i] != null) {
				if (ninjas[i].x() - areaNinja / 2 < x + areaSakura / 2
						&& x - areaSakura / 2 < ninjas[i].x() + areaNinja / 2
						&& y + areaSakura / 2 > ninjas[i].y() - areaNinja / 2
						&& y - areaSakura / 2 < ninjas[i].y() + areaNinja / 2) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean llegoALaFlecha(int[] coordenadas) {
		int posicionDelAreaDeEntregaX = coordenadas[0];
		int posicionDelAreaDeEntregaY = coordenadas[1];
		int radioDeEntrega = 25;
		if (x >= posicionDelAreaDeEntregaX - radioDeEntrega && posicionDelAreaDeEntregaX + radioDeEntrega >= x
				&& y >= posicionDelAreaDeEntregaY - radioDeEntrega && posicionDelAreaDeEntregaY + radioDeEntrega >= y) {
			return true;
		}
		return false;
	}

	public Rasengan rasengan() {
		if (direccion == "U") {
			return new Rasengan(x, y - tamaño / 2, direccion);
		}
		if (direccion == "D") {
			return new Rasengan(x, y + tamaño / 2, direccion);
		}
		if (direccion == "L") {
			return new Rasengan(x - tamaño / 2, y, direccion);
		}
		return new Rasengan(x + tamaño / 2, y, direccion);
	}

	public void settearModoDetenido() {
		noSeMueve = true;
	}

}
