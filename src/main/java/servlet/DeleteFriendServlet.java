package servlet;

import bean.FriendBean;
import bean.UserBean;
import dao.FriendDAO;
import dao.UserDAO;
import dao.sql.DAOFactorySQL;
import dao.sql.UserDAOsql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** @author - Maxime Choné **/

public class DeleteFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        UserDAO userDAO = DAOFactorySQL.getInstance().getUserDAO();
        FriendDAO friendDAO = DAOFactorySQL.getInstance().getFriendRequestDAO();

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");
        String path = "/myFriends.jsp";


        if(current_user != null){
            if(request.getParameter("id_user_to_delete") != null) {

                UserBean friendToDelete = (UserBean) userDAO.load(Integer.parseInt(request.getParameter("id_user_to_delete")));
                if (friendToDelete != null) {
                    if (friendDAO.exist(current_user.getId(), friendToDelete.getId())) {
                        FriendBean friendRelation = friendDAO.loadFriend(current_user.getId(), friendToDelete.getId());
                        friendDAO.deleteFriend(friendRelation);
                    }
                    if (friendDAO.exist(friendToDelete.getId(), current_user.getId())) {
                        FriendBean friendRelation = friendDAO.loadFriend(friendToDelete.getId(), current_user.getId());
                        friendDAO.deleteFriend(friendRelation);
                    }
                }
            }
        }

        request.getRequestDispatcher(path).forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        String path = "/myFriends.jsp";

        response.sendRedirect(request.getContextPath() + path);
    }
}
