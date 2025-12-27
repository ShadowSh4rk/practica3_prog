package dades;

/**
 * 
 * Representa un estudiant matriculat a un ensenyament URV.
 *  
 */

public class Estudiant extends Usuari {
    private String ensenyament;
    private int anyInici;

    /**
     * 
     * @param alies identifica a l'usuari
     * @param adrecaCorreu nom de l'adreça del correu electrònic fins abans de l'@
     * @param ensenyament
     * @param anyInici
     */
    public Estudiant(String alies, String adrecaCorreu, String ensenyament, int anyInici) {
        super(alies, adrecaCorreu);
        this.ensenyament = ensenyament;
        this.anyInici = anyInici;
    }

    /**
     * 
     * @return retorna l'ensenyament de l'estudiant
     */
    public String getEnsenyament() {
        return ensenyament;
    }

    /**
     * 
     * @return retorna l'any d'inici dels estudis
     */
    public int getIniciEstudis() {
        return anyInici;
    }

}



