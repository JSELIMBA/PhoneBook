package ge.bog.training.phonebook.model;


import ge.bog.training.phonebook.core.ContactApi;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local
public class Contact {

    int index = 0;
    private int id;
    private String phoneNumber;
    private String firstName;
    private String lastName;

    public Contact (String firstName, String lastName, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact (int id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact (int id) {
        this.id = id;
    }

    public Contact() {
    }


    public void addContact() throws Exception {

        ContactApi contact = new ContactApi(firstName, lastName, phoneNumber);


        try {
            contact.addContact();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateContact() throws Exception {

        ContactApi contact = new ContactApi(id, firstName, lastName, phoneNumber);

        contact.updateContact();

    }

    public List<Contact> searchContact(String sValue) throws Exception {

        ContactApi contacts = new ContactApi();

        List<Contact> result = new ArrayList<>();

        List<ContactApi> res = contacts.searchContact(sValue);


        for (ContactApi results: res
             ) {

            result.add(new Contact(res.get(index).getId() ,res.get(index).getFirstName(),res.get(index).getLastName(),res.get(index).getPhoneNumber()));
            index++;
        }

        return result;
    }

    public List<Contact> searchContact(int id) throws Exception {

        ContactApi contact = new ContactApi();

        List<Contact> result = new ArrayList<>();

        List<ContactApi> res =  contact.searchContact(id);

        for (ContactApi results: res
        ) {
            result.add(new Contact(res.get(index).getId() ,res.get(index).getFirstName(),res.get(index).getLastName(),res.get(index).getPhoneNumber()));
        }

        return result;
    }

    public boolean isContactExist(Contact contacts) throws Exception{

        ContactApi contact = new ContactApi(contacts.firstName, contacts.lastName, contacts.phoneNumber);

        return contact.isContactExist(contact);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }
}
