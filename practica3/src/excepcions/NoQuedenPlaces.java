package excepcions;

public class NoQuedenPlaces extends Exception {
    public NoQuedenPlaces(){
        super("No hi ha places disponibles en aquesta activitat");
    }
}
