package ge.bog.training.phonebook.gui;

import ge.bog.training.Phonebook.core.ContactApi;
import ge.bog.training.Phonebook.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class AddContact {
    void addContact() {

        final JFrame addContact = new JFrame("Add Contact");
        addContact.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addContact.setSize(550, 400);
        final JTextField phone = new JTextField("Phone", 10);
        final JTextField fName = new JTextField("Firs Name", 10);
        final JTextField lName = new JTextField("Last Name", 10);
        JButton buttonAdd = new JButton("Add");
        JButton buttonback = new JButton("Back");
        JPanel addPanel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        addPanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        addPanel.add(phone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        addPanel.add(fName, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        addPanel.add(lName, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        addPanel.add(buttonAdd, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        addPanel.add(buttonback, gbc);
        addContact.add(addPanel);
        addContact.setVisible(true);

        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (phone.getText().length() == 0 || fName.getText().length() == 0 || lName.getText().length() == 0) {

                    JOptionPane.showMessageDialog(null, "ყველა ველი სავალდებულოა!");

                } else {

                    Contact search = new Contact();

                    Contact contact = new Contact(phone.getText().trim(), fName.getText().trim(), lName.getText().trim());

                    ContactApi contactApi = new ContactApi(contact.getPhoneNumber(), contact.getFirstName(), contact.getLastName());

                    if (search.isContactExist(contactApi)) {

                        JOptionPane.showMessageDialog(null, "ასეთი ნომერი უკვე არსებობს!");

                    } else {

                        contact.addContact();

                        JOptionPane.showMessageDialog(null, "ნომერი დამატებულია!");
                    }
                }

            }
        });

        buttonback.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                PhoneBook phoneBook = new PhoneBook();
                phoneBook.run();

                addContact.setVisible(false);
            }
        });

    }
}
