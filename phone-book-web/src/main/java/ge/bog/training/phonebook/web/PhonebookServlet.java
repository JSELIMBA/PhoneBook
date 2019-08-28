package ge.bog.training.phonebook.web;

import ge.bog.training.Phonebook.model.Contact;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PhonebookServlet extends HttpServlet {

    @Inject
    private Contact phoneBookApi;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        System.out.println("Get method called");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String phonenumber = req.getParameter("phonenumber");

        req.getSession().setAttribute("nameOfLastEnteredContact", firstname);

        System.out.println(firstname + " ; " + lastname + "; " + phonenumber);

        phoneBookApi = new Contact(phonenumber, firstname, lastname);
        phoneBookApi.addContact();

        resp.sendRedirect("index.jsp");
    }
}
