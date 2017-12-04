package pens.ac.id.model;

public class Buku {

    private long id;
    private String author;
    private String judul;
    private long tahun;
    private long stokTersedia;
    private long dalamPeminjaman;

    public Buku() {

    }

    public Buku(long id, String author, String judul, long tahun, long stok_tersedia, long dalam_peminjaman) {
        this.id = id;
        this.author = author;
        this.judul = judul;
        this.tahun = tahun;
        this.stokTersedia = stok_tersedia;
        this.dalamPeminjaman = dalam_peminjaman;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public long getTahun() {
        return tahun;
    }

    public void setTahun(long tahun) {
        this.tahun = tahun;
    }

    public long getStokTersedia() {
        return stokTersedia;
    }

    public void setStokTersedia(long stok_tersedia) {
        this.stokTersedia = stok_tersedia;
    }

    public long getDalamPeminjaman() {
        return dalamPeminjaman;
    }

    public void setDalamPeminjaman(long dalam_peminjaman) {
        this.dalamPeminjaman = dalam_peminjaman;
    }

    @Override
    public String toString() {
        return judul;
    }

}
