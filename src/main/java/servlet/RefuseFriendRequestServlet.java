package servlet;

import bean.FriendBean;
import bean.UserBean;
import dao.FriendDAO;
import dao.UserDAO;
import dao.sql.DAOFactorySQL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** @author - Maxime Choné **/

public class RefuseFriendRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        UserDAO userDAO = DAOFactorySQL.getInstance().getUserDAO();
        FriendDAO friendDAO = DAOFactorySQL.getInstance().getFriendRequestDAO();

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");
        String path = "/myFriends.jsp";


        if(current_user != null){
            if(request.getParameter("id_user_to_refuse") != null) {

                UserBean friendToRefuse = (UserBean) userDAO.load(Integer.parseInt(request.getParameter("id_user_to_refuse")));
                if (friendToRefuse != null) {
                    if (friendDAO.exist(friendToRefuse.getId(), current_user.getId())) {
                        FriendBean friendRelation = friendDAO.loadFriend(friendToRefuse.getId(), current_user.getId());
                        friendDAO.refuseFriendRequest(friendRelation);
                    }
                }
            }
        }

        request.getRequestDispatcher(path).forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/myFriends.jsp";
        response.sendRedirect(request.getContextPath() + path);
    }
}
