package dades;
/**
 * 
 * Representa el personal tècnic i de gestió
 */

public class PTGAS extends Usuari {
    private String campus;

    /**
     * Constructor
     * @param alies identifica a l'usuari
     * @param adrecaCorreu nom de l'adreça del correu electrònic fins abans de l'@
     * @param campus el campus on treballa
     * 
     */
    public PTGAS(String alies, String adrecaCorreu, String campus) {
        super(alies, adrecaCorreu);
        this.campus = campus;
    }

    /**
     * 
     * @return retorna el nom del campus on treballa
     */
    public String getCampusTreballen() {
        return campus;
    }

}
