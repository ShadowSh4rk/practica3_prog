package proves_unitaries;

import dades.Data;

public class DataTest {

    public static void main(String[] args) {

        System.out.println("=== PROVES CREACIÓ DATA ===");
        try {
            Data d1 = new Data(31, 1, 2026);
            System.out.println("Data correcta: " + d1);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            Data d2 = new Data(31, 4, 2026); // abril té 30 dies
            System.out.println("ERROR: data incorrecta creada!");
        } catch (IllegalArgumentException e) {
            System.out.println("Capturada excepció data incorrecta: " + e.getMessage());
        }

        try {
            Data d3 = new Data(29, 2, 2020); // any de traspàs
            System.out.println("Data traspàs correcta: " + d3);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            Data d4 = new Data(29, 2, 2021); // any normal
            System.out.println("ERROR: data incorrecta creada!");
        } catch (IllegalArgumentException e) {
            System.out.println("Capturada excepció data incorrecta: " + e.getMessage());
        }

        System.out.println("\n=== PROVES COMPARACIONS ===");
        Data d5 = new Data(1, 1, 2026);
        Data d6 = new Data(2, 1, 2026);
        System.out.println("d5 esAnterior d6? " + d5.esAnterior(d6)); // true
        System.out.println("d6 esPosterior d5? " + d6.esPosterior(d5)); // true
        System.out.println("d5 esIgual d5? " + d5.esIgual(d5)); // true
        System.out.println("d5 esIgual d6? " + d5.esIgual(d6)); // false

        System.out.println("\n=== PROVES AFEGIR DIES ===");
        Data d7 = new Data(30, 12, 2025);
        Data d8 = d7.afegirDies(5); // hauria de ser 4/1/2026
        System.out.println("30/12/2025 + 5 dies = " + d8);

        Data d9 = new Data(1, 3, 2021);
        Data d10 = d9.afegirDies(-1); // hauria de ser 28/2/2021
        System.out.println("1/3/2021 - 1 dia = " + d10);

        System.out.println("\n=== PROVES DIA DE LA SETMANA ===");
        Data d11 = new Data(5, 1, 2026); // Dilluns
        System.out.println(d11 + " és " + d11.getDiaSetmana());

        Data d12 = new Data(1, 1, 2022); // Dissabte
        System.out.println(d12 + " és " + d12.getDiaSetmana());

        System.out.println("\n=== PROVES MODIFICAR MES I ANY ===");
        Data d13 = new Data(31, 12, 2025);
        d13.modificarMes(1); // hauria de ser 31/1/2026
        System.out.println("31/12/2025 + 1 mes = " + d13);

        d13.modificarMes(-1); // hauria de ser 31/12/2025
        System.out.println("31/1/2026 - 1 mes = " + d13);

        d13.modificarAny(1); // 31/12/2026
        System.out.println("Incrementar any 1 = " + d13);

        d13.modificarAny(-2); // 31/12/2024
        System.out.println("Decrementar any 2 = " + d13);

        System.out.println("\n=== PROVES NOM MES I INDEX MES ===");
        System.out.println("Nom mes 1: " + Data.nomMes(1)); // Gener
        System.out.println("Nom mes 12: " + Data.nomMes(12)); // Desembre
        System.out.println("Index mes Març: " + Data.getIndexMes("Març")); // 3
        System.out.println("Index mes invalid: " + Data.getIndexMes("Fakes")); // -1

        System.out.println("\n=== PROVES DIES DEL MES ===");
        System.out.println("Febrer 2020 = " + Data.diesDelMes(2, 2020)); // 29
        System.out.println("Febrer 2021 = " + Data.diesDelMes(2, 2021)); // 28
        System.out.println("Abril 2021 = " + Data.diesDelMes(4, 2021)); // 30
        System.out.println("Gener 2021 = " + Data.diesDelMes(1, 2021)); // 31
    }
}
