package excepcions;

public class InscripcioNoTrobada extends Exception{
    public InscripcioNoTrobada(){
        super("No s'ha trobat la inscripcio a eliminar");
    }
}
