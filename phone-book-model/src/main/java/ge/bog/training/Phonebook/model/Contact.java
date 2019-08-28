package ge.bog.training.Phonebook.model;

import ge.bog.training.Phonebook.core.ContactApi;

import java.util.List;

public class Contact {

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

        ContactApi contact = new ContactApi();

        contact.addContact(phoneNumber + " " + firstName + " " + lastName);

    }

    public List<ContactApi> searchContact(String sValue) {

        ContactApi contact = new ContactApi();

        List<ContactApi> result = contact.searchContact(sValue);;

        return result;
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
}
