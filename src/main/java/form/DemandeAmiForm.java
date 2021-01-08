package form;

import bean.FriendBean;
import bean.UserBean;
import dao.FriendDAO;
import dao.UserDAO;

import javax.servlet.http.HttpServletRequest;

/** @author - Maxime Choné **/

public class DemandeAmiForm {

    private static final String CHAMP_EMAIL = "email";

    private String resultat;
    private String erreur;

    private UserDAO userDAOo;
    private FriendDAO friendDAO;

    public DemandeAmiForm(UserDAO userDAO,FriendDAO friendDAO){
        this.userDAOo = userDAO;
        this.friendDAO = friendDAO;
    }

    public void envoyerDemandeAmi(HttpServletRequest request){
        String email = getValeurChamp(request,"email");
        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        traiterEmail(email,current_user);

        if(erreur == null){
            UserBean userToInvite = userDAOo.load(email);
             FriendBean friendRequest = new FriendBean();
                friendRequest.setId_to(userToInvite.getId());
                friendRequest.setId_from(current_user.getId());
                friendDAO.createFriendRequest(friendRequest);
                resultat = "Demande d'ami envoyée";
                request.setAttribute("erreur_demandeAmi",null);

        } else {
            request.setAttribute("erreur_demandeAmi",erreur);
            resultat = "échec de la création de la demande d'ami";
        }
    }

    private void traiterEmail(String email,UserBean current_user){
        try{
            validationEmail(email,current_user);
        } catch (FormValidationException e) {
            erreur = e.getMessage();
        }
    }

    private void validationEmail(String email,UserBean current_user) throws FormValidationException {
        if(email != null){
            if(!userDAOo.exist(email)){
                throw new FormValidationException("L'utilisateur n'existe pas.");
            } else {
                if(friendDAO.exist(current_user.getId(),userDAOo.load(email).getId())){
                    throw new FormValidationException("Vous êtes déjà en relation avec cet utilisateur.");
                } else if (userDAOo.load(email).getId() == current_user.getId()){
                    throw new FormValidationException("Vous êtes déjà ami avec vous même, au fond de votre cœur.");
                }
            }
        } else {
            throw new FormValidationException("Merci de saisir une adresse mail.");
        }

    }

    private String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }
    public String getResultat() {
        return resultat;
    }
}
