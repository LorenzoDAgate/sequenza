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

    private JPanel gridPanel;
    private JPanel gridPanel2;

    // Costruttore della classe Button
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
                startTimer(); // Avvia il timer quando il bottone è premuto
                mostraImmagini(); // Mostra le immagini
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

    // Metodo per mostrare le immagini
    public void mostraImmagini() {
        // Crea i pannelli per le immagini
        gridPanel = new JPanel();
        gridPanel2 = new JPanel();
        
        // Imposta il layout dei pannelli per contenere le immagini
        gridPanel.setLayout(new GridLayout(2, 6));
        gridPanel2.setLayout(new GridLayout(2, 6));

        // Creazione dei bottoni con le immagini
        JButton button1 = new JButton(new ImageIcon("/home/utente/Scrivania/nuoto1.jpg"));
        JButton button2 = new JButton(new ImageIcon("/home/utente/Scrivania/nuoto2.jpg"));
        JButton button3 = new JButton(new ImageIcon("/home/utente/Scrivania/nuoto3.jpg"));
        
        JButton button4 = new JButton(new ImageIcon("/home/utente/Scrivania/inSequenza/immagini/bianco.jpeg"));
        JButton button5 = new JButton(new ImageIcon("/home/utente/Scrivania/inSequenza/immagini/bianco.jpeg"));
        JButton button6 = new JButton(new ImageIcon("/home/utente/Scrivania/inSequenza/immagini/bianco.jpeg"));

        // Bottone "Controlla" inizialmente invisibile
        buttonControlla.setVisible(true);

        // Aggiungi i bottoni ai pannelli
        gridPanel.add(button1);
        gridPanel.add(button2);
        gridPanel.add(button3);
        
        gridPanel2.add(button4);
        gridPanel2.add(button5);
        gridPanel2.add(button6);

        // Aggiungi i pannelli alla finestra principale
        pannelloBase.add(gridPanel, BorderLayout.WEST);
        pannelloBase.add(gridPanel2, BorderLayout.EAST);
        pannelloBase.add(south, BorderLayout.SOUTH);

        // Rendi i pannelli visibili solo dopo aver cliccato "Start"
        gridPanel.setVisible(true);
        gridPanel2.setVisible(true);
    }
}
