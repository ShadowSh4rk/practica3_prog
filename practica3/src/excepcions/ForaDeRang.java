package excepcions;

public class ForaDeRang extends Exception {
	public ForaDeRang() {
		super("La valoracio ha d'estar en el rang 0..10");
	}
}
