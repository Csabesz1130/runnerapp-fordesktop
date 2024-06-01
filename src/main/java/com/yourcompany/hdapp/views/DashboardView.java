package com.yourcompany.hdapp.views;

import com.yourcompany.hdapp.MainFrame;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JPanel {
    private MainFrame mainFrame;

    public DashboardView(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea("Welcome to the Dashboard!");
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(e -> mainFrame.showView("LoginView"));
        add(goBackButton, BorderLayout.SOUTH);
    }
}
