package pens.ac.id.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pens.ac.id.AppContainer;
import pens.ac.id.model.Peminjaman;
import pens.ac.id.dao.DaoPeminjaman;
import pens.ac.id.model.CustomClass;

public class ServicePeminjaman {

    private DaoPeminjaman daoPeminjaman;
    private List<CustomClass> models;

    public ServicePeminjaman() {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("Spring-Module.xml");
        daoPeminjaman = (DaoPeminjaman) context.getBean("peminjamanDAO");
    }

    public DaoPeminjaman getDaoPeminjaman() {
        return daoPeminjaman;
    }

    public void setDaoPeminjaman(DaoPeminjaman daoPeminjaman) {
        this.daoPeminjaman = daoPeminjaman;
    }

    public void savePeminjaman(Peminjaman peminjaman) {
        daoPeminjaman.savePeminjaman(peminjaman);
    }

    public void updatePeminjaman(Peminjaman peminjaman) {
        daoPeminjaman.updatePeminjaman(peminjaman);
    }

    public void deletePeminjaman(Long peminjamanId) {
        daoPeminjaman.deletePeminjaman(peminjamanId);
    }

    public List<Peminjaman> getAllPeminjaman() {
        return daoPeminjaman.getAllPeminjaman();
    }

    public Peminjaman getPeminjamanById(Long peminjamanId) {
        return daoPeminjaman.getPeminjamanById(peminjamanId);
    }

    public int getDbSize() {
        return daoPeminjaman.getDbSize();
    }
    
    public List<CustomClass> getModel() {
        models = new ArrayList<CustomClass>();
            for (Peminjaman p : AppContainer.getInstance().getServicePeminjaman().getAllPeminjaman()) {
                String nama = AppContainer.getInstance().getServiceMahasiswa().getMahasiswaById(p.getMahasiswaId()).getNama();
                String judul = AppContainer.getInstance().getServiceBuku().getBukuById(p.getBukuId()).getJudul();
                CustomClass c = new CustomClass(p.getId(), nama, judul, p.getTanggal(), p.getStatus());
                models.add(c);
            }
        return models;
    }
}
