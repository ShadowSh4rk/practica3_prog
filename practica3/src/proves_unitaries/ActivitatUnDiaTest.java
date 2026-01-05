package proves_unitaries;

import dades.*;

/**
 * Proves unitàries de la classe ActivitatUnDia.
 * Es validen els comportaments principals, casos límit
 * i control d'errors segons els requeriments de l'enunciat.
 */
public class ActivitatUnDiaTest {

    public static void main(String[] args) {

        System.out.println("=== PROVES COMPLETES ActivitatUnDia ===");

        // Proves normals
        provaCreacio();
        provaTeClasseAvui();
        provaPreu();
        provaHaAcabat();

        // Proves de casos límit
        provaCasosLimits();

        // Proves de control d'errors
        provaErrors();
    }

    /** Prova la creació correcta d'una activitat d'un dia */
    private static void provaCreacio() {
        ActivitatUnDia act = new ActivitatUnDia(
                "Xerrada Java",
                new String[]{"Estudiants"},
                new Data(1, 1, 2026),
                new Data(10, 1, 2026),
                new Data(15, 1, 2026),
                "10:00-12:00",
                "Tarragona",
                20,
                5.0
        );

        if (act.getCiutat().equals("Tarragona") && act.getHorari().equals("10:00-12:00")) {
            System.out.println("provaCreacio CORRECTE");
        } else {
            System.out.println("provaCreacio INCORRECTE");
        }
    }

    /** Prova si teClasseAvui funciona correctament */
    private static void provaTeClasseAvui() {
        ActivitatUnDia act = new ActivitatUnDia(
                "Seminari",
                new String[]{"PDI"},
                new Data(1, 1, 2026),
                new Data(10, 1, 2026),
                new Data(20, 1, 2026),
                "18:00-19:00",
                "Reus",
                10,
                3.0
        );

        Data avui = new Data(20, 1, 2026);

        if (act.teClasseAvui(avui)) {
            System.out.println("provaTeClasseAvui CORRECTE");
        } else {
            System.out.println("provaTeClasseAvui INCORRECTE");
        }
    }

    /** Prova el retorn correcte del preu */
    private static void provaPreu() {
        ActivitatUnDia act = new ActivitatUnDia(
                "Taller",
                new String[]{"PTGAS"},
                new Data(1, 2, 2026),
                new Data(5, 2, 2026),
                new Data(7, 2, 2026),
                "09:00-11:00",
                "Valls",
                15,
                8.5
        );

        if (act.getPreu() == 8.5) {
            System.out.println("provaPreu CORRECTE");
        } else {
            System.out.println("provaPreu INCORRECTE");
        }
    }

    /** Prova si haAcabat detecta correctament una data posterior */
    private static void provaHaAcabat() {
        ActivitatUnDia act = new ActivitatUnDia(
                "Classe",
                new String[]{"Estudiants"},
                new Data(1, 3, 2026),
                new Data(10, 3, 2026),
                new Data(15, 3, 2026),
                "17:00-18:00",
                "Tarragona",
                10,
                6.0
        );

        Data avui = new Data(16, 3, 2026);

        if (act.haAcabat(avui)) {
            System.out.println("provaHaAcabat CORRECTE");
        } else {
            System.out.println("provaHaAcabat INCORRECTE");
        }
    }

    /** Proves de casos límit: preu 0, 0 places, dia igual a avui */
    private static void provaCasosLimits() {
        // Preu 0
        ActivitatUnDia actPreu0 = new ActivitatUnDia(
                "Gratis",
                new String[]{"PDI"},
                new Data(1, 2, 2026),
                new Data(5, 2, 2026),
                new Data(7, 2, 2026),
                "09:00-11:00",
                "Valls",
                10,
                0.0
        );

        if (actPreu0.getPreu() == 0.0) {
            System.out.println("provaPreu0 CORRECTE");
        } else {
            System.out.println("provaPreu0 INCORRECTE");
        }

        // Dia de la classe igual a avui
        Data avui = new Data(7, 2, 2026);
        if (actPreu0.teClasseAvui(avui)) {
            System.out.println("provaClasseAvui Igual CORRECTE");
        } else {
            System.out.println("provaClasseAvui Igual INCORRECTE");
        }

        // Limit places 0
        ActivitatUnDia act0Places = new ActivitatUnDia(
                "Taller limitat",
                new String[]{"PTGAS"},
                new Data(1, 3, 2026),
                new Data(5, 3, 2026),
                new Data(7, 3, 2026),
                "10:00-12:00",
                "Reus",
                0,
                10.0
        );

        if (act0Places.getLimitPlaces() == 0) {
            System.out.println("prova0Places CORRECTE");
        } else {
            System.out.println("prova0Places INCORRECTE");
        }
    }

    /** Proves de control d'errors: dates invàlides, col·lectius null */
    private static void provaErrors() {
        // Data invàlida
        try {
            new Data(32, 1, 2026);
            System.out.println("provaDataInv INCORRECTE - hauria d'haver fallat");
        } catch (Exception e) {
            System.out.println("provaDataInv CORRECTE - capturada excepció: " + e.getMessage());
        }

        // Col·lectius null
        try {
            new ActivitatUnDia(
                    "Error",
                    null,
                    new Data(1, 1, 2026),
                    new Data(10, 1, 2026),
                    new Data(15, 1, 2026),
                    "10:00-12:00",
                    "Tarragona",
                    20,
                    5.0
            );
            System.out.println("provaCollectiusNull INCORRECTE - hauria d'haver fallat");
        } catch (Exception e) {
            System.out.println("provaCollectiusNull CORRECTE - capturada excepció: " + e.getMessage());
        }

    try {
    ActivitatUnDia actErrorDates = new ActivitatUnDia(
            "ErrorDates",
            new String[]{"Estudiants"},
            new Data(10, 2, 2026),   // dataIniciInscripcio
            new Data(5, 2, 2026),    // dataFiInscripcio (anterior a la d’inici)
            new Data(15, 2, 2026),
            "09:00-11:00",
            "Tarragona",
            10,
            5.0
    );
    System.out.println("provaFiIniciError INCORRECTE - hauria d'haver fallat");
} catch (Exception e) {
    System.out.println("provaFiIniciError CORRECTE - capturada excepció: " + e.getMessage());
}

ActivitatUnDia actForaPeriode = new ActivitatUnDia(
        "ClasseForaPeriode",
        new String[]{"PDI"},
        new Data(1, 3, 2026),   // dataIniciInscripcio
        new Data(10, 3, 2026),  // dataFiInscripcio
        new Data(15, 3, 2026),  // data de la classe fora del període d’inscripció
        "10:00-12:00",
        "Reus",
        10,
        5.0
);

    Data avui = new Data(15, 3, 2026);

    // Comprovem teClasseAvui encara que la data estigui fora del període d’inscripció
    if(actForaPeriode.teClasseAvui(avui)) {
        System.out.println("provaClasseForaPeriode CORRECTE - detecta classe encara que fora del període");
    } else {
        System.out.println("provaClasseForaPeriode INCORRECTE");
    }
}
}
