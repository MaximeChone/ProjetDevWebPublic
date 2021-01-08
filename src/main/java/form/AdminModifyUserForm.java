package form;

import bean.UserBean;
import dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/** @author - Maxime Choné **/

public class AdminModifyUserForm {

    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS  = "password";
    private static final String CHAMP_NAME  = "name";
    private static final String CHAMP_FISTNAME  = "firstname";
    private static final String CHAMP_BIRTH  = "birthdate";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    private UserDAO userDAOo;
    private UserBean user_to_modify;

    public AdminModifyUserForm(UserDAO userDAO){
        this.userDAOo = userDAO;
    }

    public void modifyUser(HttpServletRequest request){
        String email = getValeurChamp(request,CHAMP_EMAIL);
        String password = getValeurChamp(request,CHAMP_PASS);
        String name = getValeurChamp(request, CHAMP_NAME);
        String firstname = getValeurChamp(request,CHAMP_FISTNAME);
        String birthdate = getValeurChamp(request,CHAMP_BIRTH);

        user_to_modify = userDAOo.load(Integer.parseInt(request.getParameter("id_user")));

        traiterEmail(email);
        traiterName(name,firstname);
        traiterPassword(password);

        if(erreurs.isEmpty()){
            UserBean user = new UserBean();

            user.setId(user_to_modify.getId());
            user.setMail(email);
            if(password == null)
                user.setPassword(user_to_modify.getPassword());
            else
                user.setPassword(password);
            user.setFirstName(firstname);
            user.setLastName(name);
            user.setBirthdate(birthdate);

            userDAOo.saveModification(user);
            resultat = "Modification réussie !";
            request.setAttribute("erreur_modifyUser",null);

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

    private void traiterPassword(String password){
        try {
            validationPassword(password);
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


    private void validationPassword(String password) throws FormValidationException{
        if(password != null){

        }
    }

    private void validationEmail(String email) throws FormValidationException{
        if(email == null){
            throw new FormValidationException("Merci de saisir une adresse mail.");
        }
        if(userDAOo.exist(email)){
            if(userDAOo.load(email).getId() != user_to_modify.getId())
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
