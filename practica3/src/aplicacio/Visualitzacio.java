package aplicacio;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import dades.*;
import aplicacio.Menu;


/**
 * Classe amb la implementació gràfica del Programa de Benestar URV
 */
public class Visualitzacio extends JFrame {
    private JPanel panellPrincipal;             // Panell per a gestionar la distribució de la pantalla
    private JPanel panellAny;                   // Panell que conté el contingut dels mesos de l'any
    private JPanel panellMes;                   // Panell que conté el contingut dels dies del mes
    private JPanel panellSuperior;              // Panell que conté el contingut de la capçalera superior
    private JButton botoEsquerra, botoDreta;    // Botons que permeten passar al any anterior o seguent
    private JButton[][] botonsAny;              // Botons que permeten clicar els mesos
    private JButton[][] botonsMes;              // Botons que permeten clicar els dies
    private JLabel etiquetaSuperior;            // Text que indica l'any, mes i/o dia en el que ens trobem
    private int anyActual;  // Any seleccionat a la finestra
    private int mesActual;  // Mes seleccionat a la finestra (1: Gener i 12: Desembre)

    private static LlistaActivitats llistaAct = new LlistaActivitats(10);


    /**
     * Mètode constructor per a crear una finestra de visualització de les activitats
     * - Primer s'ha de seleccionar un any inicial
     * - Després, es visualitzarà a l'esquerra els mesos de l'any seleccionat amb la possibilitat d'incrementar o decrementar l'any
     * - En clicar un mes, es visualitzarà a la dreta els dies del mes seleccionat
     */
    public Visualitzacio(String titol, String any) {
        super(titol);   // Assignem el títol de la finestra
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);   // Per a poder tancar la finestra
        this.setSize(1000, 400);          // Tamany de la finestra
        this.setLocation(getWidth()/2, getHeight()/2);  // Centrar la finestra al centre de la pantalla
        this.setLayout(new BorderLayout());             // Disposició dels elements en les direccions cardinals

        this.anyActual = Integer.parseInt(any);         // Convertim l'any a un enter
        this.mesActual = 1;

        crearPanellSuperior();      // Creem la capçalera superior
        crearPanellAny();           // Creem la vista dels mesos de l'any
        crearPanellMes();           // Creem la vista dels dies del mes

        // Crear un panell per gestionar la distribució de la pantalla
        panellPrincipal = new JPanel(new GridLayout(1, 2, 10, 10));

        // Afegim els diversos panells a la pantalla
        panellPrincipal.add(panellAny);     // Afegim el panell dels mesos de l'any a l'esquerra
        panellPrincipal.add(panellMes);     // Afegim el panell dels dies del mes a la dreta

        // Afegir a la finestra
        this.add(panellSuperior, BorderLayout.NORTH);   // Capçalera superior en direcció NORD
        this.add(panellPrincipal, BorderLayout.CENTER); // Panell principal centrada

        this.setVisible(true);
    }


    // MÉTODES AUXILIARS PER A GESTIONAR LA FINESTRA
    /**
     * Mètode per a crear la capçalera superior de la finestra, composada de 3 elements:
     * - Botó a l'esquerra que ens permet decrementar l'any seleccionat
     * - Etiqueta amb el any, mes i/o dia seleccionat
     * - Botó a la dreta que ens permet incrementar l'any seleccionat
     * 
     * To Do: Que fem amb això quan tenim seleccionat un mes o un dia?
     */
    private void crearPanellSuperior() {
        // Crear panell superior que conté botons per modificar l'any i l'etiqueta de l'any
        panellSuperior = new JPanel();
        panellSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Etiqueta de l'any, mes i/o dia seleccionat
        etiquetaSuperior = new JLabel("Any: "+anyActual);
        etiquetaSuperior.setFont(new Font("Calibri", Font.BOLD, 24));
        etiquetaSuperior.setForeground(Color.BLACK);

        // Botó de l'esquerra per a decrementar l'any
        botoEsquerra = new JButton("←");
        botoEsquerra.setFont(new Font("Calibri", Font.BOLD, 24));
        botoEsquerra.addActionListener(new AccioBotonsAny(this, false));

        // Botó de la dreta per a incrementar l'any 
        botoDreta = new JButton("→");
        botoDreta.setFont(new Font("Calibri", Font.BOLD, 24));
        botoDreta.addActionListener(new AccioBotonsAny(this, true));

        // Afegim el botó de l'esquerra, l'etiqueta i el botó de la dreta
        panellSuperior.add(botoEsquerra);
        panellSuperior.add(etiquetaSuperior);
        panellSuperior.add(botoDreta);
    }

    /**
     * Mètode per a crear el panell de visualització dels mesos de l'any seleccionat
     */
    private void crearPanellAny() {
        // Crear un panell per als mesos
        botonsAny = new JButton[4][3];      // Panell que permet interactuar amb els mesos
        panellAny = new JPanel();           // Panell que permet visualitzar els mesos
        panellAny.setLayout(new GridLayout(4, 3, 10, 10));
        panellAny.setBounds(50, 50, 400, 200);

        // Creació dels botons per a cada mes
        int index = 1;  // Index del mes
        for (int i = 0; i < 4; i++) {   // Files
            for (int j = 0; j < 3; j++) {   // Columnes
                botonsAny[i][j] = new JButton(Data.nomMes(3*i + j + 1));                // Creem botó amb el nom del mes corresponent
                botonsAny[i][j].addActionListener(new AccioBotonsMes(this, index));     // Afegim la interactivitat del botó
                panellAny.add(botonsAny[i][j]);                                         // Afegim el botó al panell
                index++;    // Incrementem l'index de mes
            }
        }
    }

    /**
     * Mètode per a crear el panell de visualització dels dies, inicialment espera a que es seleccioni un mes
     */
    private void crearPanellMes() {
        // Inicialment no creem la visualització dels dies, només esperem
        panellMes = new JPanel(new BorderLayout());
        panellMes.add(new JLabel("Selecciona un mes", JLabel.CENTER));
    }


    // MÉTODES CREATS PER GESTIONAR FINESTRA DES DE BOTONS
    /**
     * Mètode per a:
     * - Incrementar l'any si hem apretat el botó dret de la capçalera superior.
     * - Decrementar l'any si hem apretat el botó esquerra de la capçalera superior.
     * @param esIncrement boolean que indica si es botó dret o no
     */
    public void actualitzarAny(boolean esIncrement) {
        if (esIncrement) anyActual++;
        else anyActual--;
        etiquetaSuperior.setText("Any: "+anyActual);    // Actualitzem text de la capçalera
    }

    /**
     * Mètode per a afegir el mes seleccionat a l'etiqueta de la capçalera superior, també actualitza la variable mesActual.
     * @param mes index del mes seleccionat
     */
    public void afegirMes(int mes) {
        this.mesActual = mes;
        etiquetaSuperior.setText("Any: "+anyActual+" - Mes: "+Data.nomMes(mes));
    }

    /**
     * Mètode que actualitzarà la visualització del panell de Mes per a visualitzar els dies del mes seleccionat
     */
    public void visualitzarMes() {
        panellPrincipal.remove(panellMes);  // Eliminem el panell Mes anterior

        int diesMes = Data.diesDelMes(mesActual, anyActual);    // Càlcul del nombre de dies total que té el mes seleccionat
        Data data_ini = new Data(1, mesActual, anyActual);  // Variable Data amb el valor del primer dia del mes
        String dia_ini = data_ini.getDiaSetmana();              // Mitjançant la funció obtenim el nom del dia de la setmana en que comença el mes  

        int offset_ini = 0;
        switch (dia_ini) {
            case "Dimarts": offset_ini = 1;
                break;
            case "Dimecres": offset_ini = 2;
                break;
            case "Dijous": offset_ini = 3;
                break;
            case "Divendres": offset_ini = 4;
                break;
            case "Dissabte": offset_ini = 5;
                break;
            case "Diumenge": offset_ini = 6;
                break;
            default: break;

        }

        int n_columnes = 7; // Nombre de columnes
        int n_files = 6;    // Nombre de files

        panellMes = new JPanel(new GridLayout(n_files, n_columnes, 10, 10));

        botonsMes = new JButton[n_files][n_columnes];

        int dia = 1;
        int offset_botons = 0;
        for (int i = 0; i < n_files; i++) {
            for (int j = 0; j < n_columnes; j++) {
                if ((offset_botons < offset_ini) || (dia > diesMes)) {
                    botonsMes[i][j] = new JButton(" ");
                    offset_botons++;
                }
                else if (dia <= diesMes) {
                    botonsMes[i][j] = new JButton(Integer.toString(dia));
                    botonsMes[i][j].setFont(new Font("Calibri", Font.BOLD, 24));
                    Data dataDia = new Data(dia, mesActual, anyActual);
                    if (llistaAct.hiHaActivitat(dataDia)) {
                        botonsMes[i][j].setBackground(Color.CYAN);
		                botonsMes[i][j].setOpaque(true);
                    }
                    dia++;
                }
                panellMes.add(botonsMes[i][j]);
            }
        }

        panellPrincipal.add(panellMes);
    }


    // MAIN
    /**
     * Aplicació amb entrada/sortida gràfica.
     * - L'aplicació carrega la mateixa informació dels fitxers que es fa servir en la versió per consola (FALTA AMBDUES).
     * - Primer es selecciona l'any que es vol visualitzar.
     * - Després es selecciona el mes que es vol visualitzar.
     * - Sobre la vista tipus calendari es mostrarà els dies que hi ha activitats.
     * - Al seleccionar un dia, es mostrarà el detall de les activitats del dia.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        String[] miaus = new String[2];
        ActivitatUnDia aud = new ActivitatUnDia("Yoga", miaus, new Data(11, 9, 2001), new Data(22, 7, 2004), new Data(22, 10, 2025), "Barcelona", 8, 1.99);
        ActivitatOnline ao = new ActivitatOnline("Classe Virtual", miaus, new Data(11, 9, 2001), new Data(5, 5, 2005), new Data(1, 1, 2026), 20, "https://meet.com/miaumiaumiau");
        ActivitatPeriodica ap = new ActivitatPeriodica("Classes de Cuina", miaus, new Data(7, 11, 1917), new Data(30, 12, 1922), "Dilluns", new Data(2, 2, 2026), 3, "Sescelades", "Tarragona", 5, 4.98);
        llistaAct.afegir(aud);
        llistaAct.afegir(ao);
        llistaAct.afegir(ap);
        System.out.println("DEBUG: Activitats carregades a la llista: " + llistaAct.getNumActivitats());

        // Per a començar el codi, primer esperem a que l'usuari indiqui un any valid per a visualitzar.
        String any = JOptionPane.showInputDialog("Indica l'any a visualitzar");
        while ((any == null) || (any.equals(""))) {
            JOptionPane.showMessageDialog(null, "Cal un valor vàlid!", "ERROR", JOptionPane.ERROR_MESSAGE);
            any = JOptionPane.showInputDialog("Indica l'any a visualitzar");
        }
        new Visualitzacio("Calendari", any);
    }
}