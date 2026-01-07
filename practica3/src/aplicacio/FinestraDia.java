// PAQUET
package aplicacio;

// IMPORTS JAVA
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// IMPORTS PAQUETS LOCALS
import dades.*;


/**
 * Classe amb la implementació gràfica per a visualitzar les activitats d'un dia.
 */
public class FinestraDia extends JFrame {
    private JPanel activitats;              // Panell que conté la llista d'activitats que es produeixen la data
    private JPanel panelNord;               // Panell que contindrà la data del dia i el nombre d'activitats que es produeixen
    private JPanel panelActivitat;          // Panell corresponent a cada activitat que s'afegirà a activitats
    private JLabel etiquetaData;            // Text que contindrà la data que estem visualitzant
    private JLabel etiquetaNumActivitats;   // Text que contindrà el nombre d'activitats de la data que estem visualitzant
    private JLabel etiquetaActivitat;       // Text que contindrà el nom de l'activitat del dia
    private JButton botoDetalls;


    /**
     * Mètode constructor per a crear una visualització de les activitats que es deuen a terme en la data indicada.
     * @param data data a visualitzar
     * @param llista llista de totes les activitats que tenim al programa
     * @param ocultarUnDia filtre d'activitats d'un dia
     * @param ocultarPeriodic filtre d'activitats periòdiques
     * @param ocultarOnline filtre d'activitats online
     * @throws IOException
     */
    public FinestraDia(Data data, LlistaActivitats llista, boolean ocultarUnDia, boolean ocultarPeriodic, boolean ocultarOnline) throws IOException {
        super("Llista d'Activitats "+data.toString());  // Títol de la finestra
        setSize(350, 500);                // Mida de la finestra
        setVisible(true);
        setLayout(new BorderLayout());                  // Disposició d'elements en direccions cardianls

        // Creem la llista d'activitats de la data corresponent
        LlistaActivitats llistaData = llista.llistaActivitatsData(data, ocultarUnDia, ocultarPeriodic, ocultarOnline);
        int numActivitats = llistaData.getNumActivitats();

        crearPanellNord(data, numActivitats);               // Creem el panell nord
        crearPanellActivitats(llistaData, numActivitats);   // Creem el panell de les activitats

        this.add(panelNord, BorderLayout.NORTH);
        this.add(activitats, BorderLayout.CENTER);
    }


    // MÉTODES AUXILIARS PER A GESTIONAR LA FINESTRA DE VISUALITZACIÓ DEL DIA
    /**
     * Mètode per a crear la capçalera superior de la finestra de visualització de les activitats d'una data, conté:
     * - La data que estem visualitzant.
     * - El nombre d'activitats que hi ha la data corresponent.
     * @param data data del dia a visualitzar
     * @param numActivitats nombre d'activitats del dia a visualitzar
     */
    private void crearPanellNord(Data data, int numActivitats) {
        // Creem el panell que contindrà les etiquetes de la data i el nombre d'activitats com un GridLayout de 2 files i 1 columns
        panelNord = new JPanel(new GridLayout(2, 1, 0, 5));

        // Creació del text de la data
        etiquetaData = new JLabel(data.getDiaSetmana()+" "+data.getDia()+" de "+Data.nomMes(data.getMes())+" de "+data.getAny());
        etiquetaData.setFont(new Font("Calibri", Font.BOLD, 20));
        etiquetaData.setForeground(Color.BLACK);

        // Creació del text del número d'activitats
        etiquetaNumActivitats = new JLabel("Nombre d'activitats: "+numActivitats);
        etiquetaNumActivitats.setFont(new Font("Calibri", Font.BOLD, 20));
        etiquetaNumActivitats.setForeground(Color.BLACK);

        // Afegim les dues etiquetes al panell nord
        panelNord.add(etiquetaData);            // Fila 1
        panelNord.add(etiquetaNumActivitats);   // Fila 2
    }

    /**
     * Mètode per a crear la llista de les activitats qeu es realitzen durant la data indicada
     * @param llistaData llista d'activitats que es deuen a terme en la data indicada
     * @param numActivitats nombre d'activitats que es deuen a terme en la data indicada
     */
    private void crearPanellActivitats(LlistaActivitats llistaData, int numActivitats) {
        activitats = new JPanel(new GridLayout(numActivitats, 1, 0, 10));

        // Per a cada activitat que hi ha realitzant-se durant el dia indicat
        for (int i = 0; i < numActivitats; i++) {
            final int index = i;

            // Crear un panel per cada activitat
            panelActivitat = new JPanel(new BorderLayout());
            panelActivitat.setBackground(new Color(240, 248, 255));
                
            // Crear una etiqueta amb el nom de l'activitat
            etiquetaActivitat = new JLabel("Activitat " + (i + 1) + ": " + llistaData.getActivitat(i).getNom());
            etiquetaActivitat.setFont(new Font("Calibri", Font.PLAIN, 14));
            etiquetaActivitat.setForeground(Color.BLACK);
                
            // Botó per detalls
            botoDetalls = new JButton("Detalls");
            botoDetalls.setFont(new Font("Calibri", Font.PLAIN, 12));
            botoDetalls.setBackground(new Color(70, 130, 180));
            botoDetalls.setForeground(Color.WHITE);

            // ActionListener que obre la finestra de detalls
            botoDetalls.addActionListener(e -> {
                Activitats activitatSeleccionada = llistaData.getActivitat(index);
                new FinestraDetallActivitat(activitatSeleccionada);
            });
                
            // Afegir components al panel de l'activitat
            panelActivitat.add(etiquetaActivitat, BorderLayout.CENTER);
            panelActivitat.add(botoDetalls, BorderLayout.EAST);
                
            // Afegir panel de l'activitat al panel principal
            activitats.add(panelActivitat);
        }

        // Si no hi ha activitats, mostrar missatge
        if (numActivitats == 0) {
            activitats = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel senseActivitats = new JLabel("No hi ha activitats per a aquest dia");
            senseActivitats.setFont(new Font("Calibri", Font.ITALIC, 16));
            senseActivitats.setForeground(Color.GRAY);
            activitats.add(senseActivitats);
        }
    }
}
