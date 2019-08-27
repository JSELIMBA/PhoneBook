package ge.bog.training.phonebook.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhoneBook {

    public static void main(String[] args) {

        run();
    }

    static void run() {

        final JFrame mainFrame = new JFrame("Phone Book");

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(550, 400);
        JButton buttonAddContact = new JButton("Add Contact");
        JButton buttonSearchContact = new JButton("Search Contact");
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(buttonAddContact, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(buttonSearchContact, gbc);
        mainFrame.add(panel);
        mainFrame.setVisible(true);

        buttonAddContact.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                AddContact contact = new AddContact();

                contact.addContact();

                mainFrame.setVisible(false);

            }
        });

        buttonSearchContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                SearchContact contact = new SearchContact();

                contact.searchContact();

                mainFrame.setVisible(false);
            }
        });
    }
}
