package dades;
/**
 * 
 * Representa el personal docent i investigador.
 * 
 */

public class PDI extends Usuari {
    private String nomDepartament;
    private String campus;

    /**
     * 
     * @param alies identifica a l'usuari
     * @param adrecaCorreu nom de l'adreça del correu electrònic fins abans de l'@
     * @param nomDepartament el nom del departament on treballa
     * @param campus el campus on treballa
     */
    public PDI(String alies, String adrecaCorreu, String nomDepartament, String campus) {
        super(alies, adrecaCorreu);
        this.nomDepartament = nomDepartament;
        this.campus = campus;
    }

    /**
     * 
     * @return retorna el nom del departament
     */
    public String getNomDepartament() {
        return nomDepartament;
    }

    /**
     * 
     * @return retorna el nom del campus on treballa
    */
    public String getCampusTreballen() {
        return campus;
    }

    /**
     * toString: Mètode per mostrar la informació d'un usuari PDI (professor).
     * @return Cadena de text amb el contingut del professor.
     */
    @Override
    public String toString() {
        return (
            super.toString()+"\n"+  // Crida al toString() d'usuari
            "Tipus: PDI (Professorat)"+
            "Nom Departament: "+nomDepartament+
            "Campus Treball: "+campus
        );
    }
}
