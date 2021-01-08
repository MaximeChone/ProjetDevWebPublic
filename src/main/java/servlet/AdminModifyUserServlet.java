package servlet;

import bean.UserBean;
import dao.sql.DAOFactorySQL;
import form.AdminModifyUserForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** @author - Maxime Choné **/

public class AdminModifyUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");
        String path;

        String id_user = request.getParameter("id_user");

        if (current_user != null && id_user != null) {
            if (current_user.getRole().equals("admin")) {
                int id = Integer.parseInt(id_user.trim());
                AdminModifyUserForm adminModifyUserForm = new AdminModifyUserForm(DAOFactorySQL.getInstance().getUserDAO());
                adminModifyUserForm.modifyUser(request);
                path = "/userProfile.jsp?id_user="+id;
                request.getRequestDispatcher(path).forward(request,response);
            } else {
                path = "/myProfile";
                response.sendRedirect(request.getContextPath() + path);
            }
        } else {
            path = "/index";
            response.sendRedirect(request.getContextPath() + path);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");
        String path;

        String id_user = request.getParameter("id_user");

        if (current_user != null && id_user != null) {
            if (current_user.getRole().equals("admin")) {
                int id = Integer.parseInt(id_user.trim());
                path = "adminUserProfile?id_user="+id;
                response.sendRedirect(path);
            } else {
                path = "/myProfile";
                response.sendRedirect(request.getContextPath() + path);
            }
        } else {
            path = "/index";
            response.sendRedirect(request.getContextPath() + path);
        }
    }
}

