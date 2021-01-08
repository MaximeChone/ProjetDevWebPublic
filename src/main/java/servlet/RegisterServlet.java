package servlet;

import dao.sql.DAOFactorySQL;
import form.EnregistrementForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** @author - Maxime Choné **/

public class RegisterServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        EnregistrementForm enregistrementForm= new EnregistrementForm(DAOFactorySQL.getInstance().getUserDAO());
        enregistrementForm.creerCompteUtilisateur(request);

        String path;
        if(enregistrementForm.getResultat().equals("Enregistrement réussi !")){
            path = "/login";
            response.sendRedirect(request.getContextPath() + path);
        } else {
            path = "/register.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String path = "/register.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }
}
