package com.javaguides.javaswing.login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HomePage extends JFrame {

    private static final long serialVersionUID = 1;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomePage frame = new HomePage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public HomePage() {

    }

    /**
     * Create the frame.
     */
    public HomePage(String userSes) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250,200);
        setResizable(false);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel introLabel = new JLabel("Velkommen til CoronaNet!");
        introLabel.setLocation(-15,10);
        introLabel.setSize(200, 20);
        introLabel.setHorizontalAlignment(0);
        contentPane.add(introLabel);

        JLabel selLabel = new JLabel("Fremsøgningsfunktioner: ");
        selLabel.setLocation(-13,30);
        selLabel.setSize(195, 20);
        selLabel.setHorizontalAlignment(0);
        contentPane.add(selLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(5, 80);
        buttonPanel.setSize(250, 70);
        contentPane.add(buttonPanel);

        JButton zipButton = new JButton("Postnummer");
        zipButton.setLocation(0, 0);
        zipButton.setSize(110, 30);

        JButton timeButton = new JButton("Tidsrum");
        timeButton.setLocation(115, 0);
        timeButton.setSize(110, 30);
        buttonPanel.add(timeButton);

        timeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeSearch po = new timeSearch(userSes);
                po.setTitle("Søg - Tidsrum");
                po.setVisible(true);
            }
        });
        buttonPanel.add(timeButton);

        zipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zipSearch bo = new zipSearch(userSes);
                bo.setTitle("Søg - Postnummer");
                bo.setVisible(true);

            }
        });
        buttonPanel.add(zipButton);
    }
}
