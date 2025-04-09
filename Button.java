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
    private JLabel resultLabel;
    private JButton prossimo;
    private int seconds = 0;
    private int minutes = 0;
    JButton button1, button2, button3, button4, button5, button6;
    private JPanel gridPanel;
    private JPanel gridPanel2;

    private int livelloCorrente = 1;  // <--- Traccia il livello attuale

    public void startTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                seconds++;
                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }
                String time = String.format("%02d:%02d", minutes, seconds);
                timerLabel.setText(time);
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public void button() {
        sequenza = new JFrame();
        sequenza.setTitle("In Sequenza");
        sequenza.setSize(1300, 800);
        sequenza.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pannelloBase = new JPanel();
        pannelloBase.setLayout(new BorderLayout());

        south = new JPanel();
        south.setLayout(new FlowLayout());

        inizio = new JButton("Start");
        inizio.setBackground(Color.LIGHT_GRAY);
        inizio.setForeground(Color.WHITE);
        inizio.setFont(new Font("Arial", Font.BOLD, 16));
        inizio.setVisible(true);

        JPanel centro = new JPanel();
        centro.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 170));
        centro.add(inizio);
        pannelloBase.add(centro, BorderLayout.CENTER);

        timerLabel = new JLabel("00:00", JLabel.LEFT);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        south.add(timerLabel);

        buttonControlla = new JButton("Controlla");
        buttonControlla.setVisible(false);
        south.add(buttonControlla);

        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        south.add(resultLabel);

        prossimo = new JButton("Avanti");
        prossimo.setVisible(false);
        south.add(prossimo);

        sequenza.add(pannelloBase, BorderLayout.CENTER);
        sequenza.add(south, BorderLayout.SOUTH);

        sequenza.setVisible(true);

        inizio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inizio.setVisible(false);
                startTimer();
                mostraImmagini();
            }
        });

        buttonControlla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (livelloCorrente == 1) {
                    if (match("nuoto")) {
                        resultLabel.setText("Giusto");
                        resultLabel.setForeground(Color.GREEN);
                        prossimo.setVisible(true);
                        buttonControlla.setVisible(false);
                        nascondiBottoniSinistra();
                    } else {
                        mostraSoluzione("nuoto");
                    }
                } else if (livelloCorrente == 2) {
                    if (match("spremuta")) {
                        resultLabel.setText("Giusto");
                        resultLabel.setForeground(Color.GREEN);
                        prossimo.setVisible(true);
                        buttonControlla.setVisible(false);
                        nascondiBottoniSinistra();
                    } else {
                        mostraSoluzione("spremuta");
                    }
                } else if (livelloCorrente == 3) {
                    if (match("doccia")) {
                        resultLabel.setText("Giusto! Hai completato tutti i livelli!");
                        resultLabel.setForeground(Color.BLUE);
                        buttonControlla.setVisible(false);
                        prossimo.setVisible(false);
                    } else {
                        mostraSoluzione("doccia");
                    }
                }
            }
        });

        prossimo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                livelloCorrente++;
                resultLabel.setText("");
                if (livelloCorrente == 2) {
                    mostraLivello("spremuta");
                } else if (livelloCorrente == 3) {
                    mostraLivello("doccia");
                }
                prossimo.setVisible(false);
            }
        });
    }

    public void mostraImmagini() {
        gridPanel = new JPanel();
        gridPanel2 = new JPanel();
        gridPanel.setLayout(new GridLayout(2, 6));
        gridPanel2.setLayout(new GridLayout(2, 6));

        button1 = new JButton(new ImageIcon("nuoto1.jpg"));
        button2 = new JButton(new ImageIcon("nuoto2.jpg"));
        button3 = new JButton(new ImageIcon("nuoto3.jpg"));

        button4 = new JButton(new ImageIcon("bianco.jpeg"));
        button5 = new JButton(new ImageIcon("bianco.jpeg"));
        button6 = new JButton(new ImageIcon("bianco.jpeg"));

        bListener bL = new bListener(this);
        button1.addActionListener(bL);
        button2.addActionListener(bL);
        button3.addActionListener(bL);
        button4.addActionListener(bL);
        button5.addActionListener(bL);
        button6.addActionListener(bL);

        buttonControlla.setVisible(true);

        gridPanel.add(button1);
        gridPanel.add(button2);
        gridPanel.add(button3);
        gridPanel2.add(button4);
        gridPanel2.add(button5);
        gridPanel2.add(button6);

        pannelloBase.add(gridPanel, BorderLayout.WEST);
        pannelloBase.add(gridPanel2, BorderLayout.EAST);
        pannelloBase.add(south, BorderLayout.SOUTH);

        gridPanel.setVisible(true);
        gridPanel2.setVisible(true);
    }

    public void mostraLivello(String tipo) {
        button1.setVisible(true);
        button2.setVisible(true);
        button3.setVisible(true);

        button1.setIcon(new ImageIcon(tipo + "1.jpg"));
        button2.setIcon(new ImageIcon(tipo + "2.jpg"));
        button3.setIcon(new ImageIcon(tipo + "3.jpg"));

        button4.setIcon(new ImageIcon("bianco.jpeg"));
        button5.setIcon(new ImageIcon("bianco.jpeg"));
        button6.setIcon(new ImageIcon("bianco.jpeg"));

        buttonControlla.setVisible(true);
    }

    public void nascondiBottoniSinistra() {
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);
    }

    public boolean match(String tipo) {
        return button4.getIcon().toString().contains(tipo + "1.jpg") &&
               button5.getIcon().toString().contains(tipo + "2.jpg") &&
               button6.getIcon().toString().contains(tipo + "3.jpg");
    }

    public void mostraSoluzione(String tipo) {
        resultLabel.setText("Sbagliato");
        resultLabel.setForeground(Color.RED);
        button1.setIcon(new ImageIcon(tipo + "1.jpg"));
        button2.setIcon(new ImageIcon(tipo + "2.jpg"));
        button3.setIcon(new ImageIcon(tipo + "3.jpg"));
        button4.setIcon(new ImageIcon("bianco.jpeg"));
        button5.setIcon(new ImageIcon("bianco.jpeg"));
        button6.setIcon(new ImageIcon("bianco.jpeg"));
    }
}

class bListener implements ActionListener {
    JButton left;
    Button finestra;

    public bListener(Button b) {
        super();
        left = null;
        finestra = b;
    }

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) (e.getSource());
        if (b == finestra.button1 || b == finestra.button2 || b == finestra.button3) {
            if (left == null) {
                left = b;
            }
        } else if (b == finestra.button4 || b == finestra.button5 || b == finestra.button6) {
            if (left != null) {
                ImageIcon tempIcon = (ImageIcon) b.getIcon();
                b.setIcon((ImageIcon) left.getIcon());
                left.setIcon(tempIcon);
                left = null;
            }
        }
    }
}

