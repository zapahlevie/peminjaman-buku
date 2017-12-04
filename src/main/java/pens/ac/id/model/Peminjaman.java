package pens.ac.id.model;

public class Peminjaman {

    private long id;
    private long mahasiswaId;
    private long bukuId;
    private String tanggal;
    private String status;

    public Peminjaman() {

    }

    public Peminjaman(String status) {
        this.status = status;
    }

    public Peminjaman(long id, long mahasiswaId, long bukuId, String tanggal, String status) {
        this.id = id;
        this.mahasiswaId = mahasiswaId;
        this.bukuId = bukuId;
        this.tanggal = tanggal;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMahasiswaId() {
        return mahasiswaId;
    }

    public void setMahasiswaId(long mahasiswaId) {
        this.mahasiswaId = mahasiswaId;
    }

    public long getBukuId() {
        return bukuId;
    }

    public void setBukuId(long bukuId) {
        this.bukuId = bukuId;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
