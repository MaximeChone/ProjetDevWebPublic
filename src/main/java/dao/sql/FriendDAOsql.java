package dao.sql;

import bean.FriendBean;
import dao.FriendDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** @author - Maxime Choné **/

public class FriendDAOsql implements FriendDAO {

    DAOFactorySQL factory;

    public FriendDAOsql(DAOFactorySQL factorySQL){
        factory = factorySQL;
    }

    @Override
    public FriendBean loadFriend(int id_from, int id_to) {

        String sql = "SELECT * FROM friend WHERE id_user_1 = ? and id_user_2 = ?;";
        PreparedStatement pst = null;
        FriendBean friend = new FriendBean();
        friend.setId_from(-1);
        friend.setId_to(-1);
        try {
            pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,id_from);
            pst.setInt(2,id_to);

            ResultSet result = pst.executeQuery();

            if(result.next()){
                friend.setId_from(result.getInt("id_user_1"));
                friend.setId_to(result.getInt("id_user_2"));
                friend.setState(result.getString("request_state"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return friend;
    }

    @Override
    public void createFriendRequest(FriendBean friendRequest) {
        if(friendRequest.getState().equals("notExisting"))
        try {
            String sql = "INSERT INTO FRIEND (id_user_1,id_user_2) VALUES (?,?)";
            PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,friendRequest.getId_from());
            pst.setInt(2,friendRequest.getId_to());

            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void acceptFriendRequest(FriendBean friendRequest) {
        if(friendRequest.getState().equals("pending")){
            try {
                String sql = "UPDATE friend " +
                        "SET request_state = 'accepted' "+
                        "WHERE id_user_1 = ? and id_user_2 = ?";

                PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

                pst.setInt(1,friendRequest.getId_from());
                pst.setInt(2,friendRequest.getId_to());

                pst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void refuseFriendRequest(FriendBean friendRequest) {
        if(friendRequest.getState().equals("pending")){
            try {
                String sql = "DELETE from friend " +
                        "WHERE id_user_1 = ? and id_user_2 = ?";

                PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

                pst.setInt(1,friendRequest.getId_from());
                pst.setInt(2,friendRequest.getId_to());

                pst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void cancelFriendRequest(FriendBean friendRequest) {
        if(friendRequest.getState().equals("pending")){
            try {
                String sql = "DELETE from friend " +
                        "WHERE id_user_1 = ? and id_user_2 = ?";

                PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

                pst.setInt(1,friendRequest.getId_from());
                pst.setInt(2,friendRequest.getId_to());

                pst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void deleteFriend(FriendBean friendRequest) {
        if(friendRequest.getState().equals("accepted")){
            try {
                String sql = "DELETE from friend " +
                        "WHERE id_user_1 = ? and id_user_2 = ?";

                PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

                pst.setInt(1,friendRequest.getId_from());
                pst.setInt(2,friendRequest.getId_to());

                pst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public boolean exist(int id_user_1, int id_user_2) {

        String sql = "SELECT * FROM friend where (id_user_1 = ? and id_user_2 = ?) or (id_user_1 = ? and id_user_2 = ?)";
        PreparedStatement pst = null;
        boolean exist = false;
        try {
            pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,id_user_1);
            pst.setInt(2,id_user_2);
            pst.setInt(3,id_user_2);
            pst.setInt(4,id_user_1);

            ResultSet result = pst.executeQuery();

            if(result.next()){
                exist = true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return exist;
    }


}
