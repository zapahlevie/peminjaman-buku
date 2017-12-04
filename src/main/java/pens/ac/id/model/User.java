package pens.ac.id.model;

public class User {

    Long id;
    String user;
    String pass;

    public User() {
    }

    public User(Long id, String user, String pass) {
        this.id = id;
        this.user = user;
        this.pass = pass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return user;
    }
}
