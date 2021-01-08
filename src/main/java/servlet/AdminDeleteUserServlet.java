package servlet;

import bean.UserBean;
import dao.sql.DAOFactorySQL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** @author - Maxime Choné **/

public class AdminDeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        String path;
        path = "/index";
        response.sendRedirect(request.getContextPath() + path);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        UserBean current_user = (UserBean)request.getSession().getAttribute("current_user");
        String path;

        String id_user = request.getParameter("id_user");

        if(current_user != null){
            if(current_user.getRole().equals("admin") && id_user != null){
                UserBean userToDelete = DAOFactorySQL.getInstance().getUserDAO().load(Integer.parseInt(id_user.trim()));
                if(userToDelete != null){
                    if(userToDelete.getId() != current_user.getId()){
                        DAOFactorySQL.getInstance().getUserDAO().delete(userToDelete);
                    }
                }
            }
            path = "/adminUsers";
            response.sendRedirect(request.getContextPath() + path);
        } else {
            path = "/index";
            response.sendRedirect(request.getContextPath() + path);
        }
    }
}
