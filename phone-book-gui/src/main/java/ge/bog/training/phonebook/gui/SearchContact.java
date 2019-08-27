package ge.bog.training.phonebook.gui;

import ge.bog.training.PhoneBook.core.ContactApi;
import ge.bog.training.PhoneBook.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchContact {


    private JList<String> list;

    public JList JlistContact(ContactApi contact) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement(contact.getPhoneNumber() + " " + contact.getfirstName() + " " + contact.getlastName());

        list = new JList<>(listModel);

        return list;
    }

    public void searchContact() {


        final JFrame searchContact = new JFrame("Search Contact");
        searchContact.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchContact.setSize(550, 400);
        final JTextField name = new JTextField("Search", 10);
        final JTextField phone = new JTextField(10);
        final JTextField fName = new JTextField(10);
        final JTextField lName = new JTextField(10);
        JLabel label = new JLabel("Search Value:");
        JButton buttonSearch = new JButton("Search Contact");
        JButton buttonBack = new JButton("Back");
        JPanel searchPane = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        searchPane.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        searchPane.add(label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        searchPane.add(name, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        searchPane.add(buttonSearch, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 1;
        searchPane.add(buttonBack, gbc);
        searchContact.add(searchPane);
        searchContact.setVisible(true);


        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (name.getText().length() == 0) {

                    JOptionPane.showMessageDialog(null, "საძიებო ველი ცარიელია!");

                } else {

                    Contact search = new Contact();

                    List<ContactApi> result = search.searchContact(name.getText());

                    int i = 3;
                    for (ContactApi res : result) {

                        if (res.getfirstName() == null) {

                            phone.setText("");
                            fName.setText("");
                            lName.setText("");
                            JOptionPane.showMessageDialog(null, "კონტაქტი არ მოიძებნა.");
                        } else {


                            JList<ContactApi> list = JlistContact(res);
                            gbc.fill = GridBagConstraints.HORIZONTAL;
                            gbc.gridx = 1;
                            gbc.gridy = i;
                            i++;
                            searchPane.add(list, gbc);
                            searchContact.add(searchPane);
                            searchContact.setVisible(true);

                        }
                    }
                }
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                PhoneBook phoneBook = new PhoneBook();
                phoneBook.run();

                searchContact.setVisible(false);
            }
        });

    }
}
