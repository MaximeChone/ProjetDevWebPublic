package form;

import bean.UserBean;
import dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/** @author - Maxime Choné **/

public class ConnexionForm {

    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS  = "password";
    private static final String MATCH = "match";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    private UserDAO userDAOo;

    public ConnexionForm(UserDAO userDAO){
        this.userDAOo = userDAO;
    }

    public void connectUser(HttpServletRequest request){

        String email = getValeurChamp(request,"email");
        String password = getValeurChamp(request,"password");

        traiterEmail(email);
        traiterPassword(password);
        if(erreurs.isEmpty())
        traiterConnexion(email,password);

        if(erreurs.isEmpty()){
            UserBean current_user = userDAOo.load(email);
            request.getSession().setAttribute("current_user",current_user);
            request.setAttribute("erreur_login",null);
            resultat = "Connexion réussie !";
        } else {
            resultat = "La connexion a échouée!";
            request.getSession().setAttribute("current_user",null);
            request.setAttribute("erreur_login",erreurs);

        }
    }

    private void traiterConnexion(String email,String password){
        try{
            validationConnexion(email,password);
        } catch (FormValidationException e) {
            setErreur(MATCH,e.getMessage());
        }
    }

    private void traiterEmail(String email){
        try{
            validationEmail(email);
        } catch (FormValidationException e) {
            setErreur(CHAMP_EMAIL,e.getMessage());
        }
    }

    private void traiterPassword(String password){
        try{
            validationPassword(password);
        } catch (FormValidationException e){
            setErreur(CHAMP_PASS,e.getMessage());
        }
    }

    private void validationConnexion(String email,String password) throws FormValidationException{
        if(!userDAOo.userMatch(email, password)){
            throw new FormValidationException("Adresse mail ou mot de passe incorrect.");
        }
    }


    private void validationEmail(String email) throws FormValidationException {
        if(email == null){
            throw new FormValidationException("Merci de saisir une adresse mail.");
        }
    }

    private void validationPassword(String password) throws FormValidationException {
        if(password == null){
            throw new FormValidationException("Merci de saisir un mot de passe.");
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

    public Map<String,String> getErreur() {
        return erreurs;
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    public String getResultat() {
        return resultat;
    }
}
