package pens.ac.id;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pens.ac.id.dao.DaoMahasiswa;
import pens.ac.id.dao.DaoUser;
import pens.ac.id.model.Mahasiswa;
import pens.ac.id.model.User;

@SpringBootApplication
public class MySpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringApplication.class, args);
    }
}
