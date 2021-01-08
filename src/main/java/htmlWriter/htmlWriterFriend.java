package htmlWriter;

import bean.UserBean;
import dao.sql.DAOFactorySQL;

import javax.servlet.http.HttpServletRequest;

/** @author - Maxime Choné **/

public class htmlWriterFriend {

    public static String getFriends(HttpServletRequest request){
        StringBuilder retour = new StringBuilder();

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        if(current_user != null) {

            UserBean[] friends = DAOFactorySQL.getInstance().getUserDAO().friendList(current_user);


            for (UserBean userBean : friends) {
                String mail = userBean.getMail();
                String name = userBean.getLastName();
                String firstname = userBean.getFirstName();
                int id = userBean.getId();
                String friend = "<!-- Earnings (Monthly) Card Example -->" +
                        "                                        <div class=\"mb-2\">" +
                        "                                            <div class=\"card border-left-primary shadow h-100 py-2\">" +
                        "                                                <div class=\"card-body\">" +
                        "                                                    <div class=\"row no-gutters align-items-center\">" +
                        "                                                        <div class=\"col mr-2\">" +
                        "                                                            <div class=\"text-xs font-weight-bold text-primary text-uppercase mb-1\">" +
                        "                                                                " + mail + "</div>" +
                        "                                                            <div class=\"h5 mb-0 font-weight-bold text-gray-800\">" + firstname + " " + name + "</div>" +
                        "                                                        </div>" +
                        "                                                        <div class=\"col-auto\">" +
                        "                                                           <form id=\"formDeleteAmi"+ id+"\" name=\"formDemandeAmi"+ id +"\" method=\"post\" action=\"deleteFriend\">" +
                        "                                                               <input type=\"hidden\" name=\"id_user_to_delete\" value=\""+id+"\" />" +
                        "                                                               <button type=\"submit\" class=\"btn btn-primary\" type=\"button\">"+
                        "                                                                   <i class=\"fas fa-minus fa-sm\"></i>"+
                        "                                                               </button>" +
                        "                                                           </form>" +
                        "                                                        </div>" +
                        "                                                    </div>" +
                        "                                                </div>" +
                        "                                            </div>" +
                        "                                        </div>";

                retour.append(friend);
            }

        }


        return retour.toString();
    }

    public static String getFriendRequested(HttpServletRequest request){
        StringBuilder retour = new StringBuilder();

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        if(current_user != null) {

            UserBean[] friends = DAOFactorySQL.getInstance().getUserDAO().friendRequested(current_user);


            for (UserBean userBean : friends) {
                String mail = userBean.getMail();
                String name = userBean.getLastName();
                String firstname = userBean.getFirstName();

                int id = userBean.getId();

                String friend = "<!-- Earnings (Monthly) Card Example -->" +
                        "                                        <div class=\"mb-2\">" +
                        "                                            <div class=\"card border-left-danger shadow h-100 py-2\">" +
                        "                                                <div class=\"card-body\">" +
                        "                                                    <div class=\"row no-gutters align-items-center\">" +
                        "                                                        <div class=\"col mr-2\">" +
                        "                                                            <div class=\"text-xs font-weight-bold text-danger text-uppercase mb-1\">" +
                        "                                                                " + mail + "</div>" +
                        "                                                            <div class=\"h5 mb-0 font-weight-bold text-gray-800\">" + firstname + " " + name + "</div>" +
                        "                                                        </div>" +
                        "                                                        <div class=\"col-auto\">" +
                        "                                                           <form id=\"formCancelFriendRequest"+ id+"\" method=\"post\" action=\"cancelFriendRequest\">" +
                        "                                                               <input type=\"hidden\" name=\"id_user_to_cancel\" value=\""+id+"\" />" +
                        "                                                               <button type=\"submit\" class=\"btn btn-danger\" type=\"button\">"+
                        "                                                                   <i class=\"fas fa-times fa-sm\"></i>" +
                        "                                                               </button>" +
                        "                                                           </form>" +
                        "                                                         </div>" +
                        "                                                    </div>" +
                        "                                                </div>" +
                        "                                            </div>" +
                        "                                        </div>";

                retour.append(friend);
            }

        }


        return retour.toString();
    }

    public static String getFriendRequesting(HttpServletRequest request){
        StringBuilder retour = new StringBuilder();

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        if(current_user != null) {

            UserBean[] friends = DAOFactorySQL.getInstance().getUserDAO().friendRequesting(current_user);


            for (UserBean userBean : friends) {
                String mail = userBean.getMail();
                String name = userBean.getLastName();
                String firstname = userBean.getFirstName();
                int id = userBean.getId();

                String friend = "<!-- Earnings (Monthly) Card Example -->" +
                        "                                        <div class=\"mb-2\">" +
                        "                                            <div class=\"card border-left-warning shadow h-100 py-2\">" +
                        "                                                <div class=\"card-body\">" +
                        "                                                    <div class=\"row no-gutters align-items-center\">" +
                        "                                                        <div class=\"col mr-2\">" +
                        "                                                            <div class=\"text-xs font-weight-bold text-warning text-uppercase mb-1\">" +
                        "                                                                " + mail + "</div>" +
                        "                                                            <div class=\"h5 mb-0 font-weight-bold text-gray-800\">" + firstname + " " + name + "</div>" +
                        "                                                        </div>" +
                        "                                                           <div class=\"btn-toolbar\" role=\"toolbar\" aria-label=\"Toolbar with button groups\"> " +
                        "                                                           <div class=\"col-auto\">" +
                        "                                                               <form id=\"formAcceptFriendRequest"+ id+"\" method=\"post\" action=\"acceptFriendRequest\">" +
                        "                                                                   <input type=\"hidden\" name=\"id_user_to_accept\" value=\""+id+"\" />" +
                        "                                                                   <button type=\"submit\" class=\"btn btn-warning\" type=\"button\">"+
                        "                                                                      <i class=\"fas fa-check fa-sm\"></i>" +
                        "                                                                   </button>" +
                        "                                                               </form>" +
                        "                                                           </div>" +
                        "                                                           <div class=\"col-auto\">" +
                        "                                                               <form id=\"formRefuseFriendRequest"+ id+"\" method=\"post\" action=\"refuseFriendRequest\">" +
                        "                                                                   <input type=\"hidden\" name=\"id_user_to_refuse\" value=\""+id+"\" />" +
                        "                                                                   <button type=\"submit\" class=\"btn btn-warning\" type=\"button\">"+
                        "                                                                       <i class=\"fas fa-times fa-sm\"></i>" +
                        "                                                                   </button>" +
                        "                                                               </form>" +
                        "                                                           </div>" +
                        "                                                           </div>"+
                        "                                                    </div>" +
                        "                                                </div>" +
                        "                                            </div>" +
                        "                                        </div>";

                retour.append(friend);
            }

        }


        return retour.toString();
    }

}
