package com.javaguides.javaswing.login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField userText;
    private JPasswordField pswText;
    private JLabel label;
    private JPanel titelPanel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLogin frame = new UserLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 175);
        setTitle("CoronaNet");
        titelPanel = new JPanel();
        setContentPane(titelPanel);
        titelPanel.setLayout(null);

        JLabel introLabel = new JLabel("Adgang til CoronaNet");
        introLabel.setForeground(Color.BLACK);
        introLabel.setLocation(-15,5);
        introLabel.setSize(170, 20);
        introLabel.setHorizontalAlignment(0);
        titelPanel.add(introLabel);

        userText = new JTextField();
        userText.setLocation(100,30);
        userText.setSize(125, 20);
        titelPanel.add(userText);

        pswText = new JPasswordField();
        pswText.setLocation(100,55);
        pswText.setSize(125, 20);
        titelPanel.add(pswText);

        JLabel userLabel = new JLabel("Brugernavn: ");
        userLabel.setLocation(-5,30);
        userLabel.setSize(100, 20);
        userLabel.setHorizontalAlignment(0);
        titelPanel.add(userLabel);

        JLabel pswLabel = new JLabel("Adgangskode: ");
        pswLabel.setLocation(0,55);
        pswLabel.setSize(100, 20);
        pswLabel.setHorizontalAlignment(0);
        titelPanel.add(pswLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(0, 80);
        buttonPanel.setSize(250, 40);
        titelPanel.add(buttonPanel);

        JButton loginButton = new JButton("Log ind");
        loginButton.setLocation(75, 10);
        loginButton.setSize(100, 30);
        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userName = userText.getText();
                String password = pswText.getText();
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/swing_gui",
                            "root", "root");

                    PreparedStatement st = (PreparedStatement) connection
                            .prepareStatement("Select name, password from swing_gui_test where name=? and password=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        HomePage ah = new HomePage(userName);
                        ah.setTitle("CoronaNet");
                        ah.setVisible(true);
                        JOptionPane.showMessageDialog(loginButton, "Du er logget succesfuldt ind", "Meddelelse",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(loginButton, "Forkert brugernavn eller adgangskode","Meddelelse",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        buttonPanel.add(loginButton);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        titelPanel.add(label);
    }
}
