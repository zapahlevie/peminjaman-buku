package pens.ac.id.dao;

import java.util.List;

import pens.ac.id.model.User;

public interface DaoUser {

    public void saveUser(User user);

    public List<User> getAllUser();

    public User getUserById(Long userId);

    public int getDbSize();
}
