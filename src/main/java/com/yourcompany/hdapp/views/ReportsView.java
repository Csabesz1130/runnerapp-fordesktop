package com.yourcompany.hdapp.views;

import com.yourcompany.hdapp.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ReportsView extends JPanel {
    private MainFrame mainFrame;

    public ReportsView(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Reports", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea("View and generate reports here.");
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(e -> mainFrame.showView("DashboardView"));
        add(goBackButton, BorderLayout.SOUTH);
    }
}
