package bean;

/** @author - Maxime Choné **/

public class FriendBean {

    private int id_from;
    private int id_to;
    private String request_state = "notExisting";

    public FriendBean(){
    }

    public void setId_from(int id_from) {
        this.id_from = id_from;
    }

    public void setState(String state) {
        this.request_state = state;
    }

    public void setId_to(int id_to) {
        this.id_to = id_to;
    }

    public int getId_from() {
        return id_from;
    }

    public int getId_to() {
        return id_to;
    }

    public String getState() {
        return request_state;
    }

    @Override
    public String toString() {
        return "FriendBean{" +
                "id_from=" + id_from +
                ", id_to=" + id_to +
                ", request_state='" + request_state + '\'' +
                '}';
    }
}
