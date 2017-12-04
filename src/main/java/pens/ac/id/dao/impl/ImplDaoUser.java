package pens.ac.id.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import pens.ac.id.dao.DaoUser;
import pens.ac.id.model.User;

public class ImplDaoUser implements DaoUser {

    private List<User> DbUser;
    private DataSource dataSource;

    public ImplDaoUser() {
        DbUser = new ArrayList<User>();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO user(user, pass) VALUES (?, ?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUser());
            ps.setString(2, user.getPass());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUser() {
        String sql = "SELECT * FROM user";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            User user = null;
            ResultSet rs = ps.executeQuery();
            while (true) {
                if (rs.next()) {
                    user = new User(
                            rs.getLong("id"),
                            rs.getString("user"),
                            rs.getString("pass")
                    );
                    DbUser.add(user);
                } else {
                    break;
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return DbUser;
    }

    @Override
    public User getUserById(Long userId) {
        String sql = "SELECT * FROM user WHERE id = ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, userId);
            User user = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getLong("id"),
                        rs.getString("user"),
                        rs.getString("pass")
                );
            }
            rs.close();
            ps.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getDbSize() {
        DbUser = getAllUser();
        return DbUser.size();
    }

}
