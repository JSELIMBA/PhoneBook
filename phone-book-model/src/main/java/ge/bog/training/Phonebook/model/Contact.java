package ge.bog.training.Phonebook.model;

import ge.bog.training.Phonebook.core.ContactApi;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local
public class Contact {

    private int id;
    private String phoneNumber;
    private String firstName;
    private String lastName;

    public Contact (String phoneNumber, String firstName, String lastName) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact() {
    }


    public void addContact() {

        ContactApi contact = new ContactApi(id, firstName, lastName, phoneNumber);


        contact.addContact();

    }

    public List<ContactApi> searchContact(String sValue) {

        ContactApi contact = new ContactApi();

        List<ContactApi> result = contact.searchContact(sValue);;

        return result;
    }

    public boolean isContactExist(ContactApi contacts) {

        ContactApi contact = new ContactApi();

        return contact.isContactExist(contacts);
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
