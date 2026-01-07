package excepcions;

public class UsuariInexistentException extends Exception {
    public UsuariInexistentException(String alies){
        super("No existeix cap activitat amb l'alies: "+alies); 
    }

}
