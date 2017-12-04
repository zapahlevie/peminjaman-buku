package pens.ac.id.dao;

import java.util.List;
import pens.ac.id.model.Mahasiswa;

public interface DaoMahasiswa {

    public void saveMahasiswa(Mahasiswa mahasiswa);

    public void updateMahasiswa(Mahasiswa mahasiswa);

    public void deleteMahasiswa(Long mahasiswaId);

    public List<Mahasiswa> getAllMahasiswa();

    public Mahasiswa getMahasiswaById(Long mahasiswaId);

    public int getDbSize();
}
