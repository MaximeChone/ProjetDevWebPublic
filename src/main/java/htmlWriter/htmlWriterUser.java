package htmlWriter;

import javax.servlet.http.HttpServletRequest;

/** @author - Maxime Choné **/

public class htmlWriterUser {

    public static String getDeleteAccountModal(){
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
                "                    <form method=\"post\" action=\"deleteAccount\"> "+
                "                       <button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">Non</button>"+
                "                       <button type =\"submit\" class=\"btn btn-primary\">Oui</button>"+
                "                    </form>"+
                "                </div>"+
                "            </div>"+
                "        </div>"+
                "    </div>";

        return logoutModal;
    }

}
