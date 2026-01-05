package proves_unitaries;

import dades.*;

/**
 * Proves unitàries de la classe ActivitatPeriodica.
 * Es validen els comportaments principals, casos límit
 * i control d'errors segons els requeriments de l'enunciat.
 */
public class ActivitatPeriodicaTest {

    public static void main(String[] args) {

        System.out.println("=== PROVES COMPLETES ActivitatPeriodica ===");

        // Proves normals
        provaCreacio();
        provaTeClasseAvui();
        provaEsActivaAvui();
        provaPreu();
        provaHaAcabat();

        // Proves de casos límit
        provaCasosLimits();

        // Proves de control d'errors
        provaErrors();
    }

    /** Prova la creació correcta d'una activitat periòdica */
    private static void provaCreacio() {
        ActivitatPeriodica act = new ActivitatPeriodica(
                "Ioga",
                new String[]{"PDI"},
                new Data(1, 1, 2026),
                new Data(15, 1, 2026),
                "Dilluns",
                "18:00-19:00",
                new Data(20, 1, 2026),
                4,
                "Centre Esportiu",
                "Tarragona",
                20,
                40.0
        );

        if (act.getCentre().equals("Centre Esportiu") &&
            act.getCiutat().equals("Tarragona") &&
            act.getNumSetmanes() == 4) {
            System.out.println("provaCreacio CORRECTE");
        } else {
            System.out.println("provaCreacio INCORRECTE");
        }
    }

    /** Prova si teClasseAvui funciona correctament en el dia correcte */
    private static void provaTeClasseAvui() {
        ActivitatPeriodica act = new ActivitatPeriodica(
                "Pilates",
                new String[]{"Estudiants"},
                new Data(1, 2, 2026),
                new Data(10, 2, 2026),
                "Dimarts",
                "10:00-11:00",
                new Data(3, 2, 2026), // Dimarts
                3,
                "Campus",
                "Reus",
                15,
                30.0
        );

        Data avui = new Data(10, 2, 2026); // Dimarts

        if (act.teClasseAvui(avui)) {
            System.out.println("provaTeClasseAvui CORRECTE");
        } else {
            System.out.println("provaTeClasseAvui INCORRECTE");
        }
    }

    /** Prova si esActivaAvui detecta correctament una data dins del període */
    private static void provaEsActivaAvui() {
        ActivitatPeriodica act = new ActivitatPeriodica(
                "Natació",
                new String[]{"PTGAS"},
                new Data(1, 3, 2026),
                new Data(10, 3, 2026),
                "Dimecres",
                "09:00-10:00",
                new Data(11, 3, 2026),
                5,
                "Piscina",
                "Valls",
                10,
                50.0
        );

        Data avui = new Data(25, 3, 2026);

        if (act.esActivaAvui(avui)) {
            System.out.println("provaEsActivaAvui CORRECTE");
        } else {
            System.out.println("provaEsActivaAvui INCORRECTE");
        }
    }

    /** Prova el retorn correcte del preu total */
    private static void provaPreu() {
        ActivitatPeriodica act = new ActivitatPeriodica(
                "Teatre",
                new String[]{"PDI"},
                new Data(1, 4, 2026),
                new Data(10, 4, 2026),
                "Dijous",
                "17:00-19:00",
                new Data(16, 4, 2026),
                2,
                "Auditori",
                "Tarragona",
                25,
                60.0
        );

        if (act.getPreu() == 60.0) {
            System.out.println("provaPreu CORRECTE");
        } else {
            System.out.println("provaPreu INCORRECTE");
        }
    }

    /** Prova si haAcabat detecta correctament el final de l'activitat */
    private static void provaHaAcabat() {
        ActivitatPeriodica act = new ActivitatPeriodica(
                "Pintura",
                new String[]{"Estudiants"},
                new Data(1, 5, 2026),
                new Data(10, 5, 2026),
                "Divendres",
                "16:00-18:00",
                new Data(15, 5, 2026),
                2,
                "Aula 3",
                "Reus",
                12,
                35.0
        );

        Data avui = new Data(1, 6, 2026);

        if (act.haAcabat(avui)) {
            System.out.println("provaHaAcabat CORRECTE");
        } else {
            System.out.println("provaHaAcabat INCORRECTE");
        }
    }

    /** Proves de casos límit: 0 setmanes, preu 0 */
    private static void provaCasosLimits() {
        // 0 setmanes
        ActivitatPeriodica act0Setmanes = new ActivitatPeriodica(
                "Cas limit",
                new String[]{"PDI"},
                new Data(1, 6, 2026),
                new Data(10, 6, 2026),
                "Dilluns",
                "10:00-11:00",
                new Data(15, 6, 2026),
                0,
                "Centre",
                "Tarragona",
                10,
                0.0
        );

        if (act0Setmanes.getNumSetmanes() == 0) {
            System.out.println("prova0Setmanes CORRECTE");
        } else {
            System.out.println("prova0Setmanes INCORRECTE");
        }

        if (act0Setmanes.getPreu() == 0.0) {
            System.out.println("provaPreu0 CORRECTE");
        } else {
            System.out.println("provaPreu0 INCORRECTE");
        }
    }

    /** Proves de control d'errors */
    private static void provaErrors() {
        // Data fi inscripció anterior a inici
        try {
            new ActivitatPeriodica(
                    "Error dates",
                    new String[]{"PDI"},
                    new Data(10, 1, 2026),
                    new Data(1, 1, 2026),
                    "Dilluns",
                    "10:00-11:00",
                    new Data(20, 1, 2026),
                    3,
                    "Centre",
                    "Reus",
                    10,
                    20.0
            );
            System.out.println("provaFiIniciError INCORRECTE - hauria d'haver fallat");
        } catch (Exception e) {
            System.out.println("provaFiIniciError CORRECTE - capturada excepció: " + e.getMessage());
        }
    }
}
