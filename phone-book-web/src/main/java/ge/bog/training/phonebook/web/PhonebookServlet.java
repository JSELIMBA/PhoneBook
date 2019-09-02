package ge.bog.training.phonebook.web;

import ge.bog.training.phonebook.model.Contact;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/PhonebookServlet")
public class PhonebookServlet extends HttpServlet {

    @EJB
    private Contact contacts;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        req.getSession().setAttribute("contactSearchResult", "");
        String sValue = req.getParameter("sValue");

        if (sValue.length() != 0) {
            List<Contact> result = null;
            try {
                result = contacts.searchContact(sValue);
            } catch (SQLException e) {
                req.getSession().setAttribute("contactSearchResult", "დაფიქსირდა ბაზის შეცდომა!");
                resp.sendRedirect("searchContact.jsp");
                e.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }

            String contacts = "";

            for (Contact res : result) {

                if (res.getFirstName().length() == 0) {
                    req.getSession().setAttribute("contactSearchResult", "კონტაქტი არ მოიძებნა");

                } else {

                    contacts = contacts + (
                            "    <tr>\n" +
                                    "        <td>" + res.getId() + "</td>\n" +
                                    "        <td>" + res.getFirstName() + "</td>\n" +
                                    "        <td>" + res.getLastName() + "</td>\n" +
                                    "        <td>" + res.getPhoneNumber() + "</td>\n" +
                                    "        <td>" +
                                    "    <button onclick=\"window.location.href='/phone-book-web-1.0/phonebookUpdate?u=" + res.getId() + "' \">რედაქტირება</button>" +
                                    "        <td>" +
                                    "    </tr>");

                    req.getSession().setAttribute("contactSearchResult", contacts);
                }
            }
            if (contacts.length() == 0) {

                req.getSession().setAttribute("contactSearchResult", "კონტაქტი არ მოიძებნა");
            }

        } else {

            req.getSession().setAttribute("contactSearchResult", "საძიებო ველი ცარიელია!");
        }

        resp.sendRedirect("searchContact.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String phonenumber = req.getParameter("phonenumber");

        req.getSession().setAttribute("contactAddResult", "კონტაქტი დამატებულია");

        contacts = new Contact(phonenumber, firstname, lastname);

        try {
            if (contacts.isContactExist(contacts)) {

                req.getSession().setAttribute("contactAddResult", "კონტაქტი უკვე არსებობს");

            } else {
                contacts.addContact();
            }
        } catch (SQLException e) {
            req.getSession().setAttribute("contactAddResult", "დაფიქსირდა ბაზის შეცდომა!");
            resp.sendRedirect("addContact.jsp");
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("addContact.jsp");
    }
}
