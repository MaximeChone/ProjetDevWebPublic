package htmlWriter;

import bean.UserBean;
import dao.sql.DAOFactorySQL;

import javax.servlet.http.HttpServletRequest;

/** @author - Maxime Choné **/

public class htmlWriterAdminUser {


    public static String getUsersTable(HttpServletRequest request){
        String html = "<div class=\"table-responsive\">"+
                "                                <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">"+
                "                                    <thead>"+
                "                                        <tr>"+
                "                                            <th>id</th>"+
                "                                            <th>Email</th>"+
                "                                            <th>Nom</th>"+
                "                                            <th>Prénom</th>"+
                "                                            <th>Date de naissance</th>"+
                "                                            <th>Rôle</th>"+
                "                                        </tr>"+
                "                                    </thead>"+
                "                                    <tbody>";

                StringBuilder sb = new StringBuilder();

                UserBean[] users = DAOFactorySQL.getInstance().getUserDAO().loadAll();

                for (UserBean u:users) {

                    sb.append("<tr>");

                    sb.append("<td>");
                    sb.append("<a href=\"adminUserProfile?id_user="+u.getId()+"\">"+u.getId()+"</a>");
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append(u.getMail());
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append(u.getLastName());
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append(u.getFirstName());
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append(u.getBirthdate());
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append(u.getRole());
                    sb.append("</td>");

                    sb.append("</tr>");
                }
                html += sb.toString();
                html +=
                "                                    </tbody>"+
                "                                </table>"+
                "                            </div>";
                return html;
    }

    public static String getDeleteAccountModal(HttpServletRequest request){
        String logoutModal = "<!-- Delete account Modal-->"+
                "    <div class=\"modal fade\" id=\"deleteAccountModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleLabel\""+
                "        aria-hidden=\"true\">"+
                "        <div class=\"modal-dialog\" role=\"document\">"+
                "            <div class=\"modal-content\">"+
                "                <div class=\"modal-header\">"+
                "                    <h5 class=\"modal-title\" id=\"deleteLabel\">Êtes vous sûr de vouloir supprimer ce compte ?</h5>"+
                "                    <button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"Close\">"+
                "                        <span aria-hidden=\"true\">×</span>"+
                "                    </button>"+
                "                </div>"+
                "                <div class=\"modal-body\">Appuyez sur \"Oui\" pour supprimer définitivement ce compte.</div>"+
                "                <div class=\"modal-footer\">"+
                "                    <button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">Non</button>"+
                "                    <a class=\"btn btn-primary\" href=\"adminDeleteUser?id_user="+ request.getParameter("id_user")+"\">Oui</a>"+
                "                </div>"+
                "            </div>"+
                "        </div>"+
                "    </div>";

        return logoutModal;
    }

}
