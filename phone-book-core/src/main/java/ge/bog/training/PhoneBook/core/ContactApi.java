package ge.bog.training.PhoneBook.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ContactApi {
    private static String path = "phone-book.txt";
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private static int index = 0;

    public ContactApi(String phoneNumber, String firsName, String lastName) {
        this.phoneNumber = phoneNumber;
        this.firstName = firsName;
        this.lastName = lastName;
    }

    public ContactApi() {
    }

    public void addContact(String data) {

        try {
            FileWriter output = new FileWriter(path, true);
            output.write("\n" + data);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ContactApi> searchContact(String value) {

        ArrayList<ContactApi> list = new ArrayList<>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNext()) {

            phoneNumber = scanner.next();

            firstName = scanner.next();

            lastName = scanner.next();

            if (firstName.contains(value) || lastName.contains(value) || phoneNumber.contains(value)) {

                list.add(new ContactApi(phoneNumber, firstName, lastName));

            } else {
                phoneNumber = null;

                firstName = null;

                lastName = null;
            }

        }

        scanner.close();

        return list;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getfirstName() {
        return firstName;
    }

    public String getlastName() {
        return lastName;
    }
}
