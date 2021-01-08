package dao;

import bean.FriendBean;


/** @author - Maxime Choné **/

public interface FriendDAO {


    FriendBean loadFriend(int id_from,int id_to);

    void createFriendRequest(FriendBean friendRequest);

    void acceptFriendRequest(FriendBean friendRequest);

    void refuseFriendRequest(FriendBean friendRequest);

    void cancelFriendRequest(FriendBean friendRequest);

    void deleteFriend(FriendBean friendRequest);

    boolean exist(int id_user_1, int id_user_2);


}
