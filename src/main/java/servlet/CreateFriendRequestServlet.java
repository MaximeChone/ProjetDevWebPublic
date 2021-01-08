package servlet;

import bean.UserBean;
import dao.sql.DAOFactorySQL;
import form.ConnexionForm;
import form.DemandeAmiForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** @author - Maxime Choné **/

public class CreateFriendRequestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");


        UserBean current_user = (UserBean)request.getSession().getAttribute("current_user");
        String path = "/myFriends.jsp";

        if(current_user != null) {

            DemandeAmiForm demandeAmiForm = new DemandeAmiForm(DAOFactorySQL.getInstance().getUserDAO(), DAOFactorySQL.getInstance().getFriendRequestDAO());
            demandeAmiForm.envoyerDemandeAmi(request);

        }

        request.getRequestDispatcher(path).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String path = "/myFriends.jsp";

        response.sendRedirect(request.getContextPath() + path);

    }
}
