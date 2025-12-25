package excepcions;

public class ActivitatNoFinalitzada extends Exception {
	public ActivitatNoFinalitzada() {
		super("Has d'haver acabat l'activitat per poder valorar-la");
	}
}