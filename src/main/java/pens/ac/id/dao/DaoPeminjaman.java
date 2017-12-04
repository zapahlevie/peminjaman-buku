package pens.ac.id.dao;

import java.util.List;
import pens.ac.id.model.Peminjaman;

public interface DaoPeminjaman {

    public void savePeminjaman(Peminjaman peminjaman);

    public void updatePeminjaman(Peminjaman peminjaman);

    public void deletePeminjaman(Long peminjamanId);

    public List<Peminjaman> getAllPeminjaman();

    public Peminjaman getPeminjamanById(Long peminjamanId);

    public int getDbSize();
}
