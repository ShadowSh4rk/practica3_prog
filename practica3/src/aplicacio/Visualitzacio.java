// PAQUET
package aplicacio;


// IMPORTS JAVA
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// IMPORTS PAQUETS LOCALS
import dades.*;


/**
 * Classe amb la implementació gràfica del Programa de Benestar URV
 */
public class Visualitzacio extends JFrame {
    private JPanel panellPrincipal;                 // Panell per a gestionar la distribució de la pantalla
    private JPanel panellAny;                       // Panell que conté el contingut dels mesos de l'any
    private JPanel panellMes;                       // Panell que conté el contingut dels dies del mes
    private JPanel panellSuperior;                  // Panell que conté el contingut superior
    private JPanel panellCapçalera;                 // Panell que conté els botons per a canviar la data en 1 unitat i la etiqueta
    private JButton botoEsquerraAny, botoDretaAny;  // Botons que permeten passar al any anterior o seguent
    private JButton botoEsquerraMes, botoDretaMes;  // Botons que permeten passar al mes anterior o seguent
    private JButton botoData;                       // Botó que permet obrir una finestra en la que fixar una nova data a visualitzar
    private JButton botoPlaceholder;                // Botó temporal?
    private JButton[][] botonsAny;                  // Botons que permeten clicar els mesos
    private JButton[][] botonsMes;                  // Botons que permeten clicar els dies
    private JLabel etiquetaSuperior;                // Text que indica l'any, mes i/o dia en el que ens trobem
    private Data dataActual = new Data(0);      // Data seleccionada a la finestra

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

        this.dataActual.setAny(Integer.parseInt(any));  // Convertim l'any a un enter
        this.dataActual.setMes(0);

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
     * Mètode per a crear la capçalera superior de la finestra, composada de 7 elements:
     * - Botó que ens permet fixar una nova data a visualitzar
     * - Botó a l'esquerra que ens permet decrementar l'any seleccionat
     * - Botó a l'esquerra que ens permet decrementar el mes seleccionat
     * - Etiqueta amb el any, mes i/o dia seleccionat
     * - Botó a la dreta que ens permet incrementar el mes seleccionat
     * - Botó a la dreta que ens permet incrementar l'any seleccionat
     * - Botó temporal
     * 
     * To Do: Que fem amb això quan tenim seleccionat un mes o un dia?
     */
    private void crearPanellSuperior() {
        // Crear panell superior que conté botons per modificar l'any i l'etiqueta de l'any
        panellSuperior = new JPanel(new BorderLayout());

        botoData = new JButton("Data");         // Botó per a fixar una nova data a visualitzar
        botoPlaceholder = new JButton("aux");   // Botó temporal

        botoData.addActionListener(new AccioBotoData(this));

        panellCapçalera = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Etiqueta de l'any i/o mes seleccionat
        etiquetaSuperior = new JLabel("Any: "+dataActual.getAny());
        etiquetaSuperior.setFont(new Font("Calibri", Font.BOLD, 24));
        etiquetaSuperior.setForeground(Color.BLACK);

        // Botó de l'esquerra per a decrementar l'any
        botoEsquerraAny = new JButton("-Any");
        botoEsquerraAny.setFont(new Font("Calibri", Font.BOLD, 20));
        botoEsquerraAny.addActionListener(new AccioBotonsIncDecAny(this, false));

        // Botó de la dreta per a incrementar l'any 
        botoDretaAny = new JButton("Any+");
        botoDretaAny.setFont(new Font("Calibri", Font.BOLD, 20));
        botoDretaAny.addActionListener(new AccioBotonsIncDecAny(this, true));

        // Botó de l'esquerra per a decrementar el mes
        botoEsquerraMes = new JButton("-Mes");
        botoEsquerraMes.setFont(new Font("Calibri", Font.BOLD, 20));
        botoEsquerraMes.addActionListener(new AccioBotonsIncDecMes(this, false));

        // Botó de la dreta per a incrementar l'any 
        botoDretaMes = new JButton("Mes+");
        botoDretaMes.setFont(new Font("Calibri", Font.BOLD, 20));
        botoDretaMes.addActionListener(new AccioBotonsIncDecMes(this, true));

        // Afegim els botons de l'esquerra, l'etiqueta i el botons de la dreta, els botons dels mesos no tenen visibilitat fins que es clica un mes
        panellCapçalera.add(botoEsquerraAny);
        panellCapçalera.add(botoEsquerraMes);
        botoEsquerraMes.setVisible(false);  
        panellCapçalera.add(etiquetaSuperior);
        panellCapçalera.add(botoDretaMes);
        panellCapçalera.add(botoDretaAny);
        botoDretaMes.setVisible(false);

        // Afegim els 3 elements al panell superior
        panellSuperior.add(botoData, BorderLayout.WEST);
        panellSuperior.add(panellCapçalera, BorderLayout.CENTER);
        panellSuperior.add(botoPlaceholder, BorderLayout.EAST);
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
                botonsAny[i][j].setFont(new Font("Calibri", Font.BOLD, 20)); // Format pel text del botó
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

    /**
     * Mètode que retorna el nombre de dies de la setmana que hi ha abans del primer dia d'un mes. Per exemple
     * - Si el primer dia d'un mes és un dijous, retornarà 3
     * @return nombre de dies de la setmana que hi ha abans del primer dia d'un mes
     */
    private int calculOffsetMes(int mesActual, int anyActual) {
        Data data = new Data(1, mesActual, anyActual);  // Declarem una variable data per al primer dia del mes
        String diaInicial = data.getDiaSetmana();   // Obtenim un String amb el nom del primer dia del mes

        // Segons el primer dia del mes, modifiquem el valor de offsetInicial
        int offsetInicial = 0;
        switch (diaInicial) {
            case "Dimarts": 
                offsetInicial = 1;  // Abans: Dilluns (1 dia)
                break;
            case "Dimecres": 
                offsetInicial = 2;  // Abans: Dilluns + Dimarts (2 dies)
                break;
            case "Dijous": 
                offsetInicial = 3;  // Abans: Dilluns + ... + Dimecres (3 dies)
                break;
            case "Divendres": 
                offsetInicial = 4;  // Abans: Dilluns + ... + Dijous (4 dies)
                break;
            case "Dissabte": 
                offsetInicial = 5;  // Abans: Dilluns + ... + Divendres (5 dies)
                break;
            case "Diumenge": 
                offsetInicial = 6;  // Abans: Dilluns + ... + Dissanbte (6 dies)
                break;
            default: break;
        }

        // Retornem el nombre de dies de la setmana que hi ha abans del dia inicial
        return offsetInicial;
    }


    // MÉTODES CREATS PER GESTIONAR FINESTRA DES DE BOTONS
    /**
     * Mètode per a:
     * - Incrementar l'any si hem apretat el botó dret de la capçalera superior.
     * - Decrementar l'any si hem apretat el botó esquerra de la capçalera superior.
     * @param esIncrement boolean que indica si es botó dret o no
     */
    public void actualitzarAny(boolean esIncrement) {
        // Incrementem lo decrementem l'any corresponentment
        if (esIncrement) dataActual.modificarAny(1);
        else dataActual.modificarAny(-1);

        // Actualitzem text de la capçalera, depenent de si hem clicat anteriorment un mes o encara no
        if (dataActual.getMes() == 0) etiquetaSuperior.setText("Any: "+dataActual.getAny());
        else {
            etiquetaSuperior.setText("Any: "+dataActual.getAny()+" - Mes: "+Data.nomMes(dataActual.getMes()));
            visualitzarMes();
        }
    }

    /**
     * Mètode per a:
     * - Incrementar el mes si hem apretat el botó dret de la capçalera superior.
     * - Decrementar el mes si hem apretat el botó esquerra de la capçalera superior.
     * @param esIncrement boolean que indica si es botó dret o no
     */
    public void actualitzarMes(boolean esIncrement) {
        // Incrementem lo decrementem el mes corresponentment
        if (esIncrement) dataActual.modificarMes(1);
        else dataActual.modificarMes(-1);

        // Actualitzem text de la capçalera, depenent de si hem clicat anteriorment un mes o encara no
        etiquetaSuperior.setText("Any: "+dataActual.getAny()+" - Mes: "+Data.nomMes(dataActual.getMes()));
        visualitzarMes();
    }

    /**
     * Mètode per a afegir el mes seleccionat a l'etiqueta de la capçalera superior, també actualitza la variable mesActual.
     * @param mes index del mes seleccionat
     */
    public void afegirMes(int mes) {
        this.dataActual.setMes(mes);
        etiquetaSuperior.setText("Any: "+dataActual.getAny()+" - Mes: "+Data.nomMes(dataActual.getMes()));
    }

    /**
     * Mètode que actualitzarà la visualització del panell de Mes per a visualitzar els dies del mes seleccionat
     */
    public void visualitzarMes() {
        panellPrincipal.remove(panellMes);  // Esborrem el panell mes anterior

        // La primera vegada que cliquem a un mes, activem la visualització dels botons per a decrementar e incrementar els mesos
        botoEsquerraMes.setVisible(true);
        botoDretaMes.setVisible(true);

        int diesMes = Data.diesDelMes(dataActual.getMes(), dataActual.getAny());    // Càlcul del nombre de dies total que té el mes seleccionat
        int offsetIni = calculOffsetMes(dataActual.getMes(), dataActual.getAny());  // Càlcul del nombre de dies de la setmana que hi ha abans del primer dia del mes

        int n_columnes = 7; // Nombre de columnes
        int n_files = 6;    // Nombre de files

        // Creem el panell corresponent al Mes, amb 6 files de 7 botons cadascuna
        panellMes = new JPanel(new GridLayout(n_files, n_columnes, 10, 10));
        botonsMes = new JButton[n_files][n_columnes];

        int diaActual = 1;        // Valor del dia
        int offset = 0;           // Nombre d'offset realitzat

        // Bucle per les files (setmanes)
        for (int i = 0; i < n_files; i++) {
            // Bucle per els dies
            for (int j = 0; j < n_columnes; j++) {
                // Primer afegim botons amb text buit si encara no hem arribat al primer dia del mes o si ja hem escrit l'últim dia del mes
                if ((offset < offsetIni) || (diaActual > diesMes)) {
                    botonsMes[i][j] = new JButton(" ");
                    offset++;
                }

                // Escrivim els botons corresponents als dies del mes
                else if (diaActual <= diesMes) {
                    botonsMes[i][j] = new JButton(Integer.toString(diaActual));             // Assignem el número del dia del mes com a text del botó
                    botonsMes[i][j].setFont(new Font("Calibri", Font.BOLD, 24)); // Format pel text del botó
                    
                    // Si la data en la que estem conté cap activitat, marquem el botó amb un color
                    if (llistaAct.hiHaActivitat(new Data(diaActual, dataActual.getMes(), dataActual.getAny()))) {
                        botonsMes[i][j].setBackground(Color.CYAN);
		                botonsMes[i][j].setOpaque(true);
                    }

                    diaActual++;    // Incrementem el dia actual
                }

                // Afegim el botó buit o amb el dia corresponent
                panellMes.add(botonsMes[i][j]);
            }
        }

        panellPrincipal.add(panellMes); // Afegim el nou panellMes al panell principal
    }

    /**
     * 
     */
    public void preguntarDades() {
        EntrarDades d = new EntrarDades(this);
        if (d.dadesEntrades()) {
            //System.out.println("Dia: " + d.getNom());
            System.out.println("Mes: " + d.getMes());
            //System.out.println("Any: " + d.getAny());
        }
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
        // Codi auxiliar per a provar que es veuen activitats
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