package ge.bog.training.phonebook.web;

import ge.bog.training.Phonebook.core.ContactApi;
import ge.bog.training.Phonebook.model.Contact;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/PhonebookServlet")
public class PhonebookServlet extends HttpServlet {

    @EJB
    private Contact contact;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        req.getSession().setAttribute("contactSearchResult", "");
        String sValue = req.getParameter("sValue");

        if (sValue.length() != 0) {
            List<ContactApi> result = contact.searchContact(sValue);

            String contacts = "";

            for (ContactApi res : result) {

                if (res.getfirstName().length() == 0) {
                    req.getSession().setAttribute("contactSearchResult", "კონტაქტი არ მოიძებნა");

                } else {

                    contacts = contacts + res.getfirstName() + " " + res.getlastName() + " " + res.getPhoneNumber() + "<br/>";

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

        contact = new Contact(phonenumber, firstname, lastname);

        ContactApi contactApi = new ContactApi(contact.getPhoneNumber(), contact.getFirstName(), contact.getLastName());

        if (contact.isContactExist(contactApi)) {

            req.getSession().setAttribute("contactAddResult", "კონტაქტი უკვე არსებობს");

        } else {
            contact.addContact();
        }

        resp.sendRedirect("addContact.jsp");
    }
}
