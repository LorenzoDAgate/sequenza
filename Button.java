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
    private JLabel resultLabel;  // Etichetta per il risultato
    private JButton prossimo;
    private int seconds = 0;
    private int minutes = 0;
    JButton button1, button2, button3, button4, button5, button6;
    private JPanel gridPanel;
    private JPanel gridPanel2;

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

    // Costruttore della classe Button
    public void button() {
        // Creazione della finestra principale
        sequenza = new JFrame();
        sequenza.setTitle("In Sequenza");
        sequenza.setSize(1300, 800);
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

        // Aggiungiamo la JLabel per il risultato
        resultLabel = new JLabel("");  // La scritta per il risultato
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        south.add(resultLabel);
        
        prossimo = new JButton("Avanti");
        prossimo.setVisible(false);
        south.add(prossimo);

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

        // Aggiungi l'azione per il bottone "Controlla"
        buttonControlla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				  if (button4.getIcon().toString().equals(new ImageIcon("nuoto1.jpg").toString()) &&
                    button5.getIcon().toString().equals(new ImageIcon("nuoto2.jpg").toString()) &&
                    button6.getIcon().toString().equals(new ImageIcon("nuoto3.jpg").toString())) {
                    resultLabel.setText("Giusto");  // Se le immagini sono giuste, mostra "Giusto"
                    resultLabel.setForeground(Color.GREEN);  // Colore verde per il risultato giusto
                    prossimo.setVisible(true);
                } else {
                    resultLabel.setText("Sbagliato");  // Se le immagini sono sbagliate, mostra "Sbagliato"
                    resultLabel.setForeground(Color.RED);  // Colore rosso per il risultato sbagliato
                }
                

                
            }
        });
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
        button1 = new JButton(new ImageIcon("nuoto1.jpg"));
        button2 = new JButton(new ImageIcon("nuoto2.jpg"));
        button3 = new JButton(new ImageIcon("nuoto3.jpg"));
        
        button4 = new JButton(new ImageIcon("bianco.jpeg"));
        button5 = new JButton(new ImageIcon("bianco.jpeg"));
        button6 = new JButton(new ImageIcon("bianco.jpeg"));
        
        // aggancio il listener ai bottoni
        bListener bL = new bListener(this);
        button1.addActionListener(bL);
        button2.addActionListener(bL);
        button3.addActionListener(bL);
        
        button4.addActionListener(bL);
        button5.addActionListener(bL);
        button6.addActionListener(bL);

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

class bListener implements ActionListener {
    JButton left; // Memorizza l'immagine selezionata
    Button finestra;

    public bListener(Button b) {
        super();
        left = null;
        finestra = b;
    }

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)(e.getSource());

        // Se un'immagine della colonna sinistra viene cliccata, memorizza l'immagine
        if (b == finestra.button1 || b == finestra.button2 || b == finestra.button3) {
            if (left == null) {
                left = b; // Memorizza l'immagine
            }
        }
        // Se un'immagine della colonna destra viene cliccata
        else if (b == finestra.button4 || b == finestra.button5 || b == finestra.button6) {
            if (left != null) {
                // Inverti le immagini
                ImageIcon tempIcon = (ImageIcon) b.getIcon();
                b.setIcon((ImageIcon) left.getIcon()); // Imposta l'immagine di sinistra nella posizione destra
                left.setIcon(tempIcon); // Rimetti l'immagine di destra nella posizione sinistra
                left = null; // Resetta la memoria
            }
        }
    }
}
