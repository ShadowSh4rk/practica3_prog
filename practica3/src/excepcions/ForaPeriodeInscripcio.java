package excepcions;

public class ForaPeriodeInscripcio extends Exception{
    public ForaPeriodeInscripcio(){
        super("No es pot inscriure, avui no es periode d'inscripcio");
    }
}
