package excepcions;

public class ActivitatInexistentException extends Exception {
    public ActivitatInexistentException(String nom){
        super("No existeix cap activitat amb el nom: "+nom); 
    }

}
