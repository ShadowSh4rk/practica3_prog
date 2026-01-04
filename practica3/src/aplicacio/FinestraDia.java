package aplicacio;

import javax.swing.*;
import java.awt.*;
import dades.*;

public class FinestraDia extends JFrame {
    JLabel etiquetaSuperior;

    public FinestraDia(Data data, boolean ocultarUnDia, boolean ocultarPeriodic, boolean ocularOnline) {
        super(data.toString());
        setSize(400, 800);
        setVisible(true);
        setLayout(new BorderLayout());

        etiquetaSuperior = new JLabel(data.getDia()+" de "+Data.nomMes(data.getMes())+", "+data.getAny());
        etiquetaSuperior.setFont(new Font("Calibri", Font.BOLD, 24));
        etiquetaSuperior.setForeground(Color.BLACK);

        this.add(etiquetaSuperior, BorderLayout.NORTH);
    }
}
