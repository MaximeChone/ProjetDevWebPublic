package dao;

import bean.UserBean;

/** @author - Maxime Choné **/

public interface UserDAO {

    public UserBean load(String mail);

    public UserBean load(int id);

    public boolean userMatch(String mail, String password);

    public UserBean[] loadAll();

    public void save(UserBean user);

    public void saveModification(UserBean user);

    public boolean exist(String mail);

    public void delete(UserBean user);



    /**
     *
     * @param user
     * @return liste des utilisateurs ami avec user
     */
    public UserBean[] friendList(UserBean user);

    /**
     *
     * @param user
     * @return liste des utilisateurs à qui user a envoyé une demande d'ami
     */
    public UserBean[] friendRequested(UserBean user);

    /**
     * @param user
     * @return liste des utilisateurs qui ont envoyé une demande d'ami à user
     */
    public UserBean[] friendRequesting(UserBean user);
}
