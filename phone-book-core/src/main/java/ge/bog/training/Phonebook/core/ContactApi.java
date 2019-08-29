package ge.bog.training.Phonebook.core;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.sql.*;
import java.util.*;

@Singleton
@Startup
public class ContactApi {

    static final String DB_URL_ORACLE = "jdbc:oracle:thin:@192.168.43.45:1521/xe";   // jdbc:oracle:thin:@192.168.43.61:1521/orcl

    //  Database credentials
    static final String USER = "hr as normal";  //  sys as sysdba
    static final String PASS = "hr";            //  orcl

    //private static final String path = "phone-book.txt";
    private int id;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private boolean exists = false;

    public ContactApi(int id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ContactApi() {
    }

    public void addContact() {

        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(DB_URL_ORACLE, USER, PASS);

            stmt = conn.createStatement();

            stmt.executeUpdate(String.format("INSERT INTO PHONEBOOK(ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER) " +
                    " values (PhoneBookSeq.nextval, '%s', '%s', '%s')", firstName, lastName, phoneNumber));

            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ContactApi> searchContact(String value) {

        Connection conn = null;
        Statement stmt = null;

        ArrayList<ContactApi> list = new ArrayList<>();

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(DB_URL_ORACLE, USER, PASS);

            stmt = conn.createStatement();

            String sql = "SELECT ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER FROM PHONEBOOK WHERE FIRST_NAME like ? or LAST_NAME like ? or PHONE_NUMBER like ?";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                id = rs.getInt("id");

                phoneNumber = rs.getString("phone_number");

                firstName = rs.getString("first_name");

                lastName = rs.getString("last_name");

                if (firstName.contains(value) || lastName.contains(value) || phoneNumber.contains(value)) {

                    list.add(new ContactApi(id, firstName, lastName, phoneNumber));

                } else {

                    id = 0;

                    phoneNumber = null;

                    firstName = null;

                    lastName = null;
                }

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }


    public boolean isContactExist(ContactApi contacts) {

        Connection conn = null;
        Statement stmt = null;

        try {

            firstName = contacts.getfirstName();
            lastName = contacts.getlastName();
            phoneNumber = contacts.getPhoneNumber();

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(DB_URL_ORACLE, USER, PASS);

            stmt = conn.createStatement();

            String sql = String.format("SELECT ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER FROM PHONEBOOK WHERE FIRST_NAME = '%s' or LAST_NAME = '%s' or PHONE_NUMBER = '%s' " , firstName, lastName, phoneNumber);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                id = rs.getInt("id");

                phoneNumber = rs.getString("phone_number");

                firstName = rs.getString("first_name");

                lastName = rs.getString("last_name");


                if (firstName.equals(contacts.firstName) & lastName.equals(contacts.lastName) & phoneNumber.contains(contacts.phoneNumber)) {

                    exists = true;
                    break;

                } else {

                    exists = false;

                }

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
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
