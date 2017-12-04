package pens.ac.id.service;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pens.ac.id.model.Mahasiswa;
import pens.ac.id.dao.DaoMahasiswa;

public class ServiceMahasiswa {

    private DaoMahasiswa daoMahasiswa;

    public ServiceMahasiswa() {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("Spring-Module.xml");
        daoMahasiswa = (DaoMahasiswa) context.getBean("mahasiswaDAO");
    }

    public DaoMahasiswa getDaoMahasiswa() {
        return daoMahasiswa;
    }

    public void setDaoMahasiswa(DaoMahasiswa daoMahasiswa) {
        this.daoMahasiswa = daoMahasiswa;
    }

    public void saveMahasiswa(Mahasiswa mahasiswa) {
        daoMahasiswa.saveMahasiswa(mahasiswa);
    }

    public void updateMahasiswa(Mahasiswa mahasiswa) {
        daoMahasiswa.updateMahasiswa(mahasiswa);
    }

    public void deleteMahasiswa(Long mahasiswaId) {
        daoMahasiswa.deleteMahasiswa(mahasiswaId);
    }

    public List<Mahasiswa> getAllMahasiswa() {
        return daoMahasiswa.getAllMahasiswa();
    }

    public Mahasiswa getMahasiswaById(Long mahasiswaId) {
        return daoMahasiswa.getMahasiswaById(mahasiswaId);
    }

    public int getDbSize() {
        return daoMahasiswa.getDbSize();
    }

}
