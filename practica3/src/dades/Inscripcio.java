package dades;

public class Inscripcio implements Laos{
    private String nomActivitat;
    private String nomInscrit;
    private int dataInscripcio;
    private int nInscripcio; //per mantenir l'index!!

    //===============

    private Activitats activitat;

    public Activitats getActivitats() { //getter per activitat
        return activitat;
    }

    /**
     * constructor de la classe inscripcio
     * 
     * @param nomActivitat
     * @param nomInscrit
     * @param dataInscripcio
     * @param nInscripcio
     */
    public Inscripcio(String nomActivitat, String nomInscrit, int dataInscripcio, int nInscripcio) {
        this.nomActivitat = nomActivitat;
        this.nomInscrit = nomInscrit;
        this.dataInscripcio = dataInscripcio;
        this.nInscripcio = nInscripcio;
    }

    public Inscripcio copia(){
        Inscripcio duplicat = new Inscripcio(nomActivitat, nomInscrit, dataInscripcio, nInscripcio);
        return duplicat;
    }

    
}
