package excepcions;

public class NoAcceptaCol extends Exception{
    public NoAcceptaCol() {
		super("Aquesta activitat no accepta el col·lectiu especificat");
	}
}
