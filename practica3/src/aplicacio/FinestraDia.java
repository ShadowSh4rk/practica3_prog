package aplicacio;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import dades.*;

public class FinestraDia extends JFrame {
    private JPanel activitats;
    private JLabel etiquetaSuperior;
    private JLabel etiquetaNumActivitats;
    private JLabel[] etiquetaActivitat;

    public FinestraDia(Data data, LlistaActivitats llista, boolean ocultarUnDia, boolean ocultarPeriodic, boolean ocultarOnline) throws IOException {
        super("Llista d'Activitats "+data.toString());
        setSize(400, 500);
        setVisible(true);
        setLayout(new BorderLayout());

        etiquetaSuperior = new JLabel(data.getDia()+" de "+Data.nomMes(data.getMes())+" de "+data.getAny());
        etiquetaSuperior.setFont(new Font("Calibri", Font.BOLD, 20));
        etiquetaSuperior.setForeground(Color.BLACK);

        this.add(etiquetaSuperior, BorderLayout.NORTH);

        LlistaActivitats llistaData = llista.llistaActivitatsData(data, ocultarUnDia, ocultarPeriodic, ocultarOnline);
        int numActivitats = llistaData.getNumActivitats();

        activitats = new JPanel(new GridLayout(numActivitats + 1, 1));
        etiquetaNumActivitats = new JLabel("Nombre d'Activitats: "+numActivitats);
        etiquetaNumActivitats.setFont(new Font("Calibri", Font.BOLD, 15));
        etiquetaNumActivitats.setForeground(Color.BLACK);
        activitats.add(etiquetaNumActivitats, BorderLayout.CENTER);

        etiquetaActivitat = new JLabel[numActivitats];
        for (int i = 0; i < numActivitats; i++) {
            etiquetaActivitat[i] = new JLabel("Activitat "+(i + 1)+": "+llistaData.getActivitat(i).getNom());
            etiquetaActivitat[i].setFont(new Font("Calibri", Font.BOLD, 15));
            etiquetaActivitat[i].setForeground(Color.BLACK);
            activitats.add(etiquetaActivitat[i]);
        }

        this.add(activitats, BorderLayout.CENTER);
    }
}
