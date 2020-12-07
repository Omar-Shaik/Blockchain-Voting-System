import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// import jdk.internal.jshell.tool.resources.version;

public class Kiosk {

    public Kiosk() {
        JFrame frame = new JFrame();

        JLabel dLicense = new JLabel("Drivers License: ");
        dLicense.setBounds(10, 20, 80, 25);

        JTextField dLicensenumber = new JTextField();
        dLicensenumber.setBounds(150, 20, 165, 25);

        JLabel dCandidate = new JLabel("Candidate Name: ");
        dCandidate.setBounds(10, 40, 80, 25);

        JButton button = new JButton("Vote Candidate 1");
        // button.setBounds(150, 40, 165, 25);

        JButton button2 = new JButton("Vote Candidate 2");
        // button2.setBounds(250, 40, 165, 25);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String driverLicense = dLicensenumber.getText();
                String candidate1 = "Candidate 1";
                try {
                    Client.setUIInfo(driverLicense + "-" + candidate1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                frame.dispose();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String driverLicense = dLicensenumber.getText();
                String candidate2 = "Candidate 2";
                try {
                    Client.setUIInfo(driverLicense + "-" + candidate2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                frame.dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(dLicense);
        panel.add(dLicensenumber);
        panel.add(dCandidate);
        // panel.add(dCandidatename);

        panel.add(button);
        panel.add(button2);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Blockchain Voting System");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Kiosk();
    }

}
