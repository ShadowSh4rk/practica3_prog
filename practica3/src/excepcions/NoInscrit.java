package excepcions;

public class NoInscrit extends Exception {
	public NoInscrit() {
		super("Has d'estar inscrit a l'activitat per tal de valorar-la");
	}
}

