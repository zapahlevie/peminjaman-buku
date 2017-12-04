package pens.ac.id.model;

public class Mahasiswa {

    private long id;
    private long nrp;
    private String nama;

    public Mahasiswa() {

    }

    public Mahasiswa(long id, long nrp, String nama) {
        this.id = id;
        this.nrp = nrp;
        this.nama = nama;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNrp() {
        return nrp;
    }

    public void setNrp(long nrp) {
        this.nrp = nrp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return nama;
    }

}
