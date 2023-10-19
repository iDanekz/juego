package juego;

public class Camino {

	private int[][] caminoHabilitado;
	private int[] caminoDePatrullaNinja;

	public Camino() {
		this.caminoHabilitado = new int[][] { { 0, 52, 95, 268, 314, 486, 534, 707, 750, 800 },
				{ 0, 75, 123, 235, 281, 393, 441, 507 } };
		this.caminoDePatrullaNinja = new int[] { 73, 97, 290, 257, 510, 415, 729 };
	}

	public int caminoDePatrullaNinjaSub(int i) {
		return caminoDePatrullaNinja[i];
	}

	public boolean posicionValida(double x, double y, double t) {
		double tamaño = t;
		double posX = x;
		double posY = y;
		for (int i = 0; i < caminoHabilitado[0].length; i += 2) {
			for (int j = 0; j < caminoHabilitado[1].length; j += 2) {
				if (posX >= caminoHabilitado[0][i] && posX <= caminoHabilitado[0][i + 1]
						&& posY >= caminoHabilitado[1][j] && posY <= caminoHabilitado[1][j + 1] || posY <= 0 + tamaño
						|| posY >= 507 || posX <= 0 + tamaño / 2 || posX >= 800 - tamaño / 2) {
					return false;
				}
			}
		}
		return true;
	}

}
