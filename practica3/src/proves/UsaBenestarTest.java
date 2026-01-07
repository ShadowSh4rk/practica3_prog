package proves;

import dades.*;
import excepcions.*;
import java.io.IOException;

public class UsaBenestarTest {

    public static void main(String[] args) {

        System.out.println("===== INICI JOC DE PROVES BENESTAR URV =====");

        // ===============================
        // TEST 0: DATA – CASOS LÍMITE
        // ===============================
        System.out.println("\n--- TEST 0: Classe Data (casos límit) ---");

        Data avui = new Data(5, 10, 2025); // dins període inscripció
        Data inici = new Data(1, 10, 2025);
        Data fi = new Data(8, 10, 2025);

        System.out.println("inici abans que fi? " + inici.esAnterior(fi));
        System.out.println("fi posterior a inici? " + fi.esPosterior(inici));
        System.out.println("avui dins [inici, fi]? " +
                (!avui.esAnterior(inici) && !avui.esPosterior(fi)));

        Data anyNou = new Data(31, 12, 2025).afegirDies(1);
        System.out.println("31/12/2025 + 1 dia = " +
                anyNou.getDia() + "/" + anyNou.getMes() + "/" + anyNou.getAny());

        // ===============================
        // TEST 1: USUARIS – PDI, PTGAS, ESTUDIANT
        // ===============================
        System.out.println("\n--- TEST 1: Usuaris ---");

        PDI pdi = new PDI("anna", "anna", "DEIM", "Sescelades");
        PTGAS ptgas = new PTGAS("joan", "joan", "Bellissens");
        Estudiant est = new Estudiant("marta", "marta", "GEI", 2023);

        System.out.println(pdi);
        System.out.println(ptgas);
        System.out.println(est);

        // ===============================
        // TEST 2: ACTIVITATS – CREACIÓ I TOSTRING
        // ===============================
        System.out.println("\n--- TEST 2: Activitats (creació i toString) ---");

        String[] totsCol = {"PDI", "PTGAS", "Estudiants"};
        String[] nomésPDI = {"PDI"};

        ActivitatUnDia unDia = new ActivitatUnDia(
                "Ioga",
                totsCol,
                inici,
                fi,
                new Data(12, 10, 2025),
                "10:00-12:00",
                "Tarragona",
                3,
                30.0
        );

        ActivitatPeriodica periodica = new ActivitatPeriodica(
                "Pilates",
                totsCol,
                inici,
                fi,
                "Dilluns",
                "18:00-20:00",
                new Data(15, 10, 2025),
                4,
                "CRAI",
                "Reus",
                2,
                50.0
        );

        ActivitatOnline online = new ActivitatOnline(
                "Mindfulness",
                totsCol,
                inici,
                fi,
                new Data(5, 10, 2025),
                30,
                "https://urv.cat/mindfulness"
        );

        ActivitatUnDia exclusivaPDI = new ActivitatUnDia(
                "Sessió PDI",
                nomésPDI,
                inici,
                fi,
                new Data(20, 10, 2025),
                "09:00-11:00",
                "Tarragona",
                5,
                10.0
        );

        System.out.println(unDia);
        System.out.println(periodica);
        System.out.println(online);
        System.out.println(exclusivaPDI);

        // ===============================
        // TEST 3: ACTIVITATS – MÈTODES GENERALS
        // ===============================
        System.out.println("\n--- TEST 3: Activitats (mètodes generals) ---");

        System.out.println("Ioga accepta PDI? " + unDia.acceptaColectiu("PDI"));
        System.out.println("Ioga accepta PAS? " + unDia.acceptaColectiu("PAS"));

        System.out.println("Ioga en període inscripció (avui=5/10)? " + unDia.esEnPeriodeInscripcio(avui));
        System.out.println("Pilates en període inscripció? " + periodica.esEnPeriodeInscripcio(avui));
        System.out.println("Mindfulness en període inscripció? " + online.esEnPeriodeInscripcio(avui));

        // ===============================
        // TEST 4: ESTAT D’ACTIVITATS (teClasseAvui, esActivaAvui, haAcabat)
        // ===============================
        System.out.println("\n--- TEST 4: Estat d'activitats ---");

        Data diaIoga = new Data(12, 10, 2025);
        System.out.println("Ioga te classe el 12/10? " + unDia.teClasseAvui(diaIoga));
        System.out.println("Ioga és activa el 12/10? " + unDia.esActivaAvui(diaIoga));
        System.out.println("Ioga ha acabat el 20/10? " + unDia.haAcabat(new Data(20, 10, 2025)));

        Data diaPilates = new Data(20, 10, 2025);
        System.out.println("Pilates te classe el 20/10? " + periodica.teClasseAvui(diaPilates));
        System.out.println("Pilates és activa el 20/10? " + periodica.esActivaAvui(diaPilates));
        System.out.println("Pilates ha acabat el 31/12? " + periodica.haAcabat(new Data(31, 12, 2025)));

        System.out.println("Mindfulness és activa el 10/10? " + online.esActivaAvui(new Data(10, 10, 2025)));
        System.out.println("Mindfulness ha acabat el 31/12? " + online.haAcabat(new Data(31, 12, 2025)));

        // ===============================
        // TEST 5: INSCRIPCIONS CORRECTES
        // ===============================
        System.out.println("\n--- TEST 5: Inscripcions correctes ---");

        try {
            unDia.afegir(new Inscripcio(unDia.getNom(), pdi.getAlies(), avui, pdi, unDia));
            unDia.afegir(new Inscripcio(unDia.getNom(), ptgas.getAlies(), avui, ptgas, unDia));
            unDia.afegir(new Inscripcio(unDia.getNom(), est.getAlies(), avui, est, unDia));

            periodica.afegir(new Inscripcio(periodica.getNom(), est.getAlies(), avui, est, periodica));
            online.afegir(new Inscripcio(online.getNom(), est.getAlies(), avui, est, online));

            System.out.println("Inscripcions realitzades correctament.");
        } catch (Exception e) {
            System.out.println("Error en inscripció: " + e.getClass().getSimpleName());
        }

        // ===============================
        // TEST 6: INSCRIPCIÓ FORA DE TERMINI
        // ===============================
        System.out.println("\n--- TEST 6: Inscripció fora de termini ---");

        Data dataFora = new Data(20, 10, 2025);

        try {
            unDia.afegir(new Inscripcio(unDia.getNom(), est.getAlies(), dataFora, est, unDia));
        } catch (ForaPeriodeInscripcio e) {
            System.out.println("Correcte: ForaPeriodeInscripcio capturada");
        } catch (Exception e) {
            System.out.println("Error inesperat: " + e.getClass().getSimpleName());
        }

        // ===============================
        // TEST 7: COL·LECTIU NO ACCEPTAT
        // ===============================
        System.out.println("\n--- TEST 7: Col·lectiu no acceptat ---");

        try {
            exclusivaPDI.afegir(new Inscripcio(exclusivaPDI.getNom(), est.getAlies(), avui, est, exclusivaPDI));
        } catch (NoAcceptaCol e) {
            System.out.println("Correcte: NoAcceptaCol capturada");
        } catch (Exception e) {
            System.out.println("Error inesperat: " + e.getClass().getSimpleName());
        }

        // ===============================
        // TEST 8: LLISTA D’ESPERA I NO QUEDEN PLACES
        // ===============================
        System.out.println("\n--- TEST 8: Llista d'espera i NoQuedenPlaces ---");

        ActivitatUnDia poquesPlaces = new ActivitatUnDia(
                "Taller reduït",
                totsCol,
                inici,
                fi,
                new Data(25, 10, 2025),
                "16:00-18:00",
                "Reus",
                2,
                5.0
        );

        try {
            poquesPlaces.afegir(new Inscripcio(poquesPlaces.getNom(), "u1", avui, pdi, poquesPlaces));
            poquesPlaces.afegir(new Inscripcio(poquesPlaces.getNom(), "u2", avui, ptgas, poquesPlaces));
            poquesPlaces.afegir(new Inscripcio(poquesPlaces.getNom(), "u3", avui, est, poquesPlaces)); // espera
            System.out.println("Correcte: llista d'espera creada (nIns=" +
                    poquesPlaces.getnIns() + ", nEsp=" + poquesPlaces.getnEsp() + ")");
        } catch (Exception e) {
            System.out.println("Error inesperat: " + e.getClass().getSimpleName());
        }

        // ===============================
        // TEST 9: CANCEL·LACIÓ I PAS DE LLISTA D’ESPERA
        // ===============================
        System.out.println("\n--- TEST 9: Cancel·lació i llista d'espera ---");

        System.out.println("Abans: nIns=" + poquesPlaces.getnIns() + ", nEsp=" + poquesPlaces.getnEsp());
        poquesPlaces.cancelar(poquesPlaces.llistaInscri[0]);
        System.out.println("Després: nIns=" + poquesPlaces.getnIns() + ", nEsp=" + poquesPlaces.getnEsp());

        // ===============================
        // TEST 10: VALORACIONS – CASOS LÍMIT
        // ===============================
        System.out.println("\n--- TEST 10: Valoracions ---");

        try {
            Data dataDespres = new Data(20, 10, 2025);

            Inscripcio i1 = unDia.obtenirInscripcioPerNom(pdi.getAlies());
            Inscripcio i2 = unDia.obtenirInscripcioPerNom(ptgas.getAlies());

            i1.assignarValoracio(8, dataDespres);
            i2.assignarValoracio(10, dataDespres);

            System.out.println("Valoració PDI: " + i1.getValoracio());
            System.out.println("Valoració PTGAS: " + i2.getValoracio());

            try {
                i1.assignarValoracio(-1, dataDespres);
            } catch (ForaDeRang e) {
                System.out.println("Correcte: ForaDeRang per valoració -1");
            }

            try {
                i2.assignarValoracio(11, dataDespres);
            } catch (ForaDeRang e) {
                System.out.println("Correcte: ForaDeRang per valoració 11");
            }

        } catch (Exception e) {
            System.out.println("Error en valoració: " + e.getClass().getSimpleName());
        }

        // ===============================
        // TEST 11: ESTAINSCRIT I BUSCAALIESINSCRIPCIO
        // ===============================
        System.out.println("\n--- TEST 11: estaInscrit i buscaAliesInscripcio ---");

        System.out.println("Anna està inscrita a Ioga? " + unDia.estaInscrit("anna"));
        System.out.println("Alias d'usuari 'anna' a Ioga: " + unDia.buscaAliesInscripcio("anna"));
        System.out.println("Usuari 'pep' està inscrit a Ioga? " + unDia.estaInscrit("pep"));

        // ===============================
        // TEST 12: LLISTA ACTIVITATS – AFEGIR, ELIMINAR, BAIXA AUTOMÀTICA
        // ===============================
        System.out.println("\n--- TEST 12: LlistaActivitats i baixa automàtica ---");

        LlistaActivitats llista = new LlistaActivitats(10);

        try {
            llista.afegir(unDia);
            llista.afegir(periodica);
            llista.afegir(online);
            llista.afegir(exclusivaPDI);
            llista.afegir(poquesPlaces);
        } catch (Exception e) {
            System.out.println("Error afegint activitats");
        }

        System.out.println("Nombre activitats inicials: " + llista.getNumActivitats());

        Data dataPosterior = new Data(20, 12, 2025);

        for (int i = llista.getNumActivitats() - 1; i >= 0; i--) {
            Activitats a = llista.getActivitat(i);
            boolean baixa = false;

            if (!a.esEnPeriodeInscripcio(dataPosterior)) {
                if (a.getTipus().equalsIgnoreCase("Online")) {
                    if (a.getnIns() < 20) baixa = true;
                } else {
                    double ocupacio = (double) a.getnIns() / a.getLimitPlaces() * 100;
                    if (ocupacio < 10) baixa = true;
                }
            }

            if (baixa) {
                System.out.println("Donant de baixa: " + a.getNom() + " (" + a.getTipus() + ")");
                try {
                    llista.eliminar(i);
                } catch (IOException e) {
                    System.out.println("Error eliminant activitat: " + e.getMessage());
                }
            }
        }

        System.out.println("Nombre activitats finals: " + llista.getNumActivitats());

        System.out.println("\n===== FI JOC DE PROVES =====");
    }
}
