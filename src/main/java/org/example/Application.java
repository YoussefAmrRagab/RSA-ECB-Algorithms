package org.example;

import javax.swing.*;
import java.awt.*;

public class Application {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cyber Security Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RSA rsa = new RSA();
        ECB ecb = new ECB();
        SHA1 sha1 = new SHA1();

        int textAreaWidth = 300;
        int textAreaHeight = 100;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JTextArea textArea1 = new JTextArea();
        textArea1.setForeground(Color.BLACK);
        textArea1.setLineWrap(true);

        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        scrollPane1.setBounds(50, 30, textAreaWidth, textAreaHeight);
        panel.add(scrollPane1);

        JTextArea textArea2 = new JTextArea();
        textArea2.setForeground(Color.BLACK);
        textArea2.setEditable(false);
        textArea2.setLineWrap(true);

        JScrollPane scrollPane2 = new JScrollPane(textArea2);
        scrollPane2.setBounds(50, 140, textAreaWidth, textAreaHeight);
        panel.add(scrollPane2);

        JCheckBox rsaCheckBox = new JCheckBox("RSA Algorithm");
        rsaCheckBox.setBounds(50, 250, 150, 30);
        panel.add(rsaCheckBox);

        JCheckBox ecbCheckBox = new JCheckBox("ECB Algorithm");
        ecbCheckBox.setBounds(50, 280, 150, 30);
        panel.add(ecbCheckBox);

        JCheckBox sha1CheckBox = new JCheckBox("SHA-1 Algorithm");
        sha1CheckBox.setBounds(50, 310, 150, 30);
        panel.add(sha1CheckBox);

        rsaCheckBox.addItemListener(e -> {
            if (rsaCheckBox.isSelected()) {
                ecbCheckBox.setSelected(false);
                sha1CheckBox.setSelected(false);
            }
        });

        ecbCheckBox.addItemListener(e -> {
            if (ecbCheckBox.isSelected()) {
                rsaCheckBox.setSelected(false);
                sha1CheckBox.setSelected(false);
            }
        });

        sha1CheckBox.addItemListener(e -> {
            if (sha1CheckBox.isSelected()) {
                rsaCheckBox.setSelected(false);
                ecbCheckBox.setSelected(false);
            }
        });

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.setBounds(360, 30, 100, 30);
        encryptButton.addActionListener(e -> {
            if (!rsaCheckBox.isSelected() && !ecbCheckBox.isSelected() && !sha1CheckBox.isSelected()) {
                JOptionPane.showMessageDialog(frame, "Please select an encryption algorithm.");
                return;
            }

            String plainText = textArea1.getText();
            if (rsaCheckBox.isSelected()) {
                String cipherText = rsa.encrypt(plainText);
                textArea2.setText(cipherText);
            } else if (ecbCheckBox.isSelected()) {
                String cipherText = ecb.encrypt(plainText);
                textArea2.setText(cipherText);
            } else if (sha1CheckBox.isSelected()) {
                String cipherText = sha1.hash(plainText);
                textArea2.setText(cipherText);
            }
        });
        panel.add(encryptButton);

        JButton decryptButton = new JButton("Decrypt");
        decryptButton.setBounds(360, 70, 100, 30);
        decryptButton.addActionListener(e -> {
            if (sha1CheckBox.isSelected()) {
                JOptionPane.showMessageDialog(frame, "SHA-1 does not support decryption.");
                return;
            }

            if (!rsaCheckBox.isSelected() && !ecbCheckBox.isSelected()) {
                JOptionPane.showMessageDialog(frame, "Please select an decryption algorithm.");
                return;
            }

            try {
                String cipherText = textArea1.getText();
                if (rsaCheckBox.isSelected()) {
                    String plainText = rsa.decrypt(cipherText);
                    textArea2.setText(plainText);
                } else if (ecbCheckBox.isSelected()) {
                    String plainText = ecb.decrypt(cipherText);
                    textArea2.setText(plainText);
                }
            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(frame, "Invalid input, cipher text must be integers only.");
            } catch (Exception error) {
                JOptionPane.showMessageDialog(frame, "Invalid input.");
            }
        });
        panel.add(decryptButton);

        frame.add(panel);
        frame.setSize(500, 400);
        frame.setVisible(true);
    }
}
