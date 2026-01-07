package excepcions;

public class LlistaInscripcionsBuida extends Exception{
    public LlistaInscripcionsBuida(){
        super("No es pot cancelar cap inscripcio, la llista esta buida");
    }
}
