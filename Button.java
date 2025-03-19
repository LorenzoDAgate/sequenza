import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Button {
    private JFrame sequenza;
    private JPanel pannelloBase;
    private JPanel south;
    private JButton inizio;
    private JButton buttonControlla;
    private JLabel timerLabel;
    private int seconds = 0;
    private int minutes = 0;

    // Costruttore che crea l'interfaccia
    public void button() {
        // Creazione della finestra principale
        sequenza = new JFrame();
        sequenza.setTitle("In Sequenza");
        sequenza.setSize(1000, 500);
        sequenza.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creazione del pannello principale
        pannelloBase = new JPanel();
        pannelloBase.setLayout(new BorderLayout());

        // Creazione del pannello inferiore (South)
        south = new JPanel();
        south.setLayout(new FlowLayout());

        // Creazione e personalizzazione del bottone "Start"
        inizio = new JButton("Start");
        inizio.setBackground(Color.LIGHT_GRAY);
        inizio.setForeground(Color.WHITE);
        inizio.setFont(new Font("Arial", Font.BOLD, 16));
        inizio.setSize(1000, 500);
        inizio.setVisible(true);

        // Creazione del pannello centrale per il bottone "Start"
        JPanel centro = new JPanel();
        centro.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 170));
        centro.add(inizio);
        pannelloBase.add(centro, BorderLayout.CENTER);

        // Creazione dell'etichetta per il timer
        timerLabel = new JLabel("00:00", JLabel.LEFT);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        south.add(timerLabel);

        // Bottone "Controlla" inizialmente invisibile
        buttonControlla = new JButton("Controlla");
        buttonControlla.setVisible(false);
        south.add(buttonControlla);

        // Aggiunta del pannello principale alla finestra
        sequenza.add(pannelloBase, BorderLayout.CENTER);
        sequenza.add(south, BorderLayout.SOUTH);

        // Visualizzazione della finestra
        sequenza.setVisible(true);

        // Imposta l'azione per il bottone "Start"
        inizio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Quando clicchi su "Start", nascondi il bottone e avvia il timer
                inizio.setVisible(false);
            }
        });
    }

    // Metodo per avviare il timer
    public void startTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                // Incrementa i secondi e aggiorna il timer
                seconds++;
                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }
                String time = String.format("%02d:%02d", minutes, seconds);
                timerLabel.setText(time);  // Aggiorna l'etichetta del timer
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);  // Avvia il timer ogni secondo
    }
}
