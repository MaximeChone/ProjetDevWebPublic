package form;


import bean.UserBean;
import dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/** @author - Maxime Choné **/

public class ModifyUserForm {


    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS  = "password";
    private static final String CHAMP_CONF  = "confirmation";
    private static final String CHAMP_NAME  = "name";
    private static final String CHAMP_FISTNAME  = "firstname";
    private static final String CHAMP_BIRTH  = "birthdate";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    private UserDAO userDAOo;
    private UserBean current_user;

    public ModifyUserForm(UserDAO userDAO){
        this.userDAOo = userDAO;
    }

    public void modifyUser(HttpServletRequest request){
        String email = getValeurChamp(request,CHAMP_EMAIL);
        String password = getValeurChamp(request,CHAMP_PASS);
        String confirmation = getValeurChamp(request, CHAMP_CONF);
        String name = getValeurChamp(request, CHAMP_NAME);
        String firstname = getValeurChamp(request,CHAMP_FISTNAME);
        String birthdate = getValeurChamp(request,CHAMP_BIRTH);

        current_user = (UserBean)request.getSession().getAttribute("current_user");

        traiterEmail(email);
        traiterName(name,firstname);
        traiterPassword(password,confirmation);

        if(erreurs.isEmpty()){
            UserBean user = new UserBean();

            user.setId(current_user.getId());
            user.setMail(email);
            if(password == null)
                user.setPassword(current_user.getPassword());
            else
                user.setPassword(password);
            user.setFirstName(firstname);
            user.setLastName(name);
            user.setBirthdate(birthdate);

            userDAOo.saveModification(user);
            resultat = "Modification réussie !";
            request.setAttribute("erreur_modifyUser",null);

            request.getSession().setAttribute("current_user",userDAOo.load(current_user.getId()));

        } else {
            resultat = "La modification a échoué!";
            request.setAttribute("erreur_modifyUser",erreurs);
        }
    }

    private void traiterEmail(String email){
        try {
            validationEmail(email);
        } catch (FormValidationException e){
            setErreur(CHAMP_EMAIL,e.getMessage());
        }
    }

    private void traiterPassword(String password,String confirmation){
        try {
            validationPassword(password,confirmation);
        } catch (FormValidationException e) {
            setErreur(CHAMP_PASS,e.getMessage());
        }
    }

    private void traiterName(String name,String firstName){
        try {
            validationName(name,firstName);
        } catch (FormValidationException e) {
            setErreur(CHAMP_NAME,e.getMessage());
        }
    }

    private void validationName(String name,String firstName) throws FormValidationException {
        if(name == null){
            if(firstName == null){
                throw new FormValidationException("Merci de saisir un nom et un prénom.");
            } else {
                throw new FormValidationException("Merci de saisir un nom.");
            }
        } else {
            if(firstName == null){
                throw new FormValidationException("Merci de saisir un prénom.");
            }
        }
    }


    private void validationPassword(String password, String confirmation) throws FormValidationException{
        if(password != null){
            if(confirmation == null){
                throw new FormValidationException("Merci de confirmer votre mot de passe.");
            } else if(!password.equals(confirmation)){
                throw new FormValidationException("Les mots de passe saisis ne correspondent pas.");
            }
        }
    }

    private void validationEmail(String email) throws FormValidationException{
        if(email == null){
            throw new FormValidationException("Merci de saisir une adresse mail.");
        }
        if(userDAOo.exist(email)){
            if(userDAOo.load(email).getId() != current_user.getId())
                throw new FormValidationException("Cette email est déjà utilisée.");
        }
    }

    public Map<String,String> getErreur() {
        return erreurs;
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    public String getResultat() {
        return resultat;
    }

    private String getValeurChamp(HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }

}
