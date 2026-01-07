package dades;
/**
 * Representa un usuari registrat que pot apuntar-se a activitats.
 * 
 * Atributs comuns:
 *  - alies identificador
 *  - correu institucional (fins abans de la @)
 *
 *  Només existeixen usuaris dels tipus PDI, PTGAS i Estudiant.
 */


public abstract class Usuari {
    protected String alies;
    protected String adrecaCorreu; 
    private String colectiu; // PDI, PTGAS, Estudiants

    /**
     * 
     * @param alies identifica a l'usuari.
     * @param adrecaCorreu nom de l'adreça del correu electrònic fins abans de l'@
     */
    public Usuari(String alies, String adrecaCorreu, String colectiu) {
        this.alies = alies;
        this.adrecaCorreu = adrecaCorreu;
        this.colectiu = colectiu;
    }

    /**
     * 
     * @return el alies que identifica a l'usuari
     */
    public String getAlies() {
        return alies;
    }

    /**
     * 
     * @return la adreça del correu electrònic fins abans de l'@
    */
    public String getCorreu() {
        return adrecaCorreu;
    }

    /**
     * 
     * @return colectiu
     */
    public String getColectiu() {
        return colectiu;
    }

    /**
     * toString: Mètode per mostrar la informació bàsica d'un usuari.
     * @return Cadena de text amb el contingut de l'usuari.
     */
    public String toString() {
        return (
            "Usuari: "+alies+"\n"+
            "Adreça: "+adrecaCorreu
        );
    }
}
