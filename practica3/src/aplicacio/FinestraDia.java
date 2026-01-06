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

    public FinestraDia(Data data, LlistaActivitats llista, boolean ocultarUnDia, boolean ocultarPeriodic, boolean ocultarOnline) throws IOException {
        super("Llista d'Activitats "+data.toString());  // Títol de la finestra
        setSize(350, 500);                // Tamany de la finestra
        setVisible(true);
        setLayout(new BorderLayout());                  // Disposició d'elements en direccions cardianls

        // Creem la llista d'activitats de la data corresponent
        LlistaActivitats llistaData = llista.llistaActivitatsData(data, ocultarUnDia, ocultarPeriodic, ocultarOnline);
        int numActivitats = llistaData.getNumActivitats();

        crearPanellNord(data, numActivitats);               // Creem el panell nord
        crearPanellActivitats(llistaData, numActivitats);   // Creem el panell de les activitats
    }


    // MÉTODES AUXILIARS PER A GESTIONAR LA FINESTRA DE VISUALITZACIÓ DEL DIA
    /**
     * Mètode per a crear la capçalera superior de la finestra de visualització de les activitats d'una data, conté:
     * - La data que estem visualitzant.
     * - El nombre d'activitats que hi ha la data corresponent.
     * @param data
     * @param numActivitats
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

        this.add(panelNord, BorderLayout.NORTH);
    }

    /**
     * 
     * @param llistaData
     * @param numActivitats
     */
    private void crearPanellActivitats(LlistaActivitats llistaData, int numActivitats) {
        activitats = new JPanel(new GridLayout(numActivitats, 1, 0, 10));

        for (int i = 0; i < numActivitats; i++) {
            // Crear un panel per cada activitat
            panelActivitat = new JPanel(new BorderLayout());
            panelActivitat.setBackground(new Color(240, 248, 255));
                
            // Crear una etiqueta amb el nom de l'activitat
            etiquetaActivitat = new JLabel("Activitat " + (i + 1) + ": " + llistaData.getActivitat(i).getNom());
            etiquetaActivitat.setFont(new Font("Calibri", Font.PLAIN, 14));
            etiquetaActivitat.setForeground(Color.BLACK);
                
            // Botó per detalls
            JButton botoDetalls = new JButton("Detalls");
            botoDetalls.setFont(new Font("Calibri", Font.PLAIN, 12));
            botoDetalls.setBackground(new Color(70, 130, 180));
            botoDetalls.setForeground(Color.WHITE);
                
            // Afegir components al panel de l'activitat
            panelActivitat.add(etiquetaActivitat, BorderLayout.CENTER);
            panelActivitat.add(botoDetalls, BorderLayout.EAST);
                
            // Afegir panel de l'activitat al panel principal
            activitats.add(panelActivitat);
        }

        this.add(activitats, BorderLayout.CENTER);
    }
}
