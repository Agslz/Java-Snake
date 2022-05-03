package Snake;

import javax.swing.JFrame;

public class JuegoVentana extends JFrame {

    public JuegoVentana() {
        this.setTitle("Snake");
        this.add(new JuegoContenido());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
