package com.javaguides.javaswing.login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class zipSearch extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel introLabel;
    private JLabel searchLabel;
    private JPanel textPanel;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public zipSearch(String name) {
        setSize(250, 200);
        setResizable(false);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        introLabel = new JLabel("Velkommen til CoronaNet!");
        introLabel.setLocation(-15,10);
        introLabel.setSize(200, 20);
        introLabel.setHorizontalAlignment(0);
        contentPane.add(introLabel);

        searchLabel = new JLabel("Indsæt Postnummer:");
        searchLabel.setLocation(-5,45);
        searchLabel.setSize(150, 20);
        searchLabel.setHorizontalAlignment(0);
        contentPane.add(searchLabel);

        textPanel = new JPanel();
        textPanel.setLayout(null);
        textPanel.setLocation(0,50);
        textPanel.setSize(250,20);
        contentPane.add(textPanel);

        textField = new JTextField();
        textField.setLocation(50,70);
        textField.setSize(125, 20);
        contentPane.add(textField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(0, 80);
        buttonPanel.setSize(250, 70);
        contentPane.add(buttonPanel);

        JButton btnSearch = new JButton("Søg");
        btnSearch.setLocation(75, 40);
        btnSearch.setSize(75, 30);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String pstr = textField.getText();
                try {
                    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_gui",
                            "root", "root");

                    PreparedStatement st = (PreparedStatement) con
                            .prepareStatement("Select count(*) from mutantsearch where zipcode=?");

                    st.setString(1, pstr);
                    ResultSet rs = st.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);
                    JOptionPane.showMessageDialog(btnSearch, "COVID-positive: " + count, "Resultat", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }

            }
        });
        buttonPanel.add(btnSearch);
    }
}