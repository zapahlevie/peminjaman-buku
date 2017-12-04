package pens.ac.id.service;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pens.ac.id.AppContainer;
import pens.ac.id.dao.DaoUser;
import pens.ac.id.model.User;

public class ServiceUser {

    private DaoUser daoUser;

    public ServiceUser() {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("Spring-Module.xml");
        daoUser = (DaoUser) context.getBean("userDAO");
    }

    public DaoUser getDaoUser() {
        return daoUser;
    }

    public void setDaoUser(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    public void saveUser(User user) {
        daoUser.saveUser(user);
    }

    public List<User> getAllUser() {
        return daoUser.getAllUser();
    }

    public User getUserById(Long userId) {
        return daoUser.getUserById(userId);
    }

    public int getDbSize() {
        return daoUser.getDbSize();
    }
    
    public User login(User user){
        for (User u : AppContainer.getInstance().getServiceUser().getAllUser()) {
            if (u.getUser().equals(user.getUser()) && u.getPass().equals(user.getPass())) {
                return user;
            }
        }
        return null;
    }

}
