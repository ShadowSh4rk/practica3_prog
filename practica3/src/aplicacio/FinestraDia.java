package aplicacio;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import dades.*;

public class FinestraDia extends JFrame {
    private JLabel etiquetaSuperior;
    private JTextArea area;

    public FinestraDia(Data data, LlistaActivitats llista, boolean ocultarUnDia, boolean ocultarPeriodic, boolean ocultarOnline) throws IOException {
        super(data.toString());
        setSize(500, 800);
        setVisible(true);
        setLayout(new BorderLayout());

        etiquetaSuperior = new JLabel("Llista d'Activitats del "+data.getDia()+" de "+Data.nomMes(data.getMes())+" de "+data.getAny());
        etiquetaSuperior.setFont(new Font("Calibri", Font.BOLD, 20));
        etiquetaSuperior.setForeground(Color.BLACK);

        area = new JTextArea();

        this.add(etiquetaSuperior, BorderLayout.NORTH);
        this.add(area, BorderLayout.CENTER);

        LlistaActivitats llistaData = llista.llistaActivitatsData(data, ocultarUnDia, ocultarPeriodic, ocultarOnline);

        for (int i = 0; i < llistaData.getNumActivitats(); i++) this.area.append(llistaData.getActivitat(i).getNom()+"\n");
    }
}
