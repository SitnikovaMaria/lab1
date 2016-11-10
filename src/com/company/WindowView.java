package com.company;

import javax.swing.JOptionPane;

public class WindowView extends javax.swing.JFrame {
    private javax.swing.JButton clearButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextArea textArea;


    public WindowView() {
        initComponents();
    }

    private void initComponents() {
        scrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        clearButton = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        textArea.setColumns(20);
        textArea.setRows(5);
        scrollPane.setViewportView(textArea);
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE,380, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(clearButton))
                        )
                        .addContainerGap()
                )
        );
        pack();
    }

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (JOptionPane.showConfirmDialog(rootPane,"Are you sure you want to clear the text?","Confirmation",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            textArea.setText("");
        }
    }

    public static void main(String[] args) {
        WindowView w = new WindowView();
        w.setVisible(true);

        // TODO code application logic here
    }
}

