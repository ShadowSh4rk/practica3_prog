package proves_unitaries;

import dades.*;

public class LlistaUsuarisTest {

    public static void main(String[] args) {

        System.out.println("=== PROVES COMPLETES LlistaUsuaris ===");

        provaAfegir();
        provaBuscar();
    }

    public static void provaAfegir() {
        LlistaUsuaris llista = new LlistaUsuaris(5);
        llista.afegir(new Estudiant("marta", "marta.lopez", "GESST", 2022));

        if (llista.getNumUsuaris() == 1) {
            System.out.println("provaAfegir CORRECTE");
        } else {
            System.out.println("provaAfegir INCORRECTE");
        }
    }

    public static void provaBuscar() {
        LlistaUsuaris llista = new LlistaUsuaris(5);
        llista.afegir(new PDI("joan", "joan.perez", "DEIM", "Sescelades"));

        Usuari u = llista.buscarPerNom("joan");

        if (u != null && u.getAlies().equals("joan")) {
            System.out.println("provaBuscar CORRECTE");
        } else {
            System.out.println("provaBuscar INCORRECTE");
        }
    }
}
