import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    private static JLabel timerLabel;  
    private static int seconds = 0;
    private static int minutes = 0;

    public static void main(String[] args) {

        JFrame sequenza = new JFrame();
        sequenza.setTitle("In Sequenza");
        sequenza.setSize(1000, 500);
        sequenza.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titolo = new JLabel("Metti in ordine", JLabel.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel pannelloBase = new JPanel();
        pannelloBase.setLayout(new BorderLayout());
        pannelloBase.add(titolo, BorderLayout.NORTH);

        sequenza.add(pannelloBase);

        //bottone
        JButton inizio = new JButton("Start");
        inizio.setBackground(Color.LIGHT_GRAY);  
        inizio.setForeground(Color.WHITE); 
        inizio.setFont(new Font("Arial", Font.BOLD, 16));
        inizio.setSize(1000, 500);
        inizio.setVisible(true);
        
        
        JPanel centro = new JPanel();
        centro.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 170));
        centro.add(inizio);
        pannelloBase.add(centro, BorderLayout.CENTER);

        JPanel gridPanel = new JPanel();
        JPanel gridPanel2 = new JPanel();
        
        gridPanel.setVisible(false);
        gridPanel2.setVisible(false);
        
        gridPanel.setLayout(new GridLayout(2, 6));
        gridPanel2.setLayout(new GridLayout(2, 6)); 

        JButton button1 = new JButton("Pulsante 1");
        JButton button2 = new JButton("Pulsante 2");
        JButton button3 = new JButton("Pulsante 3");
        
        JButton button4 = new JButton("Pulsante 4");
        JButton button5 = new JButton("Pulsante 5");
        JButton button6 = new JButton("Pulsante 6");

        gridPanel.add(button1);
        gridPanel.add(button2);
        gridPanel.add(button3);
        
        gridPanel2.add(button4);
        gridPanel2.add(button5);
        gridPanel2.add(button6);

        pannelloBase.add(gridPanel, BorderLayout.WEST); 
        pannelloBase.add(gridPanel2, BorderLayout.EAST);
        
       
        timerLabel = new JLabel("00:00", JLabel.LEFT);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        pannelloBase.add(timerLabel, BorderLayout.SOUTH);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                inizio.setVisible(false);
                gridPanel.setVisible(true);
                gridPanel2.setVisible(true);
                seconds++;
                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }
                String time = String.format("%02d:%02d", minutes, seconds);
                timerLabel.setText(time);  
            }
        };

        inizio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.scheduleAtFixedRate(task, 1000, 1000);
            }
        });
        
        sequenza.setVisible(true);
        
    }
}

