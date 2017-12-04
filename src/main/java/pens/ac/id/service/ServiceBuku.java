package pens.ac.id.service;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pens.ac.id.model.Buku;
import pens.ac.id.dao.DaoBuku;

public class ServiceBuku {

    private DaoBuku daoBuku;

    public ServiceBuku() {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("Spring-Module.xml");
        daoBuku = (DaoBuku) context.getBean("bukuDAO");
    }

    public DaoBuku getDaoBuku() {
        return daoBuku;
    }

    public void setDaoBuku(DaoBuku daoBuku) {
        this.daoBuku = daoBuku;
    }

    public void saveBuku(Buku buku) {
        daoBuku.saveBuku(buku);
    }

    public void updateBuku(Buku buku) {
        daoBuku.updateBuku(buku);
    }

    public void deleteBuku(Long bukuId) {
        daoBuku.deleteBuku(bukuId);
    }

    public List<Buku> getAllBuku() {
        return daoBuku.getAllBuku();
    }

    public Buku getBukuById(Long bukuId) {
        return daoBuku.getBukuById(bukuId);
    }

    public int getDbSize() {
        return daoBuku.getDbSize();
    }
}
