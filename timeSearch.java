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

public class timeSearch extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel introLabel;
    private JLabel searchLabel;
    private JLabel formatLabel;
    private JLabel searchFromLabel;
    private JLabel searchToLabel;
    private JTextField textFromField;
    private JTextField textToField;

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
    public timeSearch(String name) {
        setSize(250, 200);
        setResizable(false);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        introLabel = new JLabel("Velkommen til CoronaNet!");
        introLabel.setLocation(-15,5);
        introLabel.setSize(200, 20);
        introLabel.setHorizontalAlignment(0);
        contentPane.add(introLabel);

        searchLabel = new JLabel("Angiv Tidsinterval:");
        searchLabel.setLocation(-10,25);
        searchLabel.setSize(150, 20);
        searchLabel.setHorizontalAlignment(0);
        contentPane.add(searchLabel);

        formatLabel = new JLabel(" - Format: YYYY/MM/DD hh/mm/ss");
        formatLabel.setLocation(10,40);
        formatLabel.setSize(200, 20);
        formatLabel.setHorizontalAlignment(0);
        contentPane.add(formatLabel);

        searchFromLabel = new JLabel("Fra:");
        searchFromLabel.setLocation(10,70);
        searchFromLabel.setSize(25, 20);
        searchFromLabel.setHorizontalAlignment(0);
        contentPane.add(searchFromLabel);

        searchToLabel = new JLabel("Til:");
        searchToLabel.setLocation(10,95);
        searchToLabel.setSize(25, 20);
        searchToLabel.setHorizontalAlignment(0);
        contentPane.add(searchToLabel);

        textFromField = new JTextField();
        textFromField.setLocation(50,70);
        textFromField.setSize(125, 20);
        contentPane.add(textFromField);

        textToField = new JTextField();
        textToField.setLocation(50,95);
        textToField.setSize(125, 20);
        contentPane.add(textToField);

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

                String searchFrom = textFromField.getText();
                String searchTo = textToField.getText();
                try {
                    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_gui",
                            "root", "root");

                    PreparedStatement st = (PreparedStatement) con
                            .prepareStatement("Select count(*) from mutantsearch where timestampPositive >=? and timestampPositive <=?");

                    st.setString(1, searchFrom);
                    st.setString(2, searchTo);
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