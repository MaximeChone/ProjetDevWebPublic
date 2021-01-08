package dao.sql;

import bean.UserBean;
import dao.UserDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/** @author - Maxime Choné **/

public class UserDAOsql implements UserDAO {

    private DAOFactorySQL factory;

    public UserDAOsql(DAOFactorySQL factory){
        this.factory = factory;
    }

    @Override
    public UserBean load(String mail) {

        String sql = "SELECT * FROM USER WHERE mail=?;";
        PreparedStatement pst = null;
        UserBean user = new UserBean();
        user.setId(-1);
        try {
            pst = factory.getConnexion().prepareStatement(sql);

            pst.setString(1,mail);

            ResultSet result = pst.executeQuery();

            if(result.next()){
                user.setId(result.getInt("id"));
                user.setRole(result.getString("role"));
                user.setMail(result.getString("mail"));
                user.setPassword(result.getString("password"));
                user.setFirstName(result.getString("firstname"));
                user.setLastName(result.getString("lastname"));
                user.setBirthdate(result.getString("birthdate"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;    }

    @Override
    public UserBean load(int id) {
        String sql = "SELECT * FROM USER WHERE id=?;";
        PreparedStatement pst = null;
        UserBean user = new UserBean();
        user.setId(-1);
        try {
            pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,id);

            ResultSet result = pst.executeQuery();

            if(result.next()){
                user.setId(result.getInt("id"));
                user.setRole(result.getString("role"));
                user.setMail(result.getString("mail"));
                user.setPassword(result.getString("password"));
                user.setFirstName(result.getString("firstname"));
                user.setLastName(result.getString("lastname"));
                user.setBirthdate(result.getString("birthdate"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean userMatch(String mail, String password) {
        PreparedStatement pst = null;
        boolean matching = false;
        try {
            String sql = "SELECT mail FROM USER where mail=? and password=?";
            pst = factory.getConnexion().prepareStatement(sql);
            pst.setString(1,mail);
            pst.setString(2,password);
            ResultSet result = pst.executeQuery();

            if(result.next())
                matching = true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return matching;
    }

    @Override
    public UserBean[] loadAll() {

        ArrayList<UserBean> usersList = new ArrayList<>();

        try {
            Statement st = factory.getConnexion().createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM USER;");

            UserBean user = null;
            while (result.next()){
                user = new UserBean();
                user.setId(result.getInt("id"));
                user.setRole(result.getString("role"));
                user.setMail(result.getString("mail"));
                user.setPassword(result.getString("password"));
                user.setFirstName(result.getString("firstname"));
                user.setLastName(result.getString("lastname"));
                user.setBirthdate(result.getString("birthdate"));
                usersList.add(user);

            }
            result.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        UserBean[] users = usersList.toArray(new UserBean[0]);

        return users;
    }

    @Override
    public void save(UserBean user) {
        try {
            String sql = "INSERT INTO USER (mail,password,firstname,lastname,birthdate,role) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

            pst.setString(1,user.getMail());
            pst.setString(2,user.getPassword());
            pst.setString(3, user.getFirstName());
            pst.setString(4,user.getLastName());
            pst.setString(5,user.getBirthdate());
            pst.setString(6,user.getRole());


            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    @Override
    public void saveModification(UserBean user) {

        PreparedStatement pst = null;
        try {
            UserBean userBeforeChange = load(user.getId());
            String sql;
            //mail
            if(!userBeforeChange.getMail().equals(user.getMail())){
                sql = "UPDATE user " +
                        "SET mail=? " +
                        "WHERE id=?";
                    pst = factory.getConnexion().prepareStatement(sql);
                    pst.setString(1, user.getMail());
                    pst.setInt(2,user.getId());

                    pst.executeUpdate();
            }
            //lastname
            if(!userBeforeChange.getLastName().equals(user.getLastName())){
                sql = "UPDATE user " +
                        "SET lastname=? " +
                        "WHERE id=?";
                pst = factory.getConnexion().prepareStatement(sql);
                pst.setString(1, user.getLastName());
                pst.setInt(2,user.getId());

                pst.executeUpdate();
            }
            //password
            if(!userBeforeChange.getPassword().equals(user.getPassword())){
                sql = "UPDATE user " +
                        "SET password=? " +
                        "WHERE id=?";
                pst = factory.getConnexion().prepareStatement(sql);
                pst.setString(1, user.getPassword());
                pst.setInt(2,user.getId());

                pst.executeUpdate();
            }
            //firstname
            if(!userBeforeChange.getFirstName().equals(user.getFirstName())){
                sql = "UPDATE user " +
                        "SET firstname=? " +
                        "WHERE id=?";
                pst = factory.getConnexion().prepareStatement(sql);
                pst.setString(1, user.getFirstName());
                pst.setInt(2,user.getId());

                pst.executeUpdate();
            }
            //birthdate
            if(!userBeforeChange.getBirthdate().equals(user.getBirthdate())){
                sql = "UPDATE user " +
                        "SET birthdate=? " +
                        "WHERE id=?";
                pst = factory.getConnexion().prepareStatement(sql);
                pst.setString(1, user.getBirthdate());
                pst.setInt(2,user.getId());

                pst.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean exist(String mail) {
        PreparedStatement pst = null;
        boolean exist = false;
        try {
            String sql = "SELECT mail FROM USER where mail=?";
            pst = factory.getConnexion().prepareStatement(sql);
            pst.setString(1,mail);

            ResultSet result = pst.executeQuery();

            if(result.next())
                exist = true;

            result.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return exist;
    }

    @Override
    public void delete(UserBean user) {
        PreparedStatement pst = null;
        if(user != null) {
            if (user.getId() != -1) {
                try {
                    String sql = "DELETE FROM USER where id=?";
                    pst = factory.getConnexion().prepareStatement(sql);
                    pst.setInt(1, user.getId());

                    pst.executeUpdate();

                    pst.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Override
    public UserBean[] friendList(UserBean user) {
        ArrayList<UserBean> friendList = new ArrayList<>();

        try {
            String sql ="SELECT id_user_1 from friend where " +
                    "friend.id_user_2 = ? and request_state = 'accepted'";
            String sql2 = "SELECT id_user_2 from friend where " +
                    "friend.id_user_1 = ? and request_state = 'accepted';";


            PreparedStatement pst = factory.getConnexion().prepareStatement(sql);
            PreparedStatement pst2 = factory.getConnexion().prepareStatement(sql2);

            pst.setInt(1,user.getId());
            pst2.setInt(1,user.getId());

            ResultSet result = pst.executeQuery();
            UserBean friend = null;
            while (result.next()){
                friend = load(result.getInt("id_user_1"));
                friendList.add(friend);
            }
            result.close();


            ResultSet result2 = pst2.executeQuery();
            UserBean friend2 = null;
            while (result2.next()){
                friend2 = load(result2.getInt("id_user_2"));
                friendList.add(friend2);
            }
            result2.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return friendList.toArray(new UserBean[0]);
    }

    @Override
    public UserBean[] friendRequested(UserBean user) {
        ArrayList<UserBean> friendRequestedList = new ArrayList<>();

        try {
            String sql ="SELECT id_user_2 from friend where " +
                    "friend.id_user_1 = ? and request_state = 'pending'";
            PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,user.getId());

            ResultSet result = pst.executeQuery();

            UserBean requested = null;
            while (result.next()){
                requested = load(result.getInt("id_user_2"));
                friendRequestedList.add(requested);
            }
            result.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        UserBean[] users = friendRequestedList.toArray(new UserBean[0]);

        return users;
    }

    @Override
    public UserBean[] friendRequesting(UserBean user) {
        ArrayList<UserBean> friendRequestingList = new ArrayList<>();

        try {
            String sql ="SELECT id_user_1 from friend where " +
                    "friend.id_user_2 = ? and request_state = 'pending'";
            PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,user.getId());

            ResultSet result = pst.executeQuery();

            UserBean userWhoIsRequesting = null;
            while (result.next()){
                userWhoIsRequesting = load(result.getInt("id_user_1"));
                friendRequestingList.add(userWhoIsRequesting);
            }
            result.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        UserBean[] users = friendRequestingList.toArray(new UserBean[0]);

        return users;    }
}
