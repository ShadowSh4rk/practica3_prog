package proves_unitaries;

import dades.*;
import excepcions.ActivitatInexistentException;
import java.io.IOException;

public class LlistaActivitatsTest {

    public static void main(String[] args) {
        System.out.println("=== PROVES COMPLETES LlistaActivitats ===");

        provaAfegirIOrdenar();
        provaEliminar();
        provaBuscarIObtenir();
        provaActivitatsEnInscripcio();
        provaActivitatsActives();
        provaActivitatsAmbClasse();
        provaActivitatsAmbPlaces();
        provaHiHaActivitat();
        provaColectius();
    }

    /** Prova afegir activitats i mantenir ordre alfabètic */
    private static void provaAfegirIOrdenar() {
        try {
            LlistaActivitats llista = new LlistaActivitats(5);
            ActivitatUnDia a1 = new ActivitatUnDia("Xerrada Java", new String[]{"Estudiants"},
                    new Data(1,1,2026), new Data(10,1,2026),
                    new Data(15,1,2026), "10:00-12:00","Tarragona", 20, 5.0);

            ActivitatUnDia a2 = new ActivitatUnDia("Algoritmes", new String[]{"PDI"},
                    new Data(1,2,2026), new Data(5,2,2026),
                    new Data(7,2,2026), "09:00-11:00", "Reus", 15, 0);

            llista.afegir(a1);
            llista.afegir(a2);

            if (llista.getActivitat(0).getNom().equals("Algoritmes") &&
                llista.getActivitat(1).getNom().equals("Xerrada Java")) {
                System.out.println("provaAfegirIOrdenar CORRECTE");
            } else {
                System.out.println("provaAfegirIOrdenar INCORRECTE");
            }
        } catch (IOException e) {
            System.out.println("provaAfegirIOrdenar INCORRECTE - " + e.getMessage());
        }
    }

    /** Prova eliminar activitats */
    private static void provaEliminar() {
        try {
            LlistaActivitats llista = new LlistaActivitats(2);
            ActivitatUnDia a1 = new ActivitatUnDia("Xerrada", new String[]{"Estudiants"},
                    new Data(1,1,2026), new Data(10,1,2026),
                    new Data(15,1,2026), "10:00-12:00","Tarragona", 20, 5.0);
            llista.afegir(a1);
            llista.eliminar(0);

            if (llista.getNumActivitats() == 0) {
                System.out.println("provaEliminar CORRECTE");
            } else {
                System.out.println("provaEliminar INCORRECTE");
            }
        } catch (IOException e) {
            System.out.println("provaEliminar INCORRECTE - " + e.getMessage());
        }
    }

    /** Prova buscarPerNom i obtenirPerNom amb excepció */
    private static void provaBuscarIObtenir() {
        try {
            LlistaActivitats llista = new LlistaActivitats(1);
            ActivitatUnDia a1 = new ActivitatUnDia("Java", new String[]{"Estudiants"},
                    new Data(1,1,2026), new Data(10,1,2026),
                    new Data(15,1,2026), "10:00-12:00","Tarragona", 20, 5.0);
            llista.afegir(a1);

            if (llista.buscarPerNom("Java") != null) {
                System.out.println("provaBuscarPerNom CORRECTE");
            }

            try {
                llista.obtenirPerNom("Python");
                System.out.println("provaObtenirPerNom INCORRECTE - hauria d'haver llençat excepció");
            } catch (ActivitatInexistentException e) {
                System.out.println("provaObtenirPerNom CORRECTE - capturada excepció: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("provaBuscarIObtenir INCORRECTE - " + e.getMessage());
        }
    }

    /** Prova activitats en període d'inscripció */
    private static void provaActivitatsEnInscripcio() {
        try {
            LlistaActivitats llista = new LlistaActivitats(1);
            ActivitatUnDia a1 = new ActivitatUnDia("Test", new String[]{"Estudiants"},
                    new Data(1,1,2026), new Data(10,1,2026),
                    new Data(15,1,2026), "10:00-12:00","Tarragona", 20, 5.0);
            llista.afegir(a1);

            Data avui = new Data(5,1,2026);
            Activitats[] res = llista.activitatsEnInscripcio(avui);
            if (res[0] != null && res[0].getNom().equals("Test")) {
                System.out.println("provaActivitatsEnInscripcio CORRECTE");
            }
        } catch (IOException e) {
            System.out.println("provaActivitatsEnInscripcio INCORRECTE - " + e.getMessage());
        }
    }

    /** Prova activitats actives avui */
    private static void provaActivitatsActives() {
        try {
            LlistaActivitats llista = new LlistaActivitats(1);
            ActivitatUnDia a1 = new ActivitatUnDia("TestActiva", new String[]{"Estudiants"},
                    new Data(1,1,2026), new Data(10,1,2026),
                    new Data(15,1,2026), "10:00-12:00","Tarragona", 20, 5.0);
            llista.afegir(a1);

            Data avui = new Data(15,1,2026);
            Activitats[] res = llista.activitatsActivesAvui(avui);
            if (res[0] != null && res[0].getNom().equals("TestActiva")) {
                System.out.println("provaActivitatsActives CORRECTE");
            }
        } catch (IOException e) {
            System.out.println("provaActivitatsActives INCORRECTE - " + e.getMessage());
        }
    }

    /** Prova activitats amb classe avui */
    private static void provaActivitatsAmbClasse() {
        try {
            LlistaActivitats llista = new LlistaActivitats(1);
            ActivitatUnDia a1 = new ActivitatUnDia("Classe", new String[]{"Estudiants"},
                    new Data(1,1,2026), new Data(10,1,2026),
                    new Data(15,1,2026), "10:00-12:00","Tarragona", 20, 5.0);
            llista.afegir(a1);

            Data avui = new Data(15,1,2026);
            Activitats[] res = llista.activitatsAmbClasseAvui(avui);
            if (res[0] != null && res[0].getNom().equals("Classe")) {
                System.out.println("provaActivitatsAmbClasse CORRECTE");
            }
        } catch (IOException e) {
            System.out.println("provaActivitatsAmbClasse INCORRECTE - " + e.getMessage());
        }
    }

    /** Prova activitats amb places disponibles */
    private static void provaActivitatsAmbPlaces() {
        try {
            LlistaActivitats llista = new LlistaActivitats(1);
            ActivitatUnDia a1 = new ActivitatUnDia("Taller", new String[]{"Estudiants"},
                    new Data(1,1,2026), new Data(10,1,2026),
                    new Data(15,1,2026), "10:00-12:00","Tarragona", 0, 5.0);
            llista.afegir(a1);

            Activitats[] res = llista.activitatsAmbPlaces();
            if (res[0] == null) {
                System.out.println("provaActivitatsAmbPlaces CORRECTE");
            }
        } catch (IOException e) {
            System.out.println("provaActivitatsAmbPlaces INCORRECTE - " + e.getMessage());
        }
    }

    /** Prova hiHaActivitat() amb diferents tipus d'activitat */
    private static void provaHiHaActivitat() {
        try {
            LlistaActivitats llista = new LlistaActivitats(3);
            ActivitatUnDia a1 = new ActivitatUnDia("Xerrada", new String[]{"Estudiants"},
                    new Data(1,1,2026), new Data(10,1,2026),
                    new Data(15,1,2026), "10:00-12:00","Tarragona", 20, 5.0);
            ActivitatPeriodica ap = new ActivitatPeriodica("Seminari", new String[]{"PDI"},
                    new Data(1,1,2026), new Data(10,1,2026),
                    "Dilluns", "18:00-20:00", new Data(15,1,2026), 2, "Centre1", "Reus", 10, 10.0);
            ActivitatOnline ao = new ActivitatOnline("Online", new String[]{"Joves"},
                    new Data(1,1,2026), new Data(10,1,2026),
                    new Data(15,1,2026), 10, "link");

            llista.afegir(a1);
            llista.afegir(ap);
            llista.afegir(ao);

            Data avui = new Data(15,1,2026);
            if (llista.hiHaActivitat(avui)) {
                System.out.println("provaHiHaActivitat CORRECTE");
            }
        } catch (IOException e) {
            System.out.println("provaHiHaActivitat INCORRECTE - " + e.getMessage());
        }
    }

/**Prova de colectius no acceptats / acceptats*/

private static void provaColectius() {
    System.out.println("=== PROVA COL·LECTIUS ===");

    String[] colectius = {"Estudiants", "PDI"};
    Data iniciInscripcio = new Data(1, 1, 2026);
    Data fiInscripcio = new Data(10, 1, 2026);
    ActivitatUnDia act = new ActivitatUnDia(
            "Xerrada Java",
            colectius,
            iniciInscripcio,
            fiInscripcio,
            new Data(5, 1, 2026),
            "10:00-12:00",
            "Tarragona",
            20,
            5.0
    );

    // Col·lectiu acceptat
    if (act.acceptaColectiu("PDI")) {
        System.out.println("Col·lectiu PDI acceptat CORRECTE");
    } else {
        System.out.println("Col·lectiu PDI acceptat INCORRECTE");
    }

    // Col·lectiu NO acceptat
    if (!act.acceptaColectiu("PTGAS")) {
        System.out.println("Col·lectiu PTGAS no acceptat CORRECTE");
    } else {
        System.out.println("Col·lectiu PTGAS no acceptat INCORRECTE");
    }

    }
}
