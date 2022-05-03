package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public final class JuegoContenido extends JPanel implements ActionListener {

    //Pantalla
    static final int PANTALLA = 600;
    static final int CUADRITO_SIZE = 25;
    static final int CUADR0S_EN_PARALELO = (int) PANTALLA / CUADRITO_SIZE;
    //Serpiente
    static final int TOTAL_CUERPO_SERPIENTE = (PANTALLA * PANTALLA) / CUADRITO_SIZE;
    int[] serpienteX = new int[TOTAL_CUERPO_SERPIENTE];
    int[] serpienteY = new int[TOTAL_CUERPO_SERPIENTE];
    int cuerpo_serpiente = 3;
    char direccion = 'd'; //awsd
    //Comida
    int comidaX;
    int comidaY;
    //Timer
    boolean running = true;
    static final int DELAY = 100;
    Timer timer;
    //Otros
    Random random = new Random();

    public JuegoContenido() {
        this.setPreferredSize(new Dimension(PANTALLA, PANTALLA)); //Seteamos el tamaño
        this.setBackground(Color.decode("#252529"));
        this.setFocusable(true);
        this.addKeyListener(new Controles());
        iniciarJuego();

    }

    public void iniciarJuego() {
        agregarComida();
        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void agregarComida() {
        comidaX = random.nextInt(CUADR0S_EN_PARALELO) * CUADRITO_SIZE;
        comidaY = random.nextInt(CUADR0S_EN_PARALELO) * CUADRITO_SIZE;

    }

    public void moverSerpiente() {
        for (int i = cuerpo_serpiente; i > 0; i--) {

            serpienteX[i] = serpienteX[i - 1];
            serpienteY[i] = serpienteY[i - 1];

        }
        switch (direccion) {
            case 'd' ->
                serpienteX[0] = serpienteX[0] + CUADRITO_SIZE;
            case 'a' ->
                serpienteX[0] = serpienteX[0] - CUADRITO_SIZE;
            case 'w' ->
                serpienteY[0] = serpienteY[0] - CUADRITO_SIZE;
            case 's' ->
                serpienteY[0] = serpienteY[0] + CUADRITO_SIZE;

        }

    }

    public void checarComida() {
        if (serpienteX[0] == comidaX && serpienteY[0] == comidaY) {
            cuerpo_serpiente++;
            agregarComida();
        }

    }

    public void checarChoques() {
        if (serpienteX[0] < 0) {
            running = false;
        }
        if (serpienteY[0] < 0) {
            running = false;
        }
        if (serpienteX[0] > PANTALLA - CUADRITO_SIZE) {
            running = false;
        }
        if (serpienteY[0] > PANTALLA - CUADRITO_SIZE) {
            running = false;
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Dibuja una cruadicula
        /*   for (int i = 0; i < CUADR0S_EN_PARALELO; i++) {
            g.drawLine(0, CUADRITO_SIZE * i, PANTALLA, CUADRITO_SIZE * i);
            g.drawLine(CUADRITO_SIZE * i, 0, CUADRITO_SIZE * i, PANTALLA);
        }*/
        g.setColor(Color.decode("#DF4E4F"));
        g.fillOval(comidaX, comidaY, CUADRITO_SIZE, CUADRITO_SIZE);
        g.setColor(Color.decode("#93BE41"));
        for (int i = 0; i < cuerpo_serpiente; i++) {
            g.fillRect(serpienteX[i], serpienteY[i], CUADRITO_SIZE, CUADRITO_SIZE);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            moverSerpiente();
            checarComida();
            checarChoques();
        }
        repaint();
    }

    public class Controles extends KeyAdapter {

        @Override //Sobreescribimos el metodo
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) { //Nos movemos con las flechas del teclado en codigo ASCII
                case 38 -> direccion = 'w';
                case 40 -> direccion = 's';
                case 37 -> direccion = 'a';
                case 39 -> direccion = 'd';

            }

        }

    }

}
