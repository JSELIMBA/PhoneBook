package ge.bog.training.phonebook.core;


import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.sql.*;
import java.util.ArrayList;

@Singleton
@Startup
public class ContactApi {

    static final String DB_URL_ORACLE = "jdbc:oracle:thin:@192.168.0.74:1521/xe";   // 92.168.43.61:1521/orcl  //  192.168.0.74:1521/xe

    //  Database credentials
    static final String USER = "hr as normal";  //  sys as sysdba // hr as normal
    static final String PASS = "hr";            //  orcl          // hr

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

    public ContactApi(String firstName, String lastName, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ContactApi() {
    }

    public void addContact() throws Exception {

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
            throw new SQLException();
        }
    }

    public void updateContact() throws Exception {

        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(DB_URL_ORACLE, USER, PASS);

            stmt = conn.createStatement();

            stmt.executeUpdate(String.format("UPDATE PHONEBOOK SET FIRST_NAME = '%s' , LAST_NAME = '%s' , PHONE_NUMBER = '%s' WHERE ID = %d"
                    , firstName, lastName, phoneNumber, id));

            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }

    public ArrayList<ContactApi> searchContact(int id) throws Exception {

        Connection conn = null;
        Statement stmt = null;

        ArrayList<ContactApi> list = new ArrayList<>();

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(DB_URL_ORACLE, USER, PASS);

            stmt = conn.createStatement();

            String sql = String.format("SELECT ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER FROM PHONEBOOK WHERE ID = '%d' ", id);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                id = rs.getInt("id");

                phoneNumber = rs.getString("phone_number");

                firstName = rs.getString("first_name");

                lastName = rs.getString("last_name");

                list.add(new ContactApi(id, firstName, lastName, phoneNumber));

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }


        return list;
    }

    public ArrayList<ContactApi> searchContact(String value) throws Exception {

        Connection conn = null;
        Statement stmt = null;

        ArrayList<ContactApi> list = new ArrayList<>();

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(DB_URL_ORACLE, USER, PASS);

            stmt = conn.createStatement();

            String sql = String.format("SELECT ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER FROM PHONEBOOK WHERE FIRST_NAME = '%s' or LAST_NAME = '%s' or PHONE_NUMBER = '%s'", value, value, value);

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
            throw new SQLException();
        }


        return list;
    }

    public boolean isContactExist(ContactApi contact) throws Exception{

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(DB_URL_ORACLE, USER, PASS);

            stmt = conn.createStatement();

            String sql = String.format("SELECT ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER FROM PHONEBOOK WHERE FIRST_NAME = '%s' and LAST_NAME = '%s' and PHONE_NUMBER = '%s' " , contact.firstName, contact.lastName, contact.phoneNumber);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                id = rs.getInt("id");

                //phoneNumber = rs.getString("phone_number");

                //firstName = rs.getString("first_name");

                //lastName = rs.getString("last_name");


                if (!firstName.isEmpty()) //& lastName.equals(lastName) & phoneNumber.contains(phoneNumber))
                {

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
            throw new SQLException();
        }

        return exists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
