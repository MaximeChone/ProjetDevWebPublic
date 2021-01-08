package servlet;

import bean.UserBean;
import dao.sql.DAOFactorySQL;
import form.ModifyUserForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** @author - Maxime Choné **/

public class ModifyUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        UserBean current_user = (UserBean)request.getSession().getAttribute("current_user");

        if(current_user != null) {
            ModifyUserForm modifyUser = new ModifyUserForm(DAOFactorySQL.getInstance().getUserDAO());
            modifyUser.modifyUser(request);
        }
        String path;

        path = "/myProfile.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        UserBean current_user = (UserBean)request.getSession().getAttribute("current_user");
        String path;


        if(current_user != null){
            path = "/myProfile.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        } else {
            path = "/index";
            response.sendRedirect(request.getContextPath() + path);
        }
    }
}
