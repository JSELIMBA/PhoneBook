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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "/PhoneBookUpdate")
public class PhoneBookUpdateServlet extends HttpServlet {

    @EJB
    private Contact contact;


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");


        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String phonenumber = req.getParameter("phonenumber");
        String rId = req.getParameter("id");

        int id = Integer.parseInt(rId);


        contact = new Contact(id, firstname, lastname, phonenumber);

        try {
            contact.updateContact();
            req.getSession().setAttribute("contactUpdateResult", "კონტაქტი განახლებულია.");
        } catch (SQLException e) {
            req.getSession().setAttribute("contactUpdateResult", "დაფიქსირდა ბაზის შეცდომა!");
            resp.sendRedirect("updateContact.jsp?u=" + rId);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("phonebookUpdate?u=" + rId);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        int id = 0;

        String rId = req.getParameter("u");
        if (rId != null) {
            id = Integer.parseInt(rId);
        }

        if (id != 0) {
            contact = new Contact(id);
            List<Contact> result = new ArrayList<>();

            try {
                result = contact.searchContact(id);

            } catch (SQLException e) {
                req.getSession().setAttribute("contactUpdateResult", "დაფიქსირდა ბაზის შეცდომა!");
                resp.sendRedirect("updateContact.jsp");
                e.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (Contact res : result) {

                req.getSession().setAttribute("id", res.getId());
                req.getSession().setAttribute("firstname", res.getFirstName());
                req.getSession().setAttribute("lastname", res.getLastName());
                req.getSession().setAttribute("phonenumber", res.getPhoneNumber());

            }

            resp.sendRedirect("updateContact.jsp");
        }

        resp.sendRedirect("updateContact.jsp");
    }
}
