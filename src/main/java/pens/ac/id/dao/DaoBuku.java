package pens.ac.id.dao;

import java.util.List;
import pens.ac.id.model.Buku;

public interface DaoBuku {

    public void saveBuku(Buku buku);

    public void updateBuku(Buku buku);

    public void deleteBuku(Long bukuId);

    public List<Buku> getAllBuku();

    public Buku getBukuById(Long bukuId);

    public int getDbSize();
}
